package com.jia.SpringBootDemo.Component;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/dofilter"})
@Component
public class Myfilter3 implements Filter {

    @Override
    // 在系统启动时就会执行
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init3 Method start...");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // doFilter 方法之前的代码在 Serlet 执行之前先执行
        System.out.println("doFilter3 Method start...");

        filterChain.doFilter(servletRequest, servletResponse);

        // doFilter 方法之后的代码在 Serlet 执行之前先执行
        System.out.println("doFilter3 Method end...");
    }



    @Override
    public void destroy() {
        System.out.println("destory Method start...");
    }
}
