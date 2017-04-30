package org.huangfugui.spring.service;

import org.huangfugui.dto.Result;
import org.huangfugui.ibatis.mapper.UserMapper;
import org.huangfugui.ibatis.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangfugui on 2017/4/28.
 */
@Component
public class BasicService {

    @Autowired
    private UserMapper userMapper;

    public Result registerUser(String username, String password){
        int result = userMapper.insertUser(username,password);
        if(result==1){
            //注册成功
            return new Result(1,"注册成功");
        }
        //已有用户名，注册失败
        return new Result(0,"用户名已存在，注册失败");
    }

    public Result login(String username, String password, HttpServletRequest request){
        User user = userMapper.selectByUsernameAndPassword(username,password);
        if(user!=null){
            //登录记录session
            HttpSession session = request.getSession();
            session.setAttribute("userSession",user);
            //向客户端返回user对象数据
            List<User> list = new ArrayList<User>();
            list.add(user);
            return new Result(1,"登陆成功",list);//数据库中有相应用户名与密码
        }
        return new Result(0,"用户名或密码错误");
    }
}
