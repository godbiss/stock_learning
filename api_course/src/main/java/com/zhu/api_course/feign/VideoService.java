package com.zhu.api_course.feign;

import com.zhu.api_course.entity.Video;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient("api-video")
public interface VideoService {

    @PostMapping(value = "/img/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String imgUpload(@RequestPart(value = " imgFile") MultipartFile imgFile);

    @GetMapping("/video/listVideoByCourse/{courseId}")
    public List<Video> listVideoByCourse(@PathVariable("courseId") Integer courseId);
}
