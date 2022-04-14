package com.zhu.api_course.controller;

import com.zhu.api_course.service.CourseStarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.common.support.IController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务控制器
 *
 * @author zhu
 * @since 2022-04-13 12:38:20
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/courseStar")
public class CourseStarController implements IController {
    private final CourseStarService courseStarService;

}