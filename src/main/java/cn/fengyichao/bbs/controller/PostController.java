package cn.fengyichao.bbs.controller;

import cn.fengyichao.bbs.entity.Comment;
import cn.fengyichao.bbs.entity.Post;
import cn.fengyichao.bbs.entity.User;
import cn.fengyichao.bbs.service.CommentService;
import cn.fengyichao.bbs.service.PostService;
import cn.fengyichao.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

/**
 * @author fengyichao
 * @date 2019/11/28 - 16:16
 */
@Controller
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable(name = "id") Integer id, Model model){
        Post post = postService.getPostById(id);
        User user = userService.getUserById(post.getAuthor());
        model.addAttribute("post",post);
        model.addAttribute("author",user);

        postService.incrViewCount(id);  //更新帖子浏览数

        List<Comment> comments = commentService.getCommentsByPostId(id);

        List<User> users = userService.getAllUser();

        HashMap<Integer,User> userMap = new HashMap<>();

        for(User u : users){
            userMap.put(user.getId(),u);
        }

        model.addAttribute("userMap",userMap);
        model.addAttribute("comments",comments);

        return "post";
    }
}
