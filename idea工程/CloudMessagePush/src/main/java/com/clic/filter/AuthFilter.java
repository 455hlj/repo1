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
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        String url = request.getRequestURL().toString();
        if(url.indexOf("login")>0)
        {
            chain.doFilter(request, response);
        }
        else if(session.getAttribute("admin")==null)
        {
            System.out.println("没有登录，去登录");
            response.sendRedirect(request.getContextPath()+"/admin/tologin");
            return ;
        }
       // chain.doFilter(request, response);
        else {chain.doFilter(request, response);}
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
