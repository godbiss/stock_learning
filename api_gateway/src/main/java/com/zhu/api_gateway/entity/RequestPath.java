package com.zhu.api_gateway.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (t_request_path)实体类
 *
 * @author zhu
 * @since 2022-04-12 23:06:07
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class RequestPath implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private Integer id;
    /**
     * 请求路径
     */
    private String url;
    /**
     * 路径描述
     */
    private String description;

}
