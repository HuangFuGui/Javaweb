package org.huangfugui.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by huangfugui on 2017/4/28.
 */
@Configuration
@ComponentScan(basePackages = {"org.huangfugui.spring.service"})
@ImportResource({"classpath:spring-mapper.xml"})
public class MyConfig {

}
