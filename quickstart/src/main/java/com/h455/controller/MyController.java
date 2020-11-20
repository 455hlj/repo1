package com.h455.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>quickstart</h3>
 * <p>上手</p>
 *
 * @author : John Fallen
 * @date : 2020-10-28 17:30
 **/
@RestController

public class MyController {

    @RequestMapping("/hello")
    public String sayHello()
    {
        return "hello world1";
    }
    @RequestMapping("/baidu")
    public void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("http://www.baidu.com").forward(request,response);
        response.sendRedirect("http://www.baidu.com");
    }
    @RequestMapping("/login")
    public void toLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("login.html");
    }
    @RequestMapping("/logout")
    public void logout(){
        System.out.println("logout");
    }
    @RequestMapping("/decrypt")
    @ResponseBody
    public Map decrypt(String code) throws IOException {
        Map map = new HashMap();
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes= decoder.decodeBuffer(code);
        String a = new String(bytes);
        map.put("a",a);
        return map ;
    }
}
