package com.zhu.api_video.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.api_video.entity.Video;
import com.zhu.api_video.mapper.VideoMapper;
import com.zhu.api_video.service.VideoService;
import com.zhu.api_video.util.JedisCRUD;
import com.zhu.api_video.util.JedisUtil;
import com.zhu.api_video.util.QueryStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

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

    private Jedis jedis;

    public List<Video> listVideoByCourse(Integer courseId){
        jedis = JedisUtil.getJedis();
        String videoListStr = jedis.hget("video_list_course", String.valueOf(courseId));
        if(StringUtil.isEmpty(videoListStr)){
            QueryWrapper<Video> wrapper = new QueryWrapper<>();
            wrapper.eq("course_id", courseId);
            List<Video> videoList = this.list(wrapper);
            jedis.hset("video_list_course", String.valueOf(courseId), JSON.toJSONString(videoList));
            jedis.expire("video_list_course", 10);
            jedis.close();
            return videoList;
        }
        return JSONObject.parseArray(videoListStr, Video.class);
    }

    public Video getOneForFeign(Integer videoId){
        Video video = JedisCRUD.doSomethingHashOne("video_list", videoId, "id",
                new QueryStrategy<Integer, Video>() {

                    @Override
                    public List<Video> doSomething(String column, Integer fieldKey) {
                        ArrayList<Video> videos = new ArrayList<>();
                        videos.add(videoMapper.selectById(fieldKey));
                        return videos;
                    }
                }, Video.class);
        return video;
    }
}
