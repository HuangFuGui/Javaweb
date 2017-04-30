package org.huangfugui.spring.service;

import org.huangfugui.ibatis.mapper.UserMapper;
import org.huangfugui.ibatis.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by huangfugui on 2017/4/28.
 */
@Component
public class BasicService {

    @Autowired
    private UserMapper userMapper;

    public boolean registerUser(String username,String password){
        int result = userMapper.insertUser(username,password);
        if(result==1){
            return true;//注册成功
        }
        return false;//注册失败
    }

    public User login(String username,String password){
        User user = userMapper.selectByUsernameAndPassword(username,password);
        return user;
    }
}
