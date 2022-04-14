package com.zhu.api_course.controller;

import com.zhu.api_course.entity.Course;
import com.zhu.api_course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 服务控制器
 *
 * @author zhu
 * @since 2022-04-13 12:38:19
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController implements IController {
    private final CourseService courseService;

    /**
     * 用户购买方法
     * @param courseId
     * @param userId
     * @return
     */
    @PutMapping("/buy")
    public R addCourseToOrder(@RequestParam("courseId") Integer courseId, @RequestParam("userId") Integer userId){

        Date now = new Date();
        int res = courseService.addCourseToOrder(courseId, userId, now, now);
        log.info("res ==> " + res);
        return res > 0 ? success("购买成功!") : fail("购买失败!");
    }

    @PutMapping("/insertCourse")
    public R insertCourse(@RequestBody Course course){
        boolean save = courseService.save(course);
        return save ? success("插入成功!") : fail("插入失败!");
    }

    @DeleteMapping("/deleteCourse/{id}")
    public R deleteCourse(@PathVariable Integer id){
        return courseService.removeById(id) ? success("删除课程成功!") : fail("删除课程失败!");
    }
}
