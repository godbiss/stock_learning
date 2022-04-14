package com.zhu.api_video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.api_video.entity.Video;
import com.zhu.api_video.mapper.VideoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.zhu.api_video.service.VideoService;
import org.springframework.stereotype.Service;

/**
 * 服务接口实现
 *
 * @author zhu
 * @since 2022-04-13 01:08:57
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    private final VideoMapper videoMapper;

}
