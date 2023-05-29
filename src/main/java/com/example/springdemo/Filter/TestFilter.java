package com.example.springdemo.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebFilter(urlPatterns = { "/user/list" }) // 注册拦截器，并添加拦截路径‘/user/getOne’
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("===> TestFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);// 处理请求和响应的分界线
        System.out.println("===> chain.doFilter 后执行处理 response 的相关方法");
        // 在response header里设置一个token
        setToken(servletResponse);
    }

    private void setToken(ServletResponse response) {
        HttpServletResponse res = (HttpServletResponse) response;
        String token = UUID.randomUUID().toString();
        res.setHeader("Token", token);
        System.out.println("===> 设置了token：" + token);
    }

    @Override
    public void destroy() {
        System.out.println("===> TestFilter destroy");
        Filter.super.destroy();

    }
}
