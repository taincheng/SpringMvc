package com.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan(value = "com.mvc")
public class HelloConfig {

    /**
     * 配置视图解析器：如何把handler 方法返回值解析为实际的物理视图
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        //前缀
        viewResolver.setPrefix("/WEB-INF/views/");

        //后缀
        viewResolver.setSuffix(".jsp");

        viewResolver.setViewClass(JstlView.class);

        return viewResolver;
    }

}
