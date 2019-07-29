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

//@SessionAttributes(value = {"user"}, types = {String.class})
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


    /**
     * @SessionAttributes 除了可以通过指定需要放到会话中的属性外（实际上使用的是value 属性值）
     *  还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中（type 属性值）
     *
     *  注意：
     *      该注解只能放在类的上面，而不能修饰方法。
     * @param map
     * @return
     */
    @RequestMapping("/testSessionAttribute")
    public String testSessionAttribute(Map<String, Object> map){
        User user = new User("Tom","12321","tom.@qq.com","45");
        map.put("user",user);
        map.put("school","jiangxi");
        return SUCCESS;
    }


    /**
     * 运行流程：
     * 1.执行@ModelAttribute 注解修饰的方法：从数据库中取出对象，把对象放入到了Map中。键为User。
     * 2.SpringMvc从 Map 中取出User对象，并把表单的请求参数赋给该User 对象的对应属性。
     *      这样就达到了 只修改了表单中对应数据，而不影响数据库中其他数据的 目的
     * 3.SpringMVC 把上述对象传入目标方法的参数。
     *
     * 注意：
     * 在 @ModelAttribute 修饰的方法中，放到Map时的键需要和目标方法入参类型
     * 的第一个字母小写的字符串一致。
     *
     *
     * SpringMvc 确定目标方法POJO类型入参的过程
     * 1.确定一个Key：
     *     1.若目标方法的POJO类型的参数没有使用@ModelAttribute 作为修饰，则key为POJO。
     *     类名的第一个字母小写。
     *     2.若使用了@ModelAttribute 来修饰，则key 为 @ModelAttribute 注解的value属性值。
     * 2.在 implicitModel 中查找key 对应的对象，若存在，则作为入参传入。
     *      1.若在@ModelAttribute 标记的方法中再Map中保存过，且key 和 1 确定的key 一致，则会获取到。
     * 3.若 implicitModel 中不存在 key 对应的对象，则检查当前的Handler 是否使用
     *      @SessionAttributes 注解修饰，若使用了该注释，且@SessionAttributes 注解的value
     *      属性值中包含了key，则会从HttpSession 中来获取key所对应的value值，若存在则直接传入
     *      到目标方法的入参中，若不存在就抛出异常。
     * 4.若handler 没有标识@SessionAttributes 注解或 @SessionAttributes 注解的value 值中不
     *      包含key，则会通过反射来创建POJO类型的参数，传入作为目标方法的参数。
     * 5.SpringMvc 会把key 和 POJO类型的对象 保存到implicitModel中，进而保存到request中。
     *
     *
     * 源码分析的流程：
     * 1.调用 @ModelAttribute 注解修饰时方法。实际上把@ModelAttribute 方法中Map中的数据放在了implicitModel中。
     * 2。解析请求处理器的目标参数，实际上该目标参数来自于WebDataBinder 对象的target 属性。
     *      1.创建WebDateBinder 对象：
     *          1.确定objectName 属性： 若传入的attrName 属性值为 “” ，则objectName为类名第一个字母小写。
     *         注意：attrName。若目标方法的POJO属性使用了@ModelAttribute 来修饰，
     *              则attrName值即为@ModelAttribute的value属性值。
     *          2.确定target属性：
     *              >.在implicitModel 中查找attrName 对应的属性值，若存在，ok
     *              >.若不存在：则验证当前Handler 是否使用了@SessionAttributes 进行修饰，
     *                    若使用了，则尝试从Session中获取attrName所对应的属性值，若session
     *                    中没有对应的属性值，则抛出异常。
     *              >.若Handler没有使用@SessionAttributes 进行修饰，或@SessionAttributes 中没有使用value
     *                  值指定的key 和 attrName相匹配，则通过反射创建了POJO对象。
     *      2.SpringMVC 把表单的请求参数赋给了WebDataBinder 的 target 对应的属性。
     *      3.SpringMVC 会把WebDataBinder 的 attrName 和 target给到 implicitModel。
     *                  进而传到 request 域对象中。
     *      4.把WebDataBinder 的 target 作为参数传递给目标方法的入参。
     *
     *
     *
     * @param user
     * @return
     */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("abc") User user){
        //@ModelAttribute("abc") 告诉springMVC，abc指了谁。
        System.out.println("修改 : " + user);
        return SUCCESS;
    }


    /**
     * 有 @ModelAttribute 标记的方法，会在每个目标执行之前被SpringMvc 调用。
     * @param id
     * @param map
     */
    @ModelAttribute
    public void getUser(@RequestParam(value = "id",required = false) Integer id, Map<String, Object> map){
        if(id != null){
            User user = new User(1,"tom","123456","a@qq.com","12");
            map.put("abc",user);
            System.out.println("模拟从数据库中获取一个对象; " + user);
        }
    }
}
