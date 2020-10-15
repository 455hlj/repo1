package com.clic.controller;

import com.clic.domain.Admin;
import com.clic.domain.CloudMessage;
import com.clic.service.Impl.AdminServiceImpl;
import com.clic.service.Impl.CloudMessageServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * <h3>CloudMessagePush</h3>
 * <p>用户操作控制器</p>
 *
 * @author : John Fallen
 * @date : 2020-09-16 14:46
 **/


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @Autowired
    private CloudMessageServiceImpl cloudMessageServiceImpl;

    private static  String FEEDBACK_SUCCESS="success";
    private static  String FEEDBACK_MESSAGE="msg";

    @RequestMapping({"tologin","/"})
    public String toLogin(){
        System.out.println("转发");
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String> result = new HashMap<String,String>();
        Admin admin = adminServiceImpl.login(username,password);
        if(admin==null) {
            result.put("success", "0");
            result.put("msg","您输入账号或密码错误");
        }
        else{
            request.getSession().setAttribute("admin",admin);
            result.put("success", "1");
            //result.put("msg","您输入账号或密码错误");

        }
        return result;

    }
    public void sendErrorMsg(Object responseObject ,HttpServletResponse response)  {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String JsonMsg = new Gson().toJson(responseObject);
        System.out.println(JsonMsg);
        //试试可以可以直接写map
        try {
            response.getWriter().write(JsonMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("/toForm")
    public String toForm()
    {
        return "form";
    }

    @RequestMapping("/pushOneMessage")
    @ResponseBody
    public Map pushOneMessage(CloudMessage cloudMessage){
        Map<String,String> map = new HashMap<String,String>();

        if(cloudMessageServiceImpl.addOneRecord(cloudMessage)<1||cloudMessage==null){
            map.put(FEEDBACK_SUCCESS,"0");
            map.put(FEEDBACK_MESSAGE,"发送失败，请稍后重试");
        }
        else
        {
            map.put(FEEDBACK_SUCCESS,"1");
            map.put(FEEDBACK_MESSAGE,"发送成功");
        }

        return map;
    }
    @RequestMapping("/pushBatchMessage")
    @ResponseBody
    public Map pushBatchMessage(String status){
        Map<String,String> map = new HashMap<String,String>();
        //如果status是0表示清除list集合，什么都不做
        if("0".equals(status)){
            CloudMessage.setMessageBatch(null);
            map.put("clear","1");
        }
        else
        {
            int i = cloudMessageServiceImpl.addBatchRecord(CloudMessage.getMessageBatch());
            if(i<1){
                map.put("clear","0");
                map.put(FEEDBACK_SUCCESS,"0");
                map.put(FEEDBACK_MESSAGE,"发送失败");
            }
            else {
                map.put("clear", "0");
                map.put(FEEDBACK_SUCCESS, "1");
                map.put(FEEDBACK_MESSAGE, "成功发送"+i+"条信息");
            }
        }
        return map;
    }
}
