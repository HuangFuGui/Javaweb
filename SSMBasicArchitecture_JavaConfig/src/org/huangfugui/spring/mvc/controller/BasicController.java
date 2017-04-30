package org.huangfugui.spring.mvc.controller;

import org.huangfugui.dto.Result;
import org.huangfugui.spring.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Result<String> registerHandler(HttpServletRequest request, Model model){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean result = basicService.registerUser(username,password);
        if(result==true){
            return new Result(200,"register successfully!");
        }
        else{
            return new Result(200,"register failed!");
        }
    }
}
