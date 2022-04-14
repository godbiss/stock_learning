package com.zhu.api_user.controller;

import com.zhu.api_user.entity.RequestPath;
import com.zhu.api_user.service.RequestPathService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.common.support.IController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/requestPath")
public class RequestPathController implements IController {
    private final RequestPathService requestPathService;

    @GetMapping("/selectAll")
    public List<RequestPath> selectAll(){
        return requestPathService.list();
    }

    @GetMapping("/selectByPermission/{permissionId}")
    public List<RequestPath> selectByPermission(@PathVariable("permissionId") Integer permissionId){
        return requestPathService.selectByPermission(permissionId);
    }
}
