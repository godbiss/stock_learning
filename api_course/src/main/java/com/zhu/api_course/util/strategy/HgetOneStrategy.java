package com.zhu.api_course.util.strategy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhu.api_course.entity.Course;
import com.zhu.api_course.service.impl.CourseServiceImpl;
import com.zhu.api_course.util.QueryStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HgetOneStrategy implements QueryStrategy<Integer, Course> {

    @Autowired
    CourseServiceImpl courseService;

    @Override
    public List<Course> doSomething(String column, Integer fieldKey) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq(column, fieldKey);
        return courseService.list(wrapper);
    }
}
