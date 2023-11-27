package com.dextea.config;

import com.dextea.interceptor.MyInterceptor;
import com.dextea.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    LoginService loginService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截的路径
        String[] addPathPatterns = {
                "/**"
        };
        //排除的路径
        String[] excludePathPatterns = {
                "/login/*","/druid"
        };
        //注册拦截器
        registry.addInterceptor(new MyInterceptor(loginService))
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathPatterns);
    }
}
