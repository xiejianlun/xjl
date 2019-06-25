package com.xjl.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lenovo on 2019/6/12.
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = Logger.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String token = httpServletRequest.getHeader("token");

        if(!"aOXnjYZyn==".equals(token)){
            PrintWriter writer = null;
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("text/html; charset=utf-8");
            try {
                writer = httpServletResponse.getWriter();
                String error = "token信息有误";
                writer.print(error);
                return false;

            } catch (IOException e) {
                logger.error("response error",e);
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
