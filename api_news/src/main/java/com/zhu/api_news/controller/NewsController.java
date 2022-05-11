package com.zhu.api_news.controller;

import com.zhu.api_news.entity.News;
import com.zhu.api_news.entity.User;
import com.zhu.api_news.feign.UserService;
import com.zhu.api_news.service.impl.NewsServiceImpl;
import com.zhu.api_news.vo.NewsUserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.core.result.R;
import net.dreamlu.mica.core.result.SystemCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务控制器
 *
 * @author zhu
 * @since 2022-05-01 15:55:45
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/news")
public class NewsController implements IController {
    private final NewsServiceImpl newsService;

    @Autowired
    private UserService userService;

    @PutMapping("/insertOrUpdate")
    public R insertOrUpdate(News news) {
        try {
            Integer id = newsService.insertOrUpdate(news);
            return id > 0 ? success() : fail("插入失败") ;
        } catch (Exception e) {
            e.printStackTrace();
            return fail(e.getMessage());
        }

    }

    @GetMapping("/list")
    public R listAll(){
        try {
            List<NewsUserVO> list = new ArrayList<>();
            List<News> newsList = newsService.listAll();
            for (News news : newsList) {
                list.add(new NewsUserVO(news, userService.getUserById(news.getCreateUser())));
            }
            return success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return fail(e.getMessage());
        }
    }

    @GetMapping("/getNewsByTitle/{title}")
    public R getNewsByTitle(@PathVariable("title") String title) {
        List<NewsUserVO> list = new ArrayList<>();
        List<News> newsList = newsService.getNewsByTitle(title);
        for (News news : newsList) {
            list.add(new NewsUserVO(news, userService.getUserById(news.getCreateUser())));
        }
        return list.size() > 0 ? success(list) : fail(SystemCode.DATA_NOT_EXIST);
    }

    @GetMapping("/getNewsByUser/{userId}")
    public R getNewsByUser(@PathVariable("userId") Integer userId) {
        try {
            List<News> list = newsService.getNewsByUser(userId);
            return list.size() > 0 ? success(list) : fail(SystemCode.DATA_NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
            return fail(e.getMessage());
        }
    }

    @DeleteMapping("/deleteNewsById/{newsId}")
    public R deleteNewsById(@PathVariable("newsId") Integer newsId) {
        try {
            Boolean flag = newsService.deleteNewsById(newsId);
            return flag ? success() : fail(SystemCode.DATA_NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
            return fail(e.getMessage());
        }
    }

}
