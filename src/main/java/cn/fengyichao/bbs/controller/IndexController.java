package cn.fengyichao.bbs.controller;

import cn.fengyichao.bbs.dto.Page;
import cn.fengyichao.bbs.entity.Post;
import cn.fengyichao.bbs.entity.User;
import cn.fengyichao.bbs.service.PostService;
import cn.fengyichao.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController{

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name="pageNum",defaultValue = "1") Integer pageNum,
                        @RequestParam(name="pageSize",defaultValue = "8") Integer pageSize){

        List<User> users = userService.getAllUser();
        HashMap<Integer,User> userMap = new HashMap<Integer,User>();  //存放userId和user对应关系
        for(User user: users){
            userMap.put(user.getId(),user);
        }

        Page<Post> page = postService.getPostsByPage(pageNum, pageSize);
        model.addAttribute("posts",page.getList());
        model.addAttribute("userMap",userMap);
        model.addAttribute("pageCount",page.getPageCount());
        model.addAttribute("total",page.getTotal());
        model.addAttribute("pageNum",page.getPageNum());
        return "index";
    }
    
}