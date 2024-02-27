package com.example.commonadvice.Filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebFilter(filterName = "MyFilter",
        urlPatterns = "/*",/*通配符（*）表示对所有的web资源进行拦截*/
        initParams = {
                @WebInitParam(name = "test", value = "1")/*这里可以放一些初始化的参数*/
        })
public class TestFilter implements Filter {

    private String filterName;
    private String test;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /*初始化方法  接收一个FilterConfig类型的参数 该参数是对Filter的一些配置*/
        Filter.super.init(filterConfig);
        filterName = filterConfig.getFilterName();
        test= filterConfig.getInitParameter("test");
        System.out.println("===> TestFilter init");
        System.out.println("过滤器名称：" + filterName);
        System.out.println("测试值：" + test);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("===> chain.doFilter 后执行处理 response 的相关方法");
        // 在response header里设置一个token
        setToken(servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);// 处理请求和响应的分界线
    }

    private void setToken(ServletResponse response) {
        HttpServletResponse res = (HttpServletResponse) response;
        String token = UUID.randomUUID().toString();
        res.setHeader("Token2", token);
        System.out.println("===> 设置了token3：" + token);
    }

    @Override
    public void destroy() {
        System.out.println("===> TestFilter destroy");
        Filter.super.destroy();

    }
}
