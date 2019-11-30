package cn.fengyichao.bbs.mapper;

import cn.fengyichao.bbs.entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author fengyichao
 * @date 2019/11/26 - 19:53
 */
@Mapper
public interface PostMapper {

    @Insert("insert into post (title,content,author,comment_count,view_count,like_count,tag,create_time,modified_time)" +
            " values(#{title},#{content},#{author},#{commentCount},#{viewCount},#{likeCount},#{tag},#{createTime},#{modifiedTime})")
    void addPost(Post post);

    @Select("select id,title,content,author,comment_count,view_count,like_count,tag,create_time,modified_time from post")
    List<Post> getAllPost();

    @Select("select id,title,content,author,comment_count,view_count,like_count,tag,create_time,modified_time from post limit #{offset},#{size}")
    List<Post> getPostsByPage(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(*) from post")
    Long getPostTotal();

    @Select("select id,title,content,author,comment_count,view_count,like_count,tag,create_time,modified_time from post where author = #{author} limit #{offset},#{size}")
    List<Post> getPostsByAuthorAndPage(@Param(value = "author") Integer author,@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(*) from post where author = #{author}")
    Long getPostTotalByAuthor(Integer author);

    @Select("select id,title,content,author,comment_count,view_count,like_count,tag,create_time,modified_time from post where id = #{id}")
    Post getPostById(Integer id);

    @Select("update post set title=#{title},content=#{content},tag=#{tag},modified_time=#{modifiedTime} where id = #{id}")
    void updatePost(Post post);

    @Update("update post set view_count = view_count + 1 where id = #{id}")
    void incrViewCount(Integer id);

    @Update("update post set comment_count = comment_count + 1 where id = #{id}")
    void incrCommentCount(Integer id);
}
