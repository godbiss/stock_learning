package com.zhu.api_user.controller;

import com.zhu.api_user.entity.User;
import com.zhu.api_user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private final UserService userService;

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        return userService.getById(id);
    }


}
