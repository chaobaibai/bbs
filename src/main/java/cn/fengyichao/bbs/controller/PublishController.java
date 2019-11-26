package cn.fengyichao.bbs.controller;

import cn.fengyichao.bbs.entity.Post;
import cn.fengyichao.bbs.entity.User;
import cn.fengyichao.bbs.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author fengyichao
 * @date 2019/11/26 - 18:50
 */
@Controller
public class PublishController {

    @Autowired
    private PostMapper postMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title, @RequestParam("content") String content,
                            @RequestParam("tag") String tag, HttpSession session){
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setTag(tag);
        post.setAuthor(((User)session.getAttribute("loginUser")).getId());
        post.setCommentCount(0);
        post.setLikeCount(0);
        post.setViewCount(0);
        post.setCreateTime(System.currentTimeMillis());
        post.setModifiedTime(post.getCreateTime());
        postMapper.addPost(post);

        return "redirect:/";
    }
}
