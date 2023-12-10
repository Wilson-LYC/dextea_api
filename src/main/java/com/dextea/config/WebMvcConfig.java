package com.dextea.config;

import com.dextea.interceptor.LoginInterceptor;
import com.dextea.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 权限拦截器配置
 */
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
                "/login/**","/druid","/ws","/test/**"
        };
        //注册拦截器
        registry.addInterceptor(new LoginInterceptor(loginService))
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathPatterns);
    }
}
