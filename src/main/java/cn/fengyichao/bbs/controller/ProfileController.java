package cn.fengyichao.bbs.controller;

import cn.fengyichao.bbs.dto.Page;
import cn.fengyichao.bbs.entity.Post;
import cn.fengyichao.bbs.entity.User;
import cn.fengyichao.bbs.mapper.UserMapper;
import cn.fengyichao.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @author fengyichao
 * @date 2019/11/27 - 18:15
 */
@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostService postService;

    @GetMapping("/profile/{action}")
    public String post(@PathVariable(name="action") String action, HttpServletRequest request, Model model,
                       @RequestParam(name="pageNum",defaultValue = "1") Integer pageNum,
                       @RequestParam(name="pageSize",defaultValue = "8") Integer pageSize){

        if(action.equals("posts")){
            model.addAttribute("section","posts");
            model.addAttribute("sectionName","我的帖子");
        }else if(action.equals("replies")){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        List<User> users = userMapper.getAllUser();
        HashMap<Integer,User> userMap = new HashMap<Integer,User>();  //存放userId和user对应关系
        for(User user: users){
            userMap.put(user.getId(),user);
        }

        Page<Post> page = postService.getPostsByAuthor(((User) request.getSession().getAttribute("loginUser")).getId(),pageNum,pageSize);

        model.addAttribute("posts",page.getList());
        model.addAttribute("userMap",userMap);
        model.addAttribute("pageCount",page.getPageCount());
        model.addAttribute("total",page.getTotal());
        model.addAttribute("pageNum",page.getPageNum());

        return "profile";
    }
}
