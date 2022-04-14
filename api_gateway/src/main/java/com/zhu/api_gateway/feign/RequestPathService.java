package com.zhu.api_gateway.feign;

import com.zhu.api_gateway.entity.RequestPath;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("api-user")
public interface RequestPathService {

    @GetMapping("/requestPath/selectAll")
    public List<RequestPath> selectAll();

    @GetMapping("/requestPath/selectByPermission/{permissionId}")
    List<RequestPath> selectByPermission(@PathVariable("permissionId") Integer permissionId);
}
