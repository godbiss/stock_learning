package com.zhu.api_course.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 账号
     */
    private String account;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 头像
     */
    private String avator;
    /**
     * phoneNumber
     */
    private Integer phoneNumber;
    /**
     * 钱
     */
    private BigDecimal money;
    /**
     * 上次登录时间
     */
    private Date lastLoginTime;
    /**
     * 账号是否可用。默认为1（可用）
     */
    private Integer enabled;
    /**
     * 是否过期。默认为1（没有过期）
     */
    private Integer notExpired;
    /**
     * 账号是否锁定。默认为1（没有锁定）
     */
    private Integer accountNotLocked;
    /**
     * 证书（密码）是否过期。默认为1（没有过期）
     */
    private Integer credentialsNotExpired;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 创建人
     */
    private Integer createUser;
    /**
     * updateUser
     */
    private Integer updateUser;

}
