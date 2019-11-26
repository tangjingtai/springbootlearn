package com.jt.springbootlearn.controller;

import com.jt.springbootlearn.bean.Person;
import com.jt.springbootlearn.exception.UserNotExistException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class HelloController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        if (username.equals("admin") && password.equals("123456")) {
            session.setAttribute("loginUser", username);
            return "redirect:/dashboard";
        }
        map.put("msg", "账号密码错误");
        return "/login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "user", required = false) String user) {
        if (user != null && user.equals("made")) {
            throw new UserNotExistException();
        }
        return "Hello world";
    }

    @GetMapping("/user/{id}")
    public Person person(@PathVariable("id") Long id){
        if(id == 1L){
            Person person = new Person();
            person.setLastName("一号用户");
            person.setAge(29);
            person.setBoss(true);
            return person;
        }
        throw new UserNotExistException();
    }

    private void a() throws Exception {
        throw new Exception();
    }

    @Value("${person.last-name}")
    private String name;

    @ResponseBody
    @RequestMapping("/sayHello")
    public String sayHelo() {
        return "hello " + name;
    }


    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        map.put("name", "john");
        map.put("age", 29);
        map.put("content", "<h1>这是内容</h1>");
        return "success";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String admin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        return "当前登录用户：" + userDetails.getUsername();
    }
}
