package com.zhu.api_comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.api_comment.entity.Comment;
import com.zhu.api_comment.entity.Course;
import com.zhu.api_comment.entity.Video;
import com.zhu.api_comment.feign.CourseService;
import com.zhu.api_comment.feign.VideoService;
import com.zhu.api_comment.mapper.CommentMapper;
import com.zhu.api_comment.util.JedisCRUD;
import com.zhu.api_comment.util.JedisUtil;
import com.zhu.api_comment.util.QueryStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.zhu.api_comment.service.CommentService;
import net.dreamlu.mica.core.utils.$;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 服务接口实现
 *
 * @author zhu
 * @since 2022-04-16 12:02:53
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    private final CommentMapper commentMapper;
    @Autowired
    private CourseService courseService;
    @Autowired
    private VideoService videoService;
    private Jedis jedis;

    public Integer insertCommentVideoRelation(Integer commentId,
                                       Integer videoId){
        return commentMapper.insertCommentVideoRelation(commentId, videoId);
    }

    public Integer checkCommentVideo(Integer commentId,
                              Integer videoId){
        return commentMapper.checkCommentVideo(commentId, videoId);
    }

    public Integer insertCommtCourseRelation(Integer commentId,
                                      Integer courseId){
        return commentMapper.insertCommtCourseRelation(commentId, courseId);
    }

    /**
     * 检查课程和评论关系是否已经存在
     * @param commentId
     * @param courseId
     * @return
     */
    public Integer checkCommentCourse(Integer commentId,
                               Integer courseId){
        return commentMapper.checkCommentCourse(commentId, courseId);
    }

    public Integer commentForComment(Comment comment){
        Integer id = JedisCRUD.insertOrUpdate("comment_list", comment, new QueryStrategy<Comment, Integer>() {

            @Override
            public List<Integer> doSomething(String column, Comment fieldKey) {
                ArrayList<Integer> list = new ArrayList<>();
                if (fieldKey.getId() == null) {
                    commentMapper.insert(comment);
                } else
                    commentMapper.updateById(comment);
                list.add(fieldKey.getId());
                return list;
            }
        });
        return id;
    }

    @Transactional
    public Boolean commentForCourse(Comment comment, Integer courseId){
        try {
            JedisCRUD.insertOrUpdate("comment_list_course", comment, new QueryStrategy<Comment, Integer>() {
                @Override
                public List<Integer> doSomething(String column, Comment fieldKey) {
                    ArrayList<Integer> list = new ArrayList<>();
                    if (fieldKey.getId() == null) {
                        commentMapper.insert(comment);
                    } else
                        commentMapper.updateById(comment);
                    // 判断课程是否存在
                    Course oneForFeign = courseService.getOneForFeign(courseId);
                    System.out.println(oneForFeign);
                    if($.isEmpty(oneForFeign)) list.add(0);
                    else {
                        // 防止关系重复插入
                        if($.isEmpty(commentMapper.checkCommentCourse(comment.getId(), courseId)))
                            commentMapper.insertCommtCourseRelation(comment.getId(), courseId);
                    }
                    list.add(courseId);
                    return list;
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Transactional
    public Boolean commentForVideo(Comment comment, Integer videoId){
        try {
            JedisCRUD.insertOrUpdate("comment_list_video", comment, new QueryStrategy<Comment, Integer>() {
                @Override
                public List<Integer> doSomething(String column, Comment fieldKey) {
                    ArrayList<Integer> list = new ArrayList<>();
                    if (fieldKey.getId() == null) {
                        commentMapper.insert(comment);
                    } else
                        commentMapper.updateById(comment);
                    // 判断视频是否存在
                    Video oneForFeign = videoService.getOneForFeign(videoId);
                    System.out.println(oneForFeign);
                    if($.isEmpty(oneForFeign)) list.add(0);
                    else {
                        // 防止关系重复插入
                        if($.isEmpty(commentMapper.checkCommentVideo(comment.getId(), videoId)))
                            commentMapper.insertCommentVideoRelation(comment.getId(), videoId);
                    }
                    list.add(videoId);
                    return list;
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Comment> getCommentByCourse(Integer courseId){
        List<Comment> commentList = JedisCRUD.doSomethingHash("comment_list_course", courseId, null,
                new QueryStrategy<Integer, Comment>() {
                    @Override
                    public List<Comment> doSomething(String column, Integer fieldKey) {
                        return commentMapper.getCommentByCourse(fieldKey);
                    }
                }, Comment.class);
        return commentList;
    }

    public List<Comment> getCommentByFid(Integer fid){
        List<Comment> commentList = JedisCRUD.doSomethingHash("comment_list_fid", fid, null,
                new QueryStrategy<Integer, Comment>() {
                    @Override
                    public List<Comment> doSomething(String column, Integer fieldKey) {
                        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
                        wrapper.eq("fid", fieldKey);
                        return list(wrapper);
                    }
                }, Comment.class);
        return commentList;
    }

    public Comment getCommentById(Integer id){
        Comment comment = JedisCRUD.doSomethingHashOne("comment_list", id, "id", (column, fieldKey) -> {
            ArrayList<Comment> comments = new ArrayList<>();
            comments.add(getById(id));
            return comments;
        }, Comment.class);
        return comment;
    }

    @Transactional
    public Boolean deleteCommentById(Integer id) {
        jedis = JedisUtil.getJedis();

        int flag = commentMapper.deleteById(id);

        // 删除子评论
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("fid", id);
        remove(wrapper);

        if(flag > 0) {
            commentMapper.deleteCommentForCourse(id);
            jedis.del("comment_list_fid", "comment_list_course",
                    "comment_list_video", "comment_list_news", "comment_list");
        }
        return flag > 0;
    }
}
