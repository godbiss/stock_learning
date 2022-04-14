package com.zhu.api_course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.api_course.entity.CourseStar;
import com.zhu.api_course.mapper.CourseStarMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.zhu.api_course.service.CourseStarService;
import org.springframework.stereotype.Service;

/**
 * 服务接口实现
 *
 * @author zhu
 * @since 2022-04-13 12:38:20
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CourseStarServiceImpl extends ServiceImpl<CourseStarMapper, CourseStar> implements CourseStarService {
    private final CourseStarMapper courseStarMapper;

}
