package cn.fengyichao.bbs.service;

import cn.fengyichao.bbs.entity.Comment;
import cn.fengyichao.bbs.entity.Post;
import cn.fengyichao.bbs.exception.CommentTypeException;
import cn.fengyichao.bbs.exception.NoParentIdException;
import cn.fengyichao.bbs.exception.NoThisCommentException;
import cn.fengyichao.bbs.exception.NoThisPostException;
import cn.fengyichao.bbs.mapper.CommentMapper;
import cn.fengyichao.bbs.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author fengyichao
 * @date 2019/11/30 - 14:23
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostMapper postMapper;


    @Transactional
    public void addComment(@RequestBody Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new NoParentIdException("没有选中任何帖子或父评论");
        }

        if(comment.getType()== null || (comment.getType() != 1 && comment.getType() != 2)){
            throw new CommentTypeException("评论类型不正确");
        }

        if(comment.getType() == 2){
            Comment parentComment = commentMapper.getCommentById(comment.getParentId());
            if(parentComment == null){                                 //回复的评论是否存在
                throw new NoThisCommentException("回复的评论不存在");
            }else{
                commentMapper.addComment(comment);
            }
        }else{
            Post post = postMapper.getPostById(comment.getParentId());
            if(post == null){                                          //评论的帖子是否存在
                throw new NoThisPostException("评论的帖子不存在");
            }
            commentMapper.addComment(comment);
            postMapper.incrCommentCount(post.getId());       //增加评论数
         }


    }

    public List<Comment> getCommentsByPostId(Integer id) {
        return commentMapper.getCommentByParentId(id,1);
    }
}
