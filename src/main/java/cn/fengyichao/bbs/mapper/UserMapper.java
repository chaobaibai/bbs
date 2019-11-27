package cn.fengyichao.bbs.mapper;

import cn.fengyichao.bbs.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author fengyichao
 * @date 2019/11/26 - 16:38
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user (account_id,name,token,create_time,modified_time,bio,img_url) " +
            "values(#{accountId},#{name},#{token},#{createTime},#{modifiedTime},#{bio},#{imgUrl})")
    void addUser(User user);

    @Select("select id,account_id,name,token,create_time,modified_time,bio,img_url from user where token = #{token}")
    User getUserByToken(String token);

    @Select("select id,account_id,name,token,create_time,modified_time,bio,img_url from user")
    List<User> getAllUser();
}
