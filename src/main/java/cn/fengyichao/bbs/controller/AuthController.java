package cn.fengyichao.bbs.controller;

import cn.fengyichao.bbs.dto.AccessTokenDTO;
import cn.fengyichao.bbs.dto.GithubUser;
import cn.fengyichao.bbs.entity.User;
import cn.fengyichao.bbs.service.UserService;
import cn.fengyichao.bbs.utils.GithubUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author fengyichao
 * @date 2019/11/26 - 9:40
 */
@Controller
public class AuthController {

    @Autowired
    private GithubUtils githubUtils;

    @Autowired
    private UserService userService;

    @Value("${github.client_id}")
    private String client_id;

    @Value("${github.client_secret}")
    private String client_secret;

    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code, @RequestParam(name="state") String state, HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        String accessToken = githubUtils.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubUtils.getUser(accessToken);
        if(githubUser != null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setCreateTime(System.currentTimeMillis());
            user.setModifiedTime(user.getCreateTime());
            user.setBio(githubUser.getBio());
            user.setImgUrl(githubUser.getAvatar_url());
            userService.updateUser(user);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);

            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session,HttpServletResponse response){
        session.removeAttribute("loginUser");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
