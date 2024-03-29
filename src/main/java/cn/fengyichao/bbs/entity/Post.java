package cn.fengyichao.bbs.entity;

import lombok.Data;

/**
 * @author fengyichao
 * @date 2019/11/26 - 19:54
 */
@Data
public class Post {

    private Integer id;
    private String title;
    private String content;
    private Integer author;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private Long createTime;
    private Long modifiedTime;
}
