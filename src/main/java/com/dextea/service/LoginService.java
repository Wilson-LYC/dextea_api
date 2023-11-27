package com.dextea.service;

import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Staff;

public interface LoginService {
    //将token和员工信息存入Redis
    public void saveTokenToRedis(String token, Staff staff);
    //从Redis中获取员工信息
    public Staff getStaffFromRedis(String token);
    //员工登录
    public JSONObject loginStaff(String account, String password);
    //员工登出
    public JSONObject logoutStaff(String token);
    //判断是否登录
    public boolean isLogin(String token);
    //判断IP短时间内访问次数
    public int isAccess(String ip);
}
