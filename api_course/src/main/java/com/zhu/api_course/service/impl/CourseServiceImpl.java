package com.zhu.api_course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.api_course.entity.Course;
import com.zhu.api_course.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.zhu.api_course.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public int addCourseToOrder(Integer courseId, Integer userId, Date createTime, Date updateTime){
        return courseMapper.addCourseToOrder(courseId, userId, new Date(), new Date());
    }

}
