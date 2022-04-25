package com.zhu.api_course.controller;

import com.zhu.api_course.entity.Course;
import com.zhu.api_course.entity.User;
import com.zhu.api_course.feign.UserService;
import com.zhu.api_course.feign.VideoService;
import com.zhu.api_course.service.CourseService;
import com.zhu.api_course.service.impl.CourseServiceImpl;
import com.zhu.api_course.vo.CourseVideoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.core.result.R;
import net.dreamlu.mica.core.result.SystemCode;
import net.dreamlu.mica.core.utils.$;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
    private final CourseServiceImpl courseService;

    @Autowired
    UserService userService;

    @Autowired
    VideoService videoService;
    /**
     * 用户购买方法
     * @param courseId
     * @param userId
     * @return
     */
    @PutMapping("/buy")
    public R addCourseToOrder(@RequestParam("courseId") Integer courseId, @RequestParam("userId") Integer userId){
        User user = userService.getUserById(userId);
        Course course = courseService.getById(courseId);
        if(user.getMoney().compareTo(course.getPrice()) == -1) return fail("余额不足请及时充值!");

        Date now = new Date();
        user.setMoney(user.getMoney().subtract(course.getPrice()));
        userService.updateUserMoney(user);
        int res = courseService.addCourseToOrder(courseId, userId, now, now);
        log.info("res ==> " + res);
        return res > 0 ? success("购买成功!") : fail("购买失败!");
    }

    /**
     *
     * @param course @requesBody与form-data的multipart有冲突
     * @param imgFile
     * @return
     */
    @PutMapping("/insertCourse")
    public R insertCourse(Course course,
                          @RequestParam(value = "imgFile", required = false) MultipartFile imgFile){
        // feign传参返回的字符串会带两“号,记得删除
        if(imgFile != null){
            String imgUpload = videoService.imgUpload(imgFile).replace("\"", "");
            course.setCover(imgUpload);
        }
        boolean save = courseService.insertCourse(course);
        return save ? success("插入成功!") : fail("插入失败!");
    }

    @DeleteMapping("/deleteCourse/{id}")
    public R deleteCourse(@PathVariable Integer id){
        return courseService.removeById(id) ? success("删除课程成功!") : fail("删除课程失败!");
    }

    @GetMapping("/listAll")
    public R listAll(){
        List<Course> courses = courseService.listAll();
        return courses.size() != 0 ? success(courses) : fail("获取信息失败!");
    }

    @GetMapping("/listByUser/{userId}")
    public R listByUser(@PathVariable("userId") Integer userId){
        List<Course> courseList = courseService.listByUser(userId);
        if(courseList.size() == 0) return fail(SystemCode.DATA_NOT_EXIST, "列表值为空");
        return success(courseList);
    }

    /**
     * 将course以及video一起传出
     * @param courseId
     * @return
     */
    @GetMapping("/listWithVideo")
    public R listWithVideo(){
        List<Course> courseList = courseService.listAll();
        List<CourseVideoVO> courseVideoVOS = new ArrayList<>();
        for (Course c:courseList){
            courseVideoVOS.add(new CourseVideoVO(c, videoService.listVideoByCourse(c.getId())));
        }
        return courseVideoVOS.size() > 0 ? success(courseVideoVOS) : fail(SystemCode.DATA_NOT_EXIST);
    }

    @GetMapping("/getOneWithVideo/{courseId}")
    public R getOneWithVideo(@PathVariable("courseId") Integer courseId){
        Course course = courseService.getOne(courseId);
        CourseVideoVO courseVideoVO = new CourseVideoVO(course, videoService.listVideoByCourse(course.getId()));
        return $.isEmpty(courseVideoVO) ? fail(SystemCode.DATA_NOT_EXIST) : success(courseVideoVO);
    }

    @GetMapping("/getOneForFeign/{courseId}")
    public Course getOneForFeign(@PathVariable("courseId") Integer courseId){
        return courseService.getOne(courseId);
    }
}
