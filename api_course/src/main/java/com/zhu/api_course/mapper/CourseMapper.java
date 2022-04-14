package com.zhu.api_course.mapper;

import com.zhu.api_course.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * (t_course)数据Mapper
 *
 * @author zhu
 * @since 2022-04-13 12:38:19
 * @description 由 Mybatisplus Code Generator 创建
*/
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    int addCourseToOrder(@Param("courseId") Integer courseId, @Param("userId") Integer userId,
                         @Param("createTime") Date createTime, @Param("updateTime") Date updateTime);
}
