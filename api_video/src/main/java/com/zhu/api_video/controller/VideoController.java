package com.zhu.api_video.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhu.api_video.entity.Video;
import com.zhu.api_video.service.VideoService;
import com.zhu.api_video.service.impl.NginxService;
import com.zhu.api_video.service.impl.VideoServiceImpl;
import com.zhu.api_video.util.FtpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.core.result.R;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务控制器
 *
 * @author zhu
 * @since 2022-04-13 01:08:57
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/video")
public class VideoController implements IController {
    @Autowired
    private final VideoServiceImpl videoService;

    @Autowired
    NginxService nginxService;

    /**
     * 可上传图片、视频，只需在nginx配置中配置可识别的后缀
     */
    @PostMapping("/upload")
    public String videoUpload(@RequestParam(value = "file") MultipartFile uploadFile,
                                @RequestParam("name") String name,
                              @RequestParam(value = "courseId", required = false) Integer courseId) {
        long begin = System.currentTimeMillis();
        String json = "";
        try {
            Object result = nginxService.uploadVideo(uploadFile);
            videoService.save(new Video(name, (String) result, new Date(), new Date(), courseId));
            json = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("任务结束，共耗时：[" + (end-begin) + "]毫秒");
        return json;
    }

    @PostMapping("/uploads")
    public Object videosUpload(@RequestParam(value = "file") MultipartFile[] uploadFile) {
        long begin = System.currentTimeMillis();
        Map<Object, Object> map = new HashMap<>(10);

        int count = 0;
        for (MultipartFile file : uploadFile) {
            Object result = nginxService.uploadVideo(file);
            map.put(count, result);
            count++;
        }
        long end = System.currentTimeMillis();
        log.info("任务结束，共耗时：[" + (end-begin) + "]毫秒");
        return map;
    }

    @DeleteMapping("/delete/{id}")
    public R<String> videoDelete(@PathVariable("id") Integer id){
        Video video = videoService.getById(id);
        if(video != null){
            videoService.removeById(video.getId());
            FtpUtil.delVideo(video.getLocation().substring(video.getLocation().lastIndexOf("/") - 9));
            return success("删除成功");
        }

        return fail("删除失败");
    }

    @GetMapping("/listVideoByCourse/{courseId}")
    public List<Video> listVideoByCourse(@PathVariable("courseId") Integer courseId){
        return videoService.listVideoByCourse(courseId);
    }

    @GetMapping("/getOneForFeign/{videoId}")
    public Video getOneForFeign(@PathVariable("videoId") Integer videoId){
         return videoService.getOneForFeign(videoId);
    }
}
