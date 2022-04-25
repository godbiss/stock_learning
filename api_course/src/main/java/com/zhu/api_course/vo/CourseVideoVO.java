package com.zhu.api_course.vo;

import com.zhu.api_course.entity.Course;
import com.zhu.api_course.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CourseVideoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Course course;

    /**
     * 此课程的video列表
     */
    private List<Video> videoList;
}
