package com.zhu.api_course.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (t_course_star)实体类
 *
 * @author zhu
 * @since 2022-04-13 12:38:20
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_course_star")
public class CourseStar extends Model<CourseStar> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
	private Integer id;
    /**
     * star
     */
    private Integer star;
    /**
     * createtime
     */
    private Date createtime;
    /**
     * updatetime
     */
    private Date updatetime;
    /**
     * courseId
     */
    private Integer courseId;
    /**
     * userId
     */
    private Integer userId;

}