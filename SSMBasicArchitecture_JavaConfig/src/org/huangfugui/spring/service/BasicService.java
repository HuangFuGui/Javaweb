package org.huangfugui.spring.service;

import org.huangfugui.dto.Result;
import org.huangfugui.ibatis.enums.UserType;
import org.huangfugui.ibatis.mapper.UserMapper;
import org.huangfugui.ibatis.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangfugui on 2017/4/28.
 */
@Component
public class BasicService {

    @Autowired
    private UserMapper userMapper;

    public Result registerUser(String username, String password,String userType){
        UserType userType1;
        if(userType.equals("0")){
            userType1 = UserType.USER;
        }
        else{
            userType1 = UserType.ADMINISTRATOR;
        }
        int result = userMapper.insertUser(username,password,userType1);
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

    public Result updateNickname(String nickname,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        int result = userMapper.updateNicknameById(user.getId(),nickname);
        if(result==1){
            return new Result(1,"更新昵称成功");
        }
        else{
            return new Result(0,"更新昵称失败");
        }
    }

    public Result updateSign(String sign,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        int result = userMapper.updateSignById(user.getId(),sign);
        if(result==1){
            return new Result(1,"更新签名成功");
        }
        else{
            return new Result(0,"更新签名失败");
        }
    }

    public Result upload(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println(multipartRequest.getParameter("uploadText"));
        List<MultipartFile> multipartFileList = multipartRequest.getFiles("uploadFiles");

        //String savePath = session.getServletContext().getRealPath("/") + "pic/"+user.getId();
        String savePath = "/mnt/sdc/static_picture_cognition/"+user.getId();
        File directory = new File(savePath);
        if(!directory.exists()){
            directory.mkdirs();
        }

        try {
            for(MultipartFile multipartFile:multipartFileList){
                String fileName = System.currentTimeMillis()+multipartFile.getOriginalFilename();
                multipartFile.transferTo(new File(savePath+"/"+fileName));
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(0,"上传失败");
        }
        return new Result(1,"上传成功");
    }
}
