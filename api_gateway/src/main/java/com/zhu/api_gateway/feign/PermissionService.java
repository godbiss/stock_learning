package com.zhu.api_gateway.feign;

import com.zhu.api_gateway.entity.Permission;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("api-user")
public interface PermissionService {

    @GetMapping("/permission/selectByUser/{userId}")
    List<Permission> selectByUser(@PathVariable("userId") Integer userId);
}
