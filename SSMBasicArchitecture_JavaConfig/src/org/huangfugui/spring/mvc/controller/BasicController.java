package org.huangfugui.spring.mvc.controller;

import org.huangfugui.dto.Result;
import org.huangfugui.spring.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by huangfugui on 2017/4/28.
 */
@Controller
@RequestMapping("/basic")
public class BasicController {

    @Autowired
    private BasicService basicService;

    @RequestMapping(value = "/register",
                    method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result registerHandler(HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        return basicService.registerUser(username,password,userType);
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result loginHandler(HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return basicService.login(username,password,request);
    }

    @RequestMapping(value = "/updateNickname",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result updateNicknameHandler(HttpServletRequest request, Model model){
        String nickname = request.getParameter("nickname");
        return basicService.updateNickname(nickname,request);
    }

    @RequestMapping(value = "/updateSign",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result updateSignHandler(HttpServletRequest request, Model model){
        String sign = request.getParameter("sign");
        return basicService.updateSign(sign,request);
    }

    @RequestMapping(value = "/upload",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result uploadHandler(HttpServletRequest request, Model model){
        return basicService.upload(request);
    }
}
