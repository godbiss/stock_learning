package com.zhu.api_course.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhu.api_course.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 服务接口
 *
 * @author zhu
 * @since 2022-04-13 12:38:19
 * @description 由 Mybatisplus Code Generator 创建
 */
public interface CourseService extends IService<Course> {

    int addCourseToOrder(Integer courseId, Integer userId,
                         Date createTime, Date updateTime);
}
