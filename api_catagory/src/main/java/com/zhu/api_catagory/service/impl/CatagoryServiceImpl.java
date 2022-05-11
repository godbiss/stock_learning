package com.zhu.api_catagory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.api_catagory.entity.Catagory;
import com.zhu.api_catagory.mapper.CatagoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.zhu.api_catagory.service.CatagoryService;
import org.springframework.stereotype.Service;

/**
 * 服务接口实现
 *
 * @author zhu
 * @since 2022-04-27 16:53:21
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CatagoryServiceImpl extends ServiceImpl<CatagoryMapper, Catagory> implements CatagoryService {
    private final CatagoryMapper catagoryMapper;
}
