package cn.fengyichao.bbs.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fengyichao
 * @date 2019/11/30 - 9:10
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BbsErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String errorHtml(HttpServletRequest request, Model model){
        HttpStatus httpStatus = getStatus(request);
        if(httpStatus.is4xxClientError()){
            model.addAttribute("message","您的请求出错了哟");
        }
        if(httpStatus.is5xxServerError()){
            model.addAttribute("message","服务正忙，请稍后再试");
        }
        return "error";
    }

    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
