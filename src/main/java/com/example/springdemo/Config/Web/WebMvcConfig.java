package com.example.springdemo.Config.Web;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


//@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 静态资源配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");//静态资源路径 css,js,img等
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");//视图
        registry.addResourceHandler("/mapper/**").addResourceLocations("classpath:/mapper/");//mapper.xml
        super.addResourceHandlers(registry);

    }

    /**
     * 视图控制器配置
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");//设置默认跳转视图为 /index
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);


    }
    /**
     * 视图解析器配置  控制controller String返回的页面    视图跳转控制
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        // registry.viewResolver(new InternalResourceViewResolver("/jsp/", ".jsp"));
        super.configureViewResolvers(registry);
    }

}


