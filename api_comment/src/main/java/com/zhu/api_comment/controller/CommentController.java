package com.zhu.api_comment.controller;

import com.zhu.api_comment.entity.Comment;
import com.zhu.api_comment.feign.CourseService;
import com.zhu.api_comment.feign.UserService;
import com.zhu.api_comment.service.CommentService;
import com.zhu.api_comment.service.impl.CommentServiceImpl;
import com.zhu.api_comment.vo.CommentUserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.core.result.R;
import net.dreamlu.mica.core.result.SystemCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务控制器
 *
 * @author zhu
 * @since 2022-04-16 12:02:53
 * @description 由 Mybatisplus Code Generator 创建
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController implements IController {
    private final CommentServiceImpl commentService;

    @Autowired
    private UserService userService;

    @PutMapping("/insert")
    public R insertComment(Comment comment){
        try {
            Integer integer = commentService.commentForComment(comment);
            return integer > 0 ? success() : fail("插入失败");
        }catch (Exception e){
            e.printStackTrace();
            return fail(e.getMessage());
        }
    }

    @PutMapping("/insertCommentForCourse")
    public R insertCommentForCourse(Comment comment, @RequestParam("courseId") Integer courseId){
        try {
            return commentService.commentForCourse(comment, courseId) ? success() : fail("插入失败");
        }catch (Exception e){
            e.printStackTrace();
            return fail(e.getMessage());
        }
    }

    @PutMapping("/insertCommentForVideo")
    public R insertCommentForVideo(Comment comment, @RequestParam("videoId") Integer videoId){
        try {
            return commentService.commentForVideo(comment, videoId) ? success() : fail("插入失败");
        }catch (Exception e){
            e.printStackTrace();
            return fail(e.getMessage());
        }
    }

    @GetMapping("/getCommentByCourse/{courseId}")
    public R getCommentByCourse(@PathVariable("courseId") Integer courseId){
        try {
            List<CommentUserVO> commentUserVOList = new ArrayList<>();
            List<Comment> comments = commentService.getCommentByCourse(courseId);
            for (Comment comment : comments) {
                commentUserVOList.add(new CommentUserVO(comment, userService.getUserById(comment.getCreateUser())));
            }
            return success(commentUserVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return fail(e.getMessage());
        }

    }

    @GetMapping("/getCommentByFid/{fid}")
    public R getCommentByFid(@PathVariable("fid") Integer fid){
        try {
            List<CommentUserVO> commentUserVOList = new ArrayList<>();
            List<Comment> comments = commentService.getCommentByFid(fid);
            for (Comment comment : comments) {
                commentUserVOList.add(new CommentUserVO(comment, userService.getUserById(comment.getCreateUser())));
            }
            return success(commentUserVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return fail(SystemCode.DATA_NOT_EXIST);
        }
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public R deleteComment(@PathVariable("commentId") Integer commentId){
        Boolean flag = commentService.deleteCommentById(commentId);
        return flag ? success() : fail(SystemCode.DATA_NOT_EXIST);
    }
}
