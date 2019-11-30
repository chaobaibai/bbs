package cn.fengyichao.bbs.entity;

import lombok.Data;

/**
 * @author fengyichao
 * @date 2019/11/30 - 14:15
 */
@Data
public class Comment {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer author;
    private String content;
    private Integer likeCount;
    private Long createTime;
    private Long modifiedTime;

}
