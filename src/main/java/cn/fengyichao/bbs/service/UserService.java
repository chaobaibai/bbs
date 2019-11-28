package cn.fengyichao.bbs.service;

import cn.fengyichao.bbs.entity.User;
import cn.fengyichao.bbs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fengyichao
 * @date 2019/11/28 - 18:24
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void updateUser(User user) {
        User getedUser = userMapper.getUserByAccountId(user.getAccountId());
        if(getedUser == null){
            userMapper.addUser(user);
        }else{
            getedUser.setBio(user.getBio());
            getedUser.setImgUrl(user.getImgUrl());
            getedUser.setModifiedTime(System.currentTimeMillis());
            getedUser.setName(user.getName());
            getedUser.setToken(user.getToken());
            userMapper.updateUser(getedUser);
        }
    }
}