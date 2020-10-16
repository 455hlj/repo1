package com.clic.filter;

import com.clic.domain.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    private static String ADMIN = "admin";
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        String url = request.getRequestURL().toString();
        try {
            if (url.indexOf("login") > 0) {
                chain.doFilter(request, response);
            }
            else if (session.getAttribute(ADMIN) == null) {
                System.out.println("没有登录，去登录");

                //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理 否则直接重定向就可以了
                if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
                    //告诉ajax我是重定向
                    response.setHeader("REDIRECT", "REDIRECT");
                    //告诉ajax我重定向的路径
                    response.setHeader("CONTENTPATH", request.getContextPath() + "/admin/tologin");
                    //403  ：资源拒绝访问
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }else{
                    response.sendRedirect(request.getContextPath() + "/admin/tologin");
                }
            }
            else {
                chain.doFilter(request, response);
            }
        }catch (IllegalStateException e)
        {
            response.sendRedirect(request.getContextPath() + "/admin/tologin");
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
