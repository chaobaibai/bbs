package cn.fengyichao.bbs.entity;

import lombok.Data;

/**
 * @author fengyichao
 * @date 2019/11/26 - 16:40
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long createTime;
    private Long modifiedTime;
    private String bio;
    private String imgUrl;

}
