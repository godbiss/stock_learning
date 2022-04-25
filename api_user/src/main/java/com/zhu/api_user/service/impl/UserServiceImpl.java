package com.zhu.api_user.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.api_user.entity.User;
import com.zhu.api_user.mapper.UserMapper;
import com.zhu.api_user.service.UserService;
import com.zhu.api_user.util.JedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.utils.StringUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Date;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;
    private Jedis jedis = JedisUtil.getJedis();

    public User getUserById(Integer id){
        String userGetUserById = jedis.hget("user_list", String.valueOf(id));
        if(StringUtil.isEmpty(userGetUserById)){
            User user = getById(id);
            if(user != null)
                jedis.hset("user_list", String.valueOf(id), JSON.toJSONString(user));
                jedis.expire("user_list", 10);
                jedis.close();
            return user;
        }
        return JSON.parseObject(userGetUserById, User.class);
    }

    public Boolean updateUserMoney(User user){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", user.getId()).set("money", user.getMoney()).set("update_time", new Date()).set("update_user", user.getUpdateUser());
        if(update(wrapper)){
            User byId = getById(user.getId());
            jedis.hset("user_list", String.valueOf(user.getId()), JSON.toJSONString(user));
            jedis.close();
            return true;
        }
        return false;
    }
}
