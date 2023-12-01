package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.CustomerMapper;
import com.dextea.mapper.OrderMapper;
import com.dextea.mapper.StoreMapper;
import com.dextea.pojo.Order;
import com.dextea.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    StoreMapper storeMapper;
    @Autowired
    CustomerMapper customerMapper;
    private ReentrantLock lock = new ReentrantLock();
    /**
     * 添加订单
     * @param order 订单
     * @return JSONObject
     */
    @Override
    public JSONObject addOrder(Order order) {
        JSONObject res=new JSONObject();
        //获取取餐码
        String code=getOrderCode(order.getStoreId());
        order.setCode(code);
        int flag=orderMapper.addOrder(order);
        if(flag==1){
            res.put("code",200);
            res.put("msg","添加成功");
        }else{
            res.put("code",500);
            res.put("msg","添加失败");
        }
        return res;
    }

    /**
     * 获取取餐码
     * @param sid 店铺id
     * @return String
     */
    @Override
    public String getOrderCode(int sid) {
        lock.lock();
        String key="store:"+sid;
        int code=0;
        //如果key不存在，设置key的值为1，过期时间为24小时
        if(!redisTemplate.hasKey(key)){
            redisTemplate.opsForValue().set(key,1);
            redisTemplate.expire(key,86400, TimeUnit.SECONDS);//过期时间为24小时
            code=1;
        }else{
            //如果key存在，自增1
            redisTemplate.opsForValue().increment(key,1);
            code = (int) redisTemplate.opsForValue().get(key);
        }
        //对code进行处理，保证code为3位数循环
        code=8000+(code % 1000);
        lock.unlock();
        return String.valueOf(code);
    }

    /**
     * 获取订单列表
     * @param id 用户id
     * @return JSONObject
     */
    @Override
    public JSONObject getOrderList(int id) {
        JSONObject res=new JSONObject();
        List<Order> list=orderMapper.getOrderList(id);
        JSONArray orderArray=new JSONArray();
        for(Order order:list){
            JSONObject orderJson=new JSONObject();
            orderJson.put("id",order.getId());
            int sid=order.getStoreId();
            orderJson.put("store",storeMapper.getStoreById(sid).getName());
            orderJson.put("price",order.getPrice());
            orderJson.put("num",order.getNum());
            orderJson.put("state",order.getState());
            orderJson.put("type","堂食");
            orderJson.put("time",order.getCreatetime());
            String commodityStr=order.getCommodity();
            JSONArray commodityArray=JSONArray.parseArray(commodityStr);
            orderJson.put("commodity",commodityArray);
            orderJson.put("code",order.getCode());
            orderArray.add(orderJson);
        }
        res.put("code",200);
        res.put("msg","获取成功");
        JSONObject data=new JSONObject();
        data.put("order",orderArray);
        res.put("data",data);
        return res;
    }

    /**
     * 获取订单详情
     * @param id 订单id
     * @return JSONObject
     */
    @Override
    public JSONObject getOrderDetailForCust(int id) {
        JSONObject res=new JSONObject();
        Order order=orderMapper.getOrderDetail(id);
        JSONObject orderJson=new JSONObject();
        orderJson.put("id",order.getId());
        //store
        JSONObject storeJson=new JSONObject();
        storeJson.put("id",order.getStoreId());
        storeJson.put("name",storeMapper.getStoreById(order.getStoreId()).getName());
        String state=order.getState();
        orderJson.put("state",state);
        //如果订单状态为1或2，获取排队人数
        if(state.equals("1")||state.equals("2")){
            //获取排队人数
            int queue=orderMapper.getOrderQueue(order.getStoreId(),order.getCreatetime())-order.getNum();
            storeJson.put("queue",queue);
        }
        orderJson.put("store",storeJson);
        orderJson.put("price",order.getPrice());
        orderJson.put("num",order.getNum());
        orderJson.put("commodity",JSONArray.parseArray(order.getCommodity()));
        orderJson.put("code",order.getCode());
        String time=order.getCreatetime();
        orderJson.put("time",time);
        orderJson.put("note",order.getNote());
        orderJson.put("phone",order.getPhone());

        res.put("code",200);
        res.put("msg","获取成功");
        JSONObject data=new JSONObject();
        data.put("order",orderJson);
        res.put("data",data);
        return res;
    }

    /**
     * 获取排队情况
     * @param id 店铺id
     * @return JSONObject
     */
    @Override
    public JSONObject getStoreQueue(int id) {
        JSONObject res=new JSONObject();
        JSONObject data=new JSONObject();
        data.put("queue",orderMapper.getStoreQueue(id));
        res.put("code",200);
        res.put("msg","获取成功");
        res.put("data",data);
        return res;
    }

    /**
     * 获取指定门店指定状态的订单
     * @param sid 店铺id
     * @param state 订单状态
     * @return JSONObject
     */
    @Override
    public JSONObject getSameState(int sid, String state) {
        JSONObject res=new JSONObject();
        List<Order> list=orderMapper.getSameState(sid,state);
        JSONArray orderArray=new JSONArray();
        for(Order order:list){
            JSONObject orderJson=new JSONObject();
            orderJson.put("id",order.getId());
            orderJson.put("num",order.getNum());
            orderJson.put("time",order.getCreatetime());
            orderJson.put("code",order.getCode());
            orderArray.add(orderJson);
        }
        res.put("code",200);
        res.put("msg","获取成功");
        JSONObject data=new JSONObject();
        data.put("order",orderArray);
        res.put("data",data);
        return res;
    }

    /**
     * 更新订单状态
     * @param id 订单id
     * @param state 订单状态
     * @return JSONObject
     */
    @Override
    public JSONObject updateState(int id, String state) {
        JSONObject res=new JSONObject();
        int flag=orderMapper.updateState(id,state);
        if(flag==1){
            res.put("code",200);
            res.put("msg","更新成功");
        }else{
            res.put("code",500);
            res.put("msg","更新失败");
        }
        return res;
    }
    /**
     * 获取订单详情
     */
    @Override
    public JSONObject getOrderDetail(int id) {
        JSONObject res=new JSONObject();
        Order order=orderMapper.getOrderDetail(id);
        if (order==null){
            res.put("code",500);
            res.put("msg","获取失败");
            return res;
        }
        JSONObject orderJson=new JSONObject();
        orderJson.put("id",order.getId());
        orderJson.put("orderTime",order.getCreatetime());
        orderJson.put("price",order.getPrice());
        orderJson.put("num",order.getNum());
        orderJson.put("code",order.getCode());
        orderJson.put("state",order.getState());
        String custName=customerMapper.getCustomerById(order.getCustId()).getName();
        orderJson.put("custName",custName);
        orderJson.put("custPhone",order.getPhone());
        orderJson.put("note",order.getNote());
        JSONArray commodityArray=JSONArray.parseArray(order.getCommodity());
        JSONArray commodityArrat2=new JSONArray();
        for (int i=0;i<commodityArray.size();i++){
            JSONObject commodityJson=commodityArray.getJSONObject(i);
            JSONObject commodity=new JSONObject();
            commodity.put("name",commodityJson.getString("name"));
            commodity.put("num",commodityJson.getInteger("num"));
            JSONArray customArray=commodityJson.getJSONArray("custom");
            JSONArray customArray2=new JSONArray();
            for(int j=0;j<customArray.size();j++){
                JSONObject customJson=customArray.getJSONObject(j);
                String custom=customJson.getString("title")+":"+customJson.getJSONArray("option").getJSONObject(customJson.getInteger("opt")).getString("text");
                customArray2.add(custom);
            }
            commodity.put("custom",customArray2);
            commodityArrat2.add(commodity);
        }
        orderJson.put("commodity",commodityArrat2);
        res.put("code",200);
        res.put("msg","获取成功");
        JSONObject data=new JSONObject();
        data.put("order",orderJson);
        res.put("data",data);
        return res;
    }
}
