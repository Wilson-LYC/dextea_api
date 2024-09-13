package com.dextea.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.service.LoginService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private LoginService loginService;
    public LoginInterceptor(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        //访问次数限制
        /*String ip=request.getRemoteAddr();
        int isAccess=loginService.isAccess(ip);
        if(isAccess>0){
            JSONObject jsonObject=new JSONObject();
            if(isAccess==2){
                //添加黑名单
                jsonObject.put("code",000);
                jsonObject.put("msg","IP访问受限，请联系IT部门");
            }else{
                //拒绝访问
                jsonObject.put("code",999);
                jsonObject.put("msg","访问过于频繁，请稍后再试");
            }
            String json=jsonObject.toJSONString();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(json);
            return false;
        }*/
        //判断是否登录
        //获取token
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
            return false;
        }
        //判断token是否有效
        boolean isLogin=loginService.isLogin(token);
        if(!isLogin){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",300);
            jsonObject.put("msg","登录失效，请重新登录");
            String json=jsonObject.toJSONString();
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(json);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
