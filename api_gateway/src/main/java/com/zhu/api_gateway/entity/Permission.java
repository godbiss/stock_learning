package com.zhu.api_gateway.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 权限code
     */
    private String permissionCode;
    /**
     * 权限名
     */
    private String permissionName;
}
