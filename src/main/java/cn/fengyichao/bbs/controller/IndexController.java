package cn.fengyichao.bbs.controller;

import cn.fengyichao.bbs.entity.Post;
import cn.fengyichao.bbs.entity.User;
import cn.fengyichao.bbs.mapper.PostMapper;
import cn.fengyichao.bbs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model){

        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.getUserByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("loginUser",user);
                    }
                    break;

                }
            }
        }

        List<User> users = userMapper.getAllUser();
        HashMap<Integer,User> userMap = new HashMap<Integer,User>();  //存放userId和user对应关系
        for(User user: users){
            userMap.put(user.getId(),user);
        }

        List<Post> posts = postMapper.getAllPost();
        model.addAttribute("posts",posts);
        model.addAttribute("userMap",userMap);

        return "index";
    }
    
}