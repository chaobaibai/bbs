package cn.fengyichao.bbs.controller;

import cn.fengyichao.bbs.dto.Result;
import cn.fengyichao.bbs.entity.Comment;
import cn.fengyichao.bbs.entity.User;
import cn.fengyichao.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author fengyichao
 * @date 2019/11/30 - 14:25
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Result comment(@RequestBody  Comment comment, HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        if(user == null){
            return new Result(401,"用户未登录无法评论");
        }
        Comment commentDb =new Comment();
        commentDb.setAuthor(user.getId());
        comment.setAuthor(1);
        commentDb.setParentId(comment.getParentId());
        commentDb.setType(comment.getType());
        commentDb.setContent(comment.getContent());

        comment.setLikeCount(0);
        comment.setCreateTime(System.currentTimeMillis());
        comment.setModifiedTime(comment.getCreateTime());
        commentService.addComment(comment);
        return Result.ok();

    }
}
