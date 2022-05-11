package com.zhu.api_news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.api_news.entity.News;
import com.zhu.api_news.mapper.NewsMapper;
import com.zhu.api_news.util.JedisCRUD;
import com.zhu.api_news.util.JedisUtil;
import com.zhu.api_news.util.QueryStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.zhu.api_news.service.NewsService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务接口实现
 *
 * @author zhu
 * @since 2022-05-01 15:55:45
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {
    private final NewsMapper newsMapper;
    private Jedis jedis;

    public Integer insertOrUpdate(News news){
        Integer id = JedisCRUD.insertOrUpdate("news_list", news, new QueryStrategy<News, Integer>() {
            @Override
            public List<Integer> doSomething(String column, News fieldKey) {
                ArrayList<Integer> list = new ArrayList<>();
                if (fieldKey.getId() == null)
                    newsMapper.insert(fieldKey);
                else
                    newsMapper.updateById(fieldKey);
                list.add(news.getId());
                return list;
            }
        });
        return id;
    }

    public List<News> getNewsByTitle(String title) {
        List<News> news = JedisCRUD.queryList("news_list_title", title, "title", (column, fieldKey) -> {
            QueryWrapper<News> wrapper = new QueryWrapper<>();
            wrapper.like(column, fieldKey);
            return list(wrapper);
        }, News.class);
        return news;
    }

    public List<News> getNewsByUser(Integer userId) {
        List<News> news = JedisCRUD.queryList("news_list_user", userId, "create_user", (column, fieldKey) -> {
            QueryWrapper<News> wrapper = new QueryWrapper<>();
            wrapper.eq(column, fieldKey);
            return list(wrapper);
        }, News.class);
        return news;
    }

    public Boolean deleteNewsById(Integer id) {
        jedis = JedisUtil.getJedis();

        int flag = newsMapper.deleteById(id);

        if (flag > 0) {
            jedis.del("news_list_title", "news_list", "news_list_user");
            jedis.close();
        }
        return flag > 0;
    }

    public List<News> listAll(){
        List<News> list = JedisCRUD.getAllHash("news_list", (column, fieldKey) -> list(), News.class);
        return list;
    }
}
