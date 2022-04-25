package com.zhu.api_user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhu.api_user.entity.User;
import com.zhu.api_user.service.UserService;
import com.zhu.api_user.service.impl.UserServiceImpl;
import com.zhu.api_user.util.JedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.core.result.R;
import net.dreamlu.mica.core.utils.StringUtil;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

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
@RequestMapping("/user")
public class UserController implements IController {
    private final UserServiceImpl userService;

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        return userService.getUserById(id);
    }

    @PostMapping("/updateUser")
    public Boolean updateUserMoney(@RequestBody User user){
        return userService.updateUserMoney(user);
    }


}
