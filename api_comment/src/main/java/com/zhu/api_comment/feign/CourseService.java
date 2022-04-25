package com.zhu.api_comment.feign;

import com.zhu.api_comment.entity.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("api-course")
public interface CourseService {

    @GetMapping("/course/getOneForFeign/{courseId}")
    public Course getOneForFeign(@PathVariable("courseId") Integer courseId);
}
