package com.zhu.api_user.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (t_user)实体类
 *
 * @author zhu
 * @since 2022-04-12 23:06:07
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_user")
public class User extends Model<User> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
    @TableField(update = "now()")
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
