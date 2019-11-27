package cn.fengyichao.bbs.mapper;

import cn.fengyichao.bbs.entity.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
