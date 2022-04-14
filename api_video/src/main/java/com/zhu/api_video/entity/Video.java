package com.zhu.api_video.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (t_video)实体类
 *
 * @author zhu
 * @since 2022-04-13 01:08:57
 * @description 由 Mybatisplus Code Generator 创建
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_video")
public class Video extends Model<Video> implements Serializable {
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
     * location
     */
    private String location;
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

    public Video(String name, String location, Date createtime, Date updatetime) {
        this.name = name;
        this.location = location;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Video(String name, String location, Date createtime, Date updatetime, Integer courseId) {
        this.name = name;
        this.location = location;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.courseId = courseId;
    }
}
