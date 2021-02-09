package com.jia.SpringBootDemo.Component;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// 过滤器
//@WebFilter(urlPatterns = "/dofilter")
 //@Component
//@Order(1)
public class MyFilter implements Filter {

    @Override
    // 在系统启动时就会执行
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init Method start...");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // doFilter 方法之前的代码在 Serlet 执行之前先执行
        System.out.println("doFilter Method start...");

        // 获取 name 值
        String name = servletRequest.getParameter("name");
        System.out.println("name=====" + name);

        if (name != null && name.equals("赌博")) {
            // 直接返回
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

        // doFilter 方法之后的代码在 Serlet 执行之前先执行
        System.out.println("doFilter Method end...");
    }

    @Override
    public void destroy() {
        System.out.println("destory Method start...");
    }
}
