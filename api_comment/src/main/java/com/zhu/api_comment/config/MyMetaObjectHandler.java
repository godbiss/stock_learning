package com.zhu.api_comment.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("insertFill");
        this.setFieldValByName("createtime", new Date(), metaObject);
        this.setFieldValByName("updatetime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("updateFill");
        this.setFieldValByName("updatetime", new Date(), metaObject);
    }
}
