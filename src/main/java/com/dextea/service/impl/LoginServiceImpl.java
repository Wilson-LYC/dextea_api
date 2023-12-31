package com.dextea.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.BlackIpMapper;
import com.dextea.mapper.CustomerMapper;
import com.dextea.mapper.LoginLogMapper;
import com.dextea.mapper.StaffMapper;
import com.dextea.pojo.BlackIp;
import com.dextea.pojo.Customer;
import com.dextea.pojo.Staff;
import com.dextea.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    static int WARN_COUNT = 20;
    static int MAX_COUNT = 30;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    BlackIpMapper blackIpMapper;
    @Autowired
    StaffMapper staffMapper;
    @Autowired
    LoginLogMapper loginLogMapper;
    @Autowired
    CustomerMapper customerMapper;
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
        //设置超级管理员和公司账号的过期时间为15分钟，店铺账号为24小时。
        if(staff.getRole().equals("2"))
            redisTemplate.expire(key,86400, TimeUnit.SECONDS);
        else
            redisTemplate.expire(key, 900, TimeUnit.SECONDS);
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
     * @param json 员工信息
     * @param ip ip
     * @return
     */
    @Override
    public JSONObject loginStaff(JSONObject json,String ip) {
        JSONObject res=new JSONObject();
        String account=json.getString("account");
        String password=json.getString("password");
        Staff staff=staffMapper.getStaffByAccount(account);
        if(staff==null){
            res.put("code",500);
            res.put("msg","用户名错误");
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
        JSONObject loginData=new JSONObject();
        loginData.put("role","staff");
        loginData.put("account",account);
        loginLogMapper.add(ip,loginData.toJSONString());
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
     * 员工登出
     * @param token token
     * @return 信息
     */
    @Override
    public JSONObject logoutStaff(String token) {
        JSONObject res=new JSONObject();
        //设置key=Token:token
        String key="Token:"+token;
        //判断key是否存在
        boolean isExist=redisTemplate.hasKey(key);
        if(isExist){
            //删除key
            redisTemplate.delete(key);
            res.put("code",200);
            res.put("msg","登出成功");
        }else{
            res.put("code",500);
            res.put("msg","登出失败");
        }
        return res;
    }

    /**
     * 判断是否登录
     * @param token token
     * @return 信息
     */
    @Override
    public boolean isLogin(String token) {
        //判断是否是顾客
        if(token.startsWith("cust")){
            //是顾客,直接返回true
            return true;
        }
        //员工，判断是否在Redis中
        //设置key=Token:token
        String key="Token:"+token;
        //判断key是否存在
        boolean isExist=redisTemplate.hasKey(key);
        if(isExist){
            //设置key的过期时间为15分钟
            redisTemplate.expire(key,900, TimeUnit.SECONDS);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断IP短时间内访问次数
     * @param ip ip
     * @return 信息
     */
    @Override
    public int isAccess(String ip) {
        //判断是否在黑名单中
        List<BlackIp> blackIp=blackIpMapper.getByIp(ip);
        System.out.println(blackIp);
        if(blackIp.size()>0){
            return 2;//在黑名单
        }
        //设置key=Access:ip
        String key="IP:"+ip;
        //判断key是否存在
        boolean isExist=redisTemplate.hasKey(key);
        if(isExist){
            //获取key的值,即访问次数
            int count=(int)redisTemplate.opsForValue().get(key);
            //判断访问次数是否超过10次
            if(count>=WARN_COUNT){
//                if(count>=MAX_COUNT){
//                    //加入黑名单
//                    BlackIp newblackIp=new BlackIp();
//                    newblackIp.setIp(ip);
//                    blackIpMapper.add(newblackIp);
//                    return 2;//加入黑名单
//                }
                count++;
                redisTemplate.opsForValue().set(key,count,10,TimeUnit.SECONDS);
                return 1;//拒绝访问
            }else{
                //访问次数加1
                count++;
                redisTemplate.opsForValue().set(key,count,10,TimeUnit.SECONDS);
                return 0;//允许访问
            }
        }else{
            //设置key的值为1,有效期10s
            redisTemplate.opsForValue().set(key,1,10,TimeUnit.SECONDS);
            return 0;//允许访问
        }
    }

    /**
     * 顾客登录
     * @param code code
     * @return 信息
     */
    @Override
    public JSONObject customerLogin(String code) {
        JSONObject res=new JSONObject();
        //获取openid
        String url="https://api.weixin.qq.com/sns/jscode2session?appid=wx0d6173ea488ff2c4&secret=e1c3e65228c7655b6a9bd520217a105f&js_code="+code+"&grant_type=authorization_code";
        JSONObject loginRes= JSONObject.parseObject(HttpUtil.get(url));
        //判断是否获取成功
        if (loginRes.getString("errcode")!=null){
            res.put("code",500);
            res.put("msg",loginRes.getString("errmsg"));
            return res;
        }
        String openid=loginRes.getString("openid");
        //判断openid是否已经注册
        List<Customer> customerList=customerMapper.getByOpenid(openid);
        if(customerList.size()==0){
            //未注册，注册
            Customer customer=new Customer();
            customer.setOpenid(openid);
            customer.setName("得闲茶友");
            int flag=customerMapper.add(customer);
            if(flag==0) {
                res.put("code", 500);
                res.put("msg", "注册失败");
                return res;
            }
        }
        //登录获取信息
        JSONObject customer= JSONObject.from(customerMapper.getByOpenid(openid).get(0));
        customer.remove("openid");//移除openid
        //创建token
        String token= "cust"+IdUtil.simpleUUID();//cust开头表示顾客
        customer.put("token",token);
        res.put("code",200);
        res.put("msg","登录成功");
        JSONObject data=new JSONObject();
        data.put("customer",customer);
        res.put("data",data);
        return res;
    }
}