package com.zhu.api_catagory.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (t_catagory)实体类
 *
 * @author zhu
 * @since 2022-04-27 16:53:21
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_catagory")
public class Catagory extends Model<Catagory> implements Serializable {
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
     * createTime
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * updateTime
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

}
