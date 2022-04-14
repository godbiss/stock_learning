package com.zhu.api_user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.api_user.entity.RequestPath;
import com.zhu.api_user.mapper.RequestPathMapper;
import com.zhu.api_user.service.RequestPathService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class RequestPathServiceImpl extends ServiceImpl<RequestPathMapper, RequestPath> implements RequestPathService {
    private final RequestPathMapper requestPathMapper;

    public List<RequestPath> selectByPermission(Integer permissionId){
        return requestPathMapper.selectByPermission(permissionId);
    }
}
