package com.zhu.api_user.mapper;

import com.zhu.api_user.entity.RequestPath;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (t_request_path)数据Mapper
 *
 * @author zhu
 * @since 2022-04-12 23:06:07
 * @description 由 Mybatisplus Code Generator 创建
*/
@Mapper
public interface RequestPathMapper extends BaseMapper<RequestPath> {

    List<RequestPath> selectByPermission(Integer permissionId);
}
