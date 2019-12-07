package cn.fengyichao.bbs.mapper;

import cn.fengyichao.bbs.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author fengyichao
 * @date 2019/11/30 - 14:20
 */
@Mapper
public interface CommentMapper {

    @Select("select id,parent_id,type,author,create_time,modified_time,like_count,content from comment")
    List<Comment> getAllComment();

    @Insert("insert into comment(parent_id,type,author,create_time,modified_time,like_count,content) " +
            "values (#{parentId},#{type},#{author},#{createTime},#{modifiedTime},#{likeCount},#{content})")
    void addComment(Comment comment);

    @Select("select id,parent_id,type,author,content,create_time,modified_time,like_count from comment where id = #{id}")
    Comment getCommentById(Integer id);

    @Select("select id,parent_id,type,author,content,create_time,modified_time,like_count from comment where parent_id = #{parentId} and type = #{type}")
    List<Comment> getCommentByParentId(@Param(value = "parentId") Integer parentId,@Param(value="type") Integer type);

}
