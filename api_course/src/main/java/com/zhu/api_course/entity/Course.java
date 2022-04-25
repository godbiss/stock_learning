package com.zhu.api_course.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (t_course)实体类
 *
 * @author zhu
 * @since 2022-04-13 12:38:19
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("t_course")
public class Course extends Model<Course> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Integer id;
    /**
     * name
     */
    private String name;
    /**
     * content
     */
    private String content;
    /**
     * info
     */
    private String info;
    /**
     * price
     */
    private BigDecimal price;
    /**
     * cover
     */
    private String cover;
    /**
     * createtime
     */
    private Date createtime;
    /**
     * updatetime
     */
    private Date updatetime;
    /**
     * createUser
     */
    private Integer createUser;

    /**
     * catagoryId
     */
    private Integer catagoryId;
}
