package com.dextea.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig{
    // 配置数据源，将数据源配置属性绑定到 DruidDataSource Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    // 配置 Druid 监控的 Servlet
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServlet() {
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean =
                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        // 设置监控页面的配置参数
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1"); // 允许访问的 IP
        servletRegistrationBean.addInitParameter("deny", "127.0.0.2"); // 拒绝访问的 IP
        servletRegistrationBean.addInitParameter("loginUsername", "admin"); // 登录用户名
        servletRegistrationBean.addInitParameter("loginPassword", "123456"); // 登录密码
        servletRegistrationBean.addInitParameter("resetEnable", "false"); // 是否允许重置数据

        return servletRegistrationBean;
    }

    // 配置 Druid 过滤器
    @Bean
    public FilterRegistrationBean<WebStatFilter> statFilter() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean =
                new FilterRegistrationBean<>(new WebStatFilter());

        // 配置过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        return filterRegistrationBean;
    }
}
