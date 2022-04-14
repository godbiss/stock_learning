package com.zhu.api_user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhu.api_user.entity.RequestPath;

import java.util.List;

/**
 * 服务接口
 *
 * @author zhu
 * @since 2022-04-12 23:06:07
 * @description 由 Mybatisplus Code Generator 创建
 */
public interface RequestPathService extends IService<RequestPath> {
    List<RequestPath> selectByPermission(Integer permissionId);
}
