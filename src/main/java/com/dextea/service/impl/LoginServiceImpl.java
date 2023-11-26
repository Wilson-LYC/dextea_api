package com.dextea.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.StaffMapper;
import com.dextea.pojo.Staff;
import com.dextea.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    StaffMapper staffMapper;
    /**
     * 将token和员工信息存入Redis
     * @param token token
     * @param staff 员工信息
     */
    @Override
    public void saveTokenToRedis(String token, Staff staff) {
        //设置key=Token:token
        String key="Token:"+token;
        //字段值都存入Redis
        redisTemplate.opsForHash().put(key,"role",staff.getRole());
        redisTemplate.opsForHash().put(key,"id",staff.getId());
        redisTemplate.opsForHash().put(key,"storeId",staff.getStoreId());
        //设置key的过期时间为30分钟
        redisTemplate.expire(key,1800, TimeUnit.SECONDS);
    }

    /**
     * 从Redis中获取员工信息
     * @param token token
     * @return 员工信息
     */
    @Override
    public Staff getStaffFromRedis(String token) {
        //设置key=Token:token
        String key="Token:"+token;
        //从Redis中获取员工信息
        Staff staff=new Staff();
        staff.setRole((String) redisTemplate.opsForHash().get(key,"role"));
        staff.setId((Integer) redisTemplate.opsForHash().get(key,"id"));
        return staff;
    }

    /**
     * 员工登录
     * @param account 用户名
     * @param password 密码
     * @return
     */
    @Override
    public JSONObject loginStaff(String account, String password) {
        JSONObject res=new JSONObject();
        Staff staff=staffMapper.getStaffByAccount(account);
        if(staff==null){
            res.put("code",500);
            res.put("msg","用户名不存在");
            return res;
        }
        boolean isMatch = staff.getPassword().equals(SecureUtil.md5(password));
        if(!isMatch){
            res.put("code",500);
            res.put("msg","密码错误");
            return res;
        }
        res.put("code",200);
        res.put("msg","登录成功");
        JSONObject data=new JSONObject();
        JSONObject staffJson= JSONObject.from(staff);
        staffJson.remove("password");
        staffJson.remove("auth");
        data.put("staff",staffJson);
        String token= IdUtil.simpleUUID();
        data.put("token",token);
        res.put("data",data);
        //将token和员工信息存入Redis
        saveTokenToRedis(token,staff);
        return res;
    }

    /**
     * 判断是否登录
     * @param token token
     * @return 信息
     */
    @Override
    public boolean isLogin(String token) {
        //设置key=Token:token
        String key="Token:"+token;
        //判断key是否存在
        boolean isExist=redisTemplate.hasKey(key);
        if(isExist){
            return true;
        }else{
            return false;
        }
    }
}