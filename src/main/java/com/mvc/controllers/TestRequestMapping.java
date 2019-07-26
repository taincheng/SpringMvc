package com.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/springmvc")
@Controller
public class TestRequestMapping {

    private static final String SUCCESS = "success";

    /**
     * 1.@RequestMapping 除了修饰方法，还可以来修饰类
     *     1.类定义处，提供初步的请求映射信息，相对于 WEB 应用的根目录
     *     2.方法处，根据进一步的细分映射信息，若方法标注了该注解，类也标注了，则是层级关系。
     * @return
     */
    @RequestMapping("/testRequestMapping")
    public String testRequestMapping(){
        System.out.println("testRequestMapping");
        return SUCCESS;
    }

    /**
     * 使用method属性来指定请求方式
     */
    @RequestMapping(value = "/testMethod", method = RequestMethod.POST)
    public String testMethod(){
        System.out.println("testMethod");
        return SUCCESS;
    }

    /**
     * 使用params 和 header
     * 可以使用简单的表达式来进行一个条件的访问要求。
     */
    @RequestMapping(value = "/testParamsAndHeader",
            params = {"username","age!=10"}, headers = {"Accept-Language=en-US,zh;1=0.8"})
    public String testParamsAndHeader(){
        return SUCCESS;
    }
}
