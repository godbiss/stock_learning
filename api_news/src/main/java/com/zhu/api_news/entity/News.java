package com.zhu.api_news.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (t_news)实体类
 *
 * @author zhu
 * @since 2022-05-01 15:55:45
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_news")
public class News extends Model<News> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Integer id;
    /**
     * title
     */
    private String title;
    /**
     * content
     */
    private String content;
    /**
     * author
     */
    private String author;
    /**
     * createtime
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createtime;
    /**
     * updatetime
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatetime;
    /**
     * createUser
     */
    private Integer createUser;
    /**
     * updateUser
     */
    private Integer updateUser;

}
