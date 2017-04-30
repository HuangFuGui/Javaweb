package org.huangfugui.spring;

import org.huangfugui.spring.mvc.interceptor.SpringMVCInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by huangfugui on 2017/4/28.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.huangfugui.spring.mvc.controller"})
public class WebConfig extends WebMvcConfigurerAdapter {

    /*暂不需要JSP视图解释器
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }*/

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        super.addResourceHandlers(registry);
    }*/

    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration interceptorRegistration =  registry.addInterceptor(new SpringMVCInterceptor());
        //在所有handler操作前进行拦截检查，除了注册与登录操作
        interceptorRegistration.addPathPatterns("/basic/*");
        interceptorRegistration.excludePathPatterns("/basic/register");
        interceptorRegistration.excludePathPatterns("/basic/login");
    }
}
