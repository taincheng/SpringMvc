package com.mvc.controllers;

import com.mvc.Pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@RequestMapping("/springmvc")
@Controller
public class TestRequestMapping {

    private static final String SUCCESS = "success";

    /**
     * 1.@RequestMapping 除了修饰方法，还可以来修饰类
     * 1.类定义处，提供初步的请求映射信息，相对于 WEB 应用的根目录
     * 2.方法处，根据进一步的细分映射信息，若方法标注了该注解，类也标注了，则是层级关系。
     *
     * @return
     */
    @RequestMapping("/testRequestMapping")
    public String testRequestMapping() {
        System.out.println("testRequestMapping");
        return SUCCESS;
    }

    /**
     * 使用method属性来指定请求方式
     */
    @RequestMapping(value = "/testMethod", method = RequestMethod.POST)
    public String testMethod() {
        System.out.println("testMethod");
        return SUCCESS;
    }

    /**
     * 使用params 和 header
     * 可以使用简单的表达式来进行一个条件的访问要求。
     */
    @RequestMapping(value = "/testParamsAndHeader",
            params = {"username", "age!=10"}, headers = {"Accept-Language=en-US,zh;1=0.8"})
    public String testParamsAndHeader() {
        return SUCCESS;
    }


    /**
     * 支持Ant风格的3种通配符
     * 1. ？：匹配文件名中的一个字符
     * 2. * ：匹配文件名中的任意字符
     * 3. ** ：匹配多层路径
     *
     * @return
     */
    @RequestMapping("/testAnt/*/ant")
    public String testAnt() {
        System.out.println("testAnt");
        return SUCCESS;
    }

    /**
     * @param id
     * @return
     * @PathVariable 可以来映射URL中的占位符到目标方法的参数中。
     */
    @RequestMapping("/testPathVaribale/{id}")
    public String testPathVaribale(@PathVariable("id") Integer id) {
        System.out.println("testPathVaribale :" + id);
        return SUCCESS;
    }


    /**
     * Rest 风格的URL
     * 以CRUB 为例：
     * 新增： /order POST
     * 修改： /order/1 PUT
     * 获取： /order/1 GET
     * 删除： /order/1 DELETE
     * <p>
     * 如何发送 PUT 请求和 DETELE 请求嗯？
     * 1.需要配置HiddenHttpMethodFilter
     * 2.需要发送POST请求
     * 3.在POST请求时携带一个name="_method" 的隐藏域，value为 DELETE PUT
     * <p>
     * 在SpringMvc 的目标方法中如何得到id呢？
     * 使用@PathVariable 注解
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
    public String testRestGet(@PathVariable Integer id) {
        System.out.println("testRest GET : " + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest", method = RequestMethod.POST)
    public String testRestPost() {
        System.out.println("testRest POST");
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable Integer id) {
        System.out.println("testRest DELETE : " + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
    public String testRestPut(@PathVariable Integer id) {
        System.out.println("testRest PUT : " + id);
        return SUCCESS;
    }

    /**
     * @param un
     * @param pw
     * @return
     * @RequestMapping 来映射请求参数
     * value 值即请求参数的参数名。
     * required 该参数是否必须， 默认为true
     * defaultValue 请求参数的默认值
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(value = "username") String un,
                                   @RequestParam(value = "password", required = false) String pw) {
        System.out.println("testRequestParam, username : " + un +
                "password : " + pw);
        return SUCCESS;
    }


    /**
     * 了解：
     * 映射请求头信息
     * 用法同 @RequestParam
     *
     * @param a1
     * @return
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept-Language") String a1) {
        System.out.println("testRequestHeader, Accept-language " + a1);
        return SUCCESS;
    }

    /**
     * 了解：
     *
     * @param sessionID
     * @return
     * @CookieValue: 映射一个Cookie值，属性同@RequestParam
     */
    @RequestMapping("/testCookiesValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionID) {
        System.out.println("testCookieValue: sessionID: " + sessionID);
        return SUCCESS;
    }

    /**
     * SpringMVC 会按请求参数名和Pojo属性名进行自动匹配，
     * 自动为该对象填充属性值，支持级联属性。
     *
     * @param user
     * @return
     */
    @RequestMapping("/testPojo")
    public String testPojo(User user) {
        System.out.println("testPojo : " + user);
        return SUCCESS;
    }


    /**
     * 可以使用Serlvet 原生的API作为目标方法的参数 具体支持一下类型
     *
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     * java.security.Principal
     * Locale
     * InputStream
     * OutputStream
     * Reader
     * Writer
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request,
                                 HttpServletResponse response) {
        System.out.println("testServletAPI， " + request + ", " + response);
        return SUCCESS;
    }


    /**
     * 目标方法的返回值可以是ModelAndView 类型
     * 其中可以包含视图和模型信息
     * SpringMVC 会把ModerAndView 的 model 中数据放入到request 域对象中。
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        String viewName = SUCCESS;
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("test",new Date());

        return modelAndView;
    }

    /**
     * 目标方法可以添加Map 类型（实际上也可以是 model类型或 modelMap类型）参数。
     *
     * @param map
     * @return
     */
    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map){
        System.out.println(map.getClass().getName());
        map.put("names", Arrays.asList("tom","jack","wuli"));
        return SUCCESS;
    }

}
