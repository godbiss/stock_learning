package com.zhu.api_comment.feign;

import com.zhu.api_comment.entity.Video;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("api-video")
public interface VideoService {

    @GetMapping("/video/getOneForFeign/{videoId}")
    public Video getOneForFeign(@PathVariable("videoId") Integer videoId);
}
