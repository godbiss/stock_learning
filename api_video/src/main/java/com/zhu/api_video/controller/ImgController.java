package com.zhu.api_video.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhu.api_video.entity.Video;
import com.zhu.api_video.service.impl.NginxService;
import com.zhu.api_video.util.FtpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.core.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/img")
public class ImgController implements IController {

    @Autowired
    NginxService nginxService;

    @PostMapping("/upload")
    public String imgUpload(@RequestParam(value = "imgFile") MultipartFile imgFile){
        long begin = System.currentTimeMillis();
        String json = "";
        try {
            Object result = nginxService.uploadImg(imgFile);
            json = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("任务结束，共耗时：[" + (end-begin) + "]毫秒");
        return json;
    }

}
