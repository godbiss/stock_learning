package com.zhu.api_comment.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (t_comment)实体类
 *
 * @author zhu
 * @since 2022-04-16 12:02:53
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_comment")
public class Comment extends Model<Comment> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Integer id;
    /**
     * content
     */
    private String content;
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
     * 父评论id
     */
    private Integer fid;

}
