package com.zhu.api_user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.api_user.entity.Permission;
import com.zhu.api_user.mapper.PermissionMapper;
import com.zhu.api_user.service.PermissionService;
import com.zhu.api_user.util.JedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 服务接口实现
 *
 * @author zhu
 * @since 2022-04-12 23:40:40
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    private final PermissionMapper permissionMapper;

    private Jedis jedis;

    public List<Permission> selectByUser(Integer userId){
        jedis = JedisUtil.getJedis();
        String permission = jedis.hget("permission_selectByUser", String.valueOf(userId));
        if(permission == null){
            List<Permission> permissions = permissionMapper.selectByUser(userId);
            jedis.hset("permission_selectByUser", String.valueOf(userId), JSON.toJSONString(permissions));
            jedis.expire("permission_selectByUser", 10);
            jedis.close();
            return permissions;
        }
        return JSONObject.parseArray(permission, Permission.class);
    }
}
