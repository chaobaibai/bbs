package cn.fengyichao.bbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fengyichao
 * @date 2019/11/30 - 15:08
 */
@Data
@AllArgsConstructor
public class Result {
    private Integer code;
    private String message;

    public static Result ok(){
        return new Result(200,"请求成功");
    }

}
