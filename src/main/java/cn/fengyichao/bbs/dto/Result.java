package cn.fengyichao.bbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengyichao
 * @date 2019/11/30 - 15:08
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor()
public class Result<T> {
    private Integer code;
    private String message;

    private List<T> list = new ArrayList<>();



    public static Result ok(){
        return new Result(200,"请求成功",null);
    }

    public static Result ok(List list){
        return new Result(200,"请求成功",list);
    }

}
