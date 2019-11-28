package cn.fengyichao.bbs.controller;

import cn.fengyichao.bbs.entity.Post;
import cn.fengyichao.bbs.entity.User;
import cn.fengyichao.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private PostService postService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }


    @PostMapping(value={"/publish/{id}","/publish"})
    public String doPublish(@PathVariable(name = "id",required = false) Integer id, @RequestParam("title") String title, @RequestParam("content") String content,
                            @RequestParam("tag") String tag, HttpSession session){
        Post post = new Post();
        if(id != null){
            post.setId(id);
            post.setTitle(title);
            post.setContent(content);
            post.setTag(tag);
            post.setModifiedTime(System.currentTimeMillis());
            postService.updatePost(post);
            return "redirect:/";
        }

        post.setTitle(title);
        post.setContent(content);
        post.setTag(tag);
        post.setAuthor(((User)session.getAttribute("loginUser")).getId());
        post.setCommentCount(0);
        post.setLikeCount(0);
        post.setViewCount(0);
        post.setCreateTime(System.currentTimeMillis());
        post.setModifiedTime(post.getCreateTime());
        postService.addPost(post);

        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String toEidtPost(@PathVariable(name = "id") Integer id, Model model){

        Post post = postService.getPostById(id);
        model.addAttribute("post",post);
        return "publish";

    }
}
