package com.zhu.api_user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhu.api_user.entity.Permission;
import com.zhu.api_user.service.PermissionService;
import com.zhu.api_user.service.impl.PermissionServiceImpl;
import com.zhu.api_user.util.JedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.common.support.IController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.List;

/**
 * 服务控制器
 *
 * @author zhu
 * @since 2022-04-12 23:06:07
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/permission")
public class PermissionController implements IController {
    private final PermissionServiceImpl permissionService;

    @GetMapping("/selectByUser/{userId}")
    List<Permission> selectByUser(@PathVariable("userId") Integer userId){
        return permissionService.selectByUser(userId);
    }
}
