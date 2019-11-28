package cn.fengyichao.bbs.controller;

import cn.fengyichao.bbs.entity.Post;
import cn.fengyichao.bbs.entity.User;
import cn.fengyichao.bbs.mapper.UserMapper;
import cn.fengyichao.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author fengyichao
 * @date 2019/11/28 - 16:16
 */
@Controller
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/post/{id}")
    public String post(@PathVariable(name = "id") Integer id, Model model){
        Post post = postService.getPostById(id);
        User user = userMapper.getUserById(post.getAuthor());
        model.addAttribute("post",post);
        model.addAttribute("author",user);
        return "post";
    }
}
