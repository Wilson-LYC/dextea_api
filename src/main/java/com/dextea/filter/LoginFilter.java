package com.dextea.filter;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@CrossOrigin(origins = "*")
public class LoginFilter implements Filter {
    @Autowired
    LoginService loginService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LoginFilter启动");
    }


    @Override
    public void destroy() {
        System.out.println("LoginFilter关闭");
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
        response.setHeader("Access-Control-Allow-Headers","authorization, content-type");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        //放行/login
        String url=request.getRequestURI();
        if(url.contains("/login")||url.contains("/druid")){
            chain.doFilter(req, res);
            return;
        }
        //获取header中的token
        String token=request.getHeader("Authorization");
        //判断token是否登为空
        if(token==null || token.equals("")){
            //json返回信息
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",300);
            jsonObject.put("msg","未登录");
            String json=jsonObject.toJSONString();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(json);
            return;
        }
        //判断token是否有效
        boolean isLogin=loginService.isLogin(token);
        if(!isLogin){
            //json返回信息
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",300);
            jsonObject.put("msg","登录过期，请重新登录");
            String json=jsonObject.toJSONString();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(json);
            return;
        }
        chain.doFilter(req, res);
    }
}
