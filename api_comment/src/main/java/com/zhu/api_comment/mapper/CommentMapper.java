package com.zhu.api_comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhu.api_comment.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (t_comment)数据Mapper
 *
 * @author zhu
 * @since 2022-04-16 12:02:53
 * @description 由 Mybatisplus Code Generator 创建
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    Integer insertCommtCourseRelation(@Param("commentId") Integer commentId,
                                      @Param("courseId") Integer courseId);

    Integer checkCommentCourse(@Param("commentId") Integer commentId,
                               @Param("courseId") Integer courseId);

    Integer insertCommentVideoRelation(@Param("commentId") Integer commentId,
                                      @Param("videoId") Integer videoId);

    Integer checkCommentVideo(@Param("commentId") Integer commentId,
                               @Param("videoId") Integer videoId);

    List<Comment> getCommentByCourse(Integer courseId);

    Boolean deleteCommentForCourse(Integer id);
}
