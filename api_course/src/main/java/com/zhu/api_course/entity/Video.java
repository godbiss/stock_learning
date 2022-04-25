package com.zhu.api_course.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
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
