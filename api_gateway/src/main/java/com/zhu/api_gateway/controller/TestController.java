package com.zhu.api_gateway.controller;

import com.zhu.api_gateway.feign.PermissionService;
import com.zhu.api_gateway.feign.RequestPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    PermissionService permissionService;

    @GetMapping("/test")
    public void test(){
        permissionService.selectByUser(2).stream().forEach(System.out::println);
    }
}
