package com.zhu.api_course.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.MapUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.api_course.entity.Course;
import com.zhu.api_course.mapper.CourseMapper;
import com.zhu.api_course.util.strategy.HgetOneStrategy;
import com.zhu.api_course.util.strategy.HgetQueryStrategy;
import com.zhu.api_course.util.JedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.zhu.api_course.service.CourseService;
import net.dreamlu.mica.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 服务接口实现
 *
 * @author zhu
 * @since 2022-04-13 12:38:19
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    private final CourseMapper courseMapper;
    private Jedis jedis;

    @Autowired
    private HgetQueryStrategy hgetQueryStrategy;

    @Autowired
    private HgetOneStrategy hgetOneStrategy;
    /**
     * 购买课程方法
     * @param courseId
     * @param userId
     * @param createTime
     * @param updateTime
     * @return
     */
    public int addCourseToOrder(Integer courseId, Integer userId, Date createTime, Date updateTime){
        return courseMapper.addCourseToOrder(courseId, userId, new Date(), new Date());
    }

    /**
     * 删除课程信息
     * @param courseId
     * @return
     */
    public Boolean deleteCourse(Integer courseId){
        jedis = JedisUtil.getJedis();
        boolean flag = removeById(courseId);
        //删除redis中的缓存信息
        if (flag){
            String courseStr = jedis.hget("course_list", String.valueOf(courseId));
            if(!StringUtil.isEmpty(courseStr)) jedis.hdel("course_list", String.valueOf(courseId));

        }
        jedis.close();
        return flag;
    }

    /**
     * 添加或更新课程
     *
     * @param course course中没有更新时间 如果是插入则没有创建时间
     * @return
     */
    public Boolean insertCourse(Course course){
        jedis = JedisUtil.getJedis();
        if (course.getCreatetime() == null) course.setCreatetime(new Date());
        course.setUpdatetime(new Date());

        boolean flag = this.saveOrUpdate(course);
        if(flag){
            String courseStr = jedis.hget("course_list", String.valueOf(course.getId()));
            if(!StringUtil.isEmpty(courseStr)) jedis.hset("course_list",
                    String.valueOf(course.getId()), JSON.toJSONString(course));
            return flag;
        }
        return flag;
    }

    /**
     * 列出课程信息
     * @return
     */
    public List<Course> listAll(){
        jedis = JedisUtil.getJedis();
        Map<String, String> stringMap = jedis.hgetAll("course_list");
        if(MapUtils.isEmpty(stringMap)){
            List<Course> list = list();
            Map<String, String> collect = list.stream().collect(Collectors.toMap(course -> {
                        return String.valueOf(course.getId());
                    },
                    course -> {
                        return JSON.toJSONString(course);
                    }));
            jedis.hmset("course_list", collect);
            jedis.expire("course_list", 10);
            jedis.close();
            return list;
        }
        jedis.close();
        return stringMap.values().stream().map(s -> {
            return JSON.parseObject(s, Course.class);
        }).collect(Collectors.toList());
    }


    public List<Course> listByUser(Integer userId){
        List<Course> courses = JedisUtil.doSomethingHash("course_list_user", userId,
                "create_user", hgetQueryStrategy, Course.class);
        return courses;
    }

    public List<Course> listByCatagory(Integer catagoryId){
        List<Course> courses = JedisUtil.doSomethingHash("course_list_catagory", catagoryId,
                "create_catagory", hgetQueryStrategy, Course.class);
        return courses;
    }

    public Course getOne(Integer courseId){
        Course course = JedisUtil.doSomethingHashOne("course_list", courseId, "id", hgetOneStrategy, Course.class);
        return course;
    }
}
