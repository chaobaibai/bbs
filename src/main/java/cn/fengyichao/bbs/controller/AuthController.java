package cn.fengyichao.bbs.controller;

import cn.fengyichao.bbs.dto.AccessTokenDTO;
import cn.fengyichao.bbs.dto.GithubUser;
import cn.fengyichao.bbs.utils.GithubUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author fengyichao
 * @date 2019/11/26 - 9:40
 */
@Controller
public class AuthController {

    @Autowired
    private GithubUtils githubUtils;

    @Value("${github.client_id}")
    private String client_id;

    @Value("${github.client_secret}")
    private String client_secret;

    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,@RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        String accessToken = githubUtils.getAccessToken(accessTokenDTO);
        GithubUser user = githubUtils.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }

}
