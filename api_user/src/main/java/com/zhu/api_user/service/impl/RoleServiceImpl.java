package com.zhu.api_user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.api_user.entity.Role;
import com.zhu.api_user.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.zhu.api_user.service.RoleService;
import org.springframework.stereotype.Service;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    private final RoleMapper roleMapper;

}
