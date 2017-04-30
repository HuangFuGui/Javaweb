package org.huangfugui.spring.mvc.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.huangfugui.dto.Result;
import org.huangfugui.ibatis.po.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created by huangfugui on 2017/4/30.
 */
public class SpringMVCInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute("userSession");
        if(user==null){
            //拦截器直接向客户端返回数据
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            Result result=new Result(-1,"请先登录");
            PrintWriter out=httpServletResponse.getWriter();
            out.write(new ObjectMapper().writeValueAsString(result));
            out.close();
            return false;//不会继续调用其他的拦截器或处理器
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
