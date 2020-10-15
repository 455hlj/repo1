package com.clic.Exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <h3>CloudMessagePush</h3>
 * <p>文件异常处理类</p>
 *
 * @author : John Fallen
 * @date : 2020-09-12 11:44
 **/
public class FileExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        if(e instanceof FileException)
        {
            e = (FileException) e ;
            httpServletRequest.setAttribute("message",e.getMessage());
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        return mv;
    }

}
