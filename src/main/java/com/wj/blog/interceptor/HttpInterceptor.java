package com.wj.blog.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wj
 * @descript
 * @date 2022/8/2 - 14:37
 */
public class HttpInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 允许跨域，所有的Ip请求
        response.setHeader("Access-Control-Allow-Origin","*");
        // 请求头的允许方式
        response.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers","token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
