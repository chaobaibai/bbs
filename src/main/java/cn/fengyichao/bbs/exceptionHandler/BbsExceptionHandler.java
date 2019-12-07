package cn.fengyichao.bbs.exceptionHandler;

import cn.fengyichao.bbs.dto.Result;
import cn.fengyichao.bbs.exception.*;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fengyichao
 * @date 2019/11/30 - 8:53
 */
@ControllerAdvice
public class BbsExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Throwable exception, HttpServletRequest request, HttpServletResponse response, Model model){

        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            String message = null;
            if(exception instanceof NoParentIdException){
               message = ((NoParentIdException)exception).getMessage();
            }
            if(exception instanceof CommentTypeException){
                message = ((CommentTypeException)exception).getMessage();
            }
            if(exception instanceof NoThisCommentException){
                message = ((NoThisCommentException)exception).getMessage();
            }
            if(exception instanceof NoThisPostException){
                message = ((NoThisPostException)exception).getMessage();
            }
            returnResult(response,message);
            return null;
        }else{
            if(exception instanceof NoEntityException){
                model.addAttribute("message",((NoEntityException)exception).getMessage());
            }else{
                model.addAttribute("访问出错，请重新试试！");
            }
            ModelAndView mav = new ModelAndView();
            mav.setViewName("error");
            return mav;
        }


    }

    private void returnResult(HttpServletResponse response,String message){
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(new Result(400,message,null)));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
