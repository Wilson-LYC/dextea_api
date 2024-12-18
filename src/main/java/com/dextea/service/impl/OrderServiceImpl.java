package com.dextea.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.util.AudioUntil;
import com.dextea.mapper.CustomerMapper;
import com.dextea.mapper.OrderMapper;
import com.dextea.mapper.StoreMapper;
import com.dextea.pojo.Order;
import com.dextea.server.AudioServiceServer;
import com.dextea.server.StoreServiceServer;
import com.dextea.service.OrderService;
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
     * 转为json
     * @param order 订单
     * @return JSONObject
     */
    @Override
    public JSONObject toJson(Order order) {
        JSONObject orderJson= JSONObject.from(order);
        JSONArray commodity= JSONArray.parseArray(order.getCommodity());
        orderJson.put("commodity",commodity);
        return orderJson;
    }

    /**
     * 列表转为json
     * @param orderList 订单列表
     * @return JSONArray
     */
    @Override
    public JSONArray toJson(List<Order> orderList) {
        JSONArray orderArray=new JSONArray();
        for(Order order:orderList){
            JSONObject orderJson=toJson(order);
            orderArray.add(orderJson);
        }
        return orderArray;
    }

    /**
     * 添加订单v1
     * @param json 订单
     * @return JSONObject
     */
    @Override
    public JSONObject addOrderV1(JSONObject json) {
        JSONObject res=new JSONObject();
        Order order=new Order();
        order.setStoreId(json.getInteger("sid"));
        order.setCustId(json.getInteger("cid"));
        order.setPrice(json.getDouble("price"));
        order.setNum(json.getInteger("num"));
        JSONArray commodityArray=json.getJSONArray("commodity");
        order.setCommodity(commodityArray.toJSONString());
        order.setPhone(json.getString("phone"));
        order.setNote(json.getString("note"));
        //获取取餐码
        String code=getOrderCode(order.getStoreId());
        order.setCode(code);
        order.setState("1");
        try{
            orderMapper.addOrder(order);
            //发送订单给门店
            try{
                JSONObject orderList=getTodayOrderV1(order.getStoreId()).getJSONObject("data").getJSONObject("order");
                JSONObject sendData=new JSONObject();
                sendData.put("type","order");
                sendData.put("content",orderList);
                StoreServiceServer.sendToStoreBySid(String.valueOf(order.getStoreId()),sendData);
                res.put("code",200);
                res.put("msg","添加成功");
            }catch (Exception e){
                res.put("code",500);
                res.put("msg","服务错误");
                return res;
            }
        }
        catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库连接错误");
            return res;
        }
        return res;
    }

    /**
     * 获取所有订单v1
     * @return JSONObject
     */
    @Override
    public JSONObject getAllOrderV1() {
        JSONObject res=new JSONObject();
        List<Order> list=null;
        try{
            list=orderMapper.getAllOrder();
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库连接错误");
        }
        JSONArray orderArray=toJson(list);
        res.put("code",200);
        res.put("msg","获取成功");
        JSONObject data=new JSONObject();
        data.put("order",orderArray);
        res.put("data",data);
        return res;
    }

    /**
     * 修改订单v1
     * @param json 订单
     * @return JSONObject
     */
    @Override
    public JSONObject updateOrderV1(JSONObject json) {
        JSONObject res=new JSONObject();
        Order order=new Order();
        order.setId(json.getInteger("id"));
        order.setPhone(json.getString("phone"));
        order.setState(json.getString("state"));
        order.setNote(json.getString("note"));
        try{
            orderMapper.updateOrder(order);
            res.put("code",200);
            res.put("msg","修改成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库连接错误");
        }
        return res;
    }

    /**
     * 搜索订单v1
     * @param json 订单
     * @return JSONObject
     */
    @Override
    public JSONObject searchOrderV1(JSONObject json) {
        JSONObject res=new JSONObject();
        int id=json.getInteger("id")==null?0:json.getInteger("id");
        int storeId=json.getInteger("storeId")==null?0:json.getInteger("storeId");
        String custName=json.getString("custName")==null?"":json.getString("custName");
        String code=json.getString("code")==null?"":json.getString("code");
        String state=json.getString("state")==null?"":json.getString("state");
        String phone=json.getString("phone")==null?"":json.getString("phone");
        JSONArray time=json.getJSONArray("time")==null?new JSONArray():json.getJSONArray("time");
        String begintime="";
        String endtime="";
        if (time.size()>0){
            begintime=time.getString(0)+" 00:00:00";
            endtime=time.getString(1)+" 23:59:59";
        }
        try{
            List<Order> list=orderMapper.search(id,storeId,custName,code,state,phone,begintime,endtime);
            JSONArray orderArray=toJson(list);
            res.put("code",200);
            res.put("msg","获取成功");
            JSONObject data=new JSONObject();
            data.put("order",orderArray);
            res.put("data",data);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库连接错误");
            res.put("error",e.getMessage());
        }
        return res;
    }

    /**
     * 获取今日门店订单v1
     * @param id 店铺id
     * @return JSONObject
     */
    @Override
    public JSONObject getTodayOrderV1(int id) {
        JSONObject res=new JSONObject();
        JSONObject data=new JSONObject();
        JSONObject order=new JSONObject();
        String begintime=DateUtil.today()+" 00:00:00";
        String endtime=DateUtil.today()+" 23:59:59";
        for(int state=0;state<=4;state++){
            int mode=state==4?1:0;//下单时间 0正序 1倒序
            try{
                List<Order> list=orderMapper.getTodayOrder(id,state,begintime,endtime,mode);//下单时间 0正序 1倒序
                JSONArray orderArray=toJson(list);
                order.put("s"+state,orderArray);
            } catch (Exception e) {
                res.put("code", 500);
                res.put("msg", "数据库错误");
                return res;
            }
        }
        data.put("order",order);
        res.put("code",200);
        res.put("msg","获取成功");
        res.put("data",data);
        return res;
    }

    /**
     * 获取单笔订单详情v1
     * @param id 订单id
     * @return JSONObject
     */
    @Override
    public JSONObject getOrderDetailV1(int id) {
        JSONObject res=new JSONObject();
        try{
            Order order=orderMapper.getOrderDetail(id);
            if(order==null){
                res.put("code",500);
                res.put("msg","订单不存在");
                return res;
            }
            JSONObject orderJson=toJson(order);
            res.put("code",200);
            res.put("msg","获取成功");
            JSONObject data=new JSONObject();
            data.put("order",orderJson);
            res.put("data",data);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库错误");
            return res;
        }
        return res;
    }

    /**
     * 更新制作进度v1
     * @param sid 店铺id
     * @param oid 订单id
     * @param state 订单状态
     * @return JSONObject
     */
    @Override
    public JSONObject updateOrderStateV1(int sid,int oid,String state) {
        JSONObject res=new JSONObject();
        try{
            orderMapper.updateState(oid,state);
            res.put("code",200);
            res.put("msg","更新成功");
            //发送新订单
            try{
                JSONObject order=this.getTodayOrderV1(sid).getJSONObject("data").getJSONObject("order");
                JSONObject sendData=new JSONObject();
                sendData.put("type","order");
                sendData.put("content",order);
                StoreServiceServer.sendToStoreBySid(String.valueOf(sid),sendData);
                Order order1=orderMapper.getOrderDetail(oid);
                if(state.equals("3")){
                    this.sendAudioV1(sid,order1.getCode());
                }
            }catch (Exception e){
                res.put("code",500);
                res.put("msg","服务错误");
                return res;
            }
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库错误");
        }
        return res;
    }

    /**
     * 发送音频v1
     * @param sid 店铺id
     * @param code 取餐码
     * @return JSONObject
     */
    @Override
    public JSONObject sendAudioV1(int sid, String code) {
        JSONObject res=new JSONObject();
        String base64Audio = null;
        try {
            base64Audio = AudioUntil.createAudio(code);
        } catch (Exception e) {
            res.put("code",500);
            res.put("msg","服务错误");
            res.put("error",e.getMessage());
        }
        JSONObject sendData=new JSONObject();
        sendData.put("type","audio");
        JSONObject base64Json=new JSONObject();
        base64Json.put("base64",base64Audio);
        base64Json.put("code",code);
        sendData.put("content",base64Json);
        AudioServiceServer.sendToStoreBySid(String.valueOf(sid),sendData);
        res.put("code",200);
        res.put("msg","发送成功");
        return res;
    }

    /**
     * 获取排队情况v1
     * @param id 店铺id
     * @return JSONObject
     */
    @Override
    public JSONObject getStoreQueueV1(int id) {
        JSONObject res=new JSONObject();
        JSONObject data=new JSONObject();
        data.put("queue",orderMapper.getStoreQueue(id));
        res.put("code",200);
        res.put("msg","获取成功");
        res.put("data",data);
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
     * 获取顾客的订单列表V1
     * @param id 用户id
     * @return JSONObject
     */
    @Override
    public JSONObject getCustOrderListV1(int id) {
        JSONObject res=new JSONObject();
        List<Order> list=orderMapper.getCustOrderList(id);
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
    public JSONObject getOrderDetailForCustV1(int id) {
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
     * 获取门店订单v1
     * @param id 店铺id
     * @return JSONObject
     */
    @Override
    public JSONObject getStoreOrderV1(int id) {
        JSONObject res=new JSONObject();
        try{
            List<Order> list=orderMapper.getStoreOrderById(id);
            JSONArray orderArray=toJson(list);
            res.put("code",200);
            res.put("msg","获取成功");
            JSONObject data=new JSONObject();
            data.put("order",orderArray);
            res.put("data",data);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","数据库错误");
            return res;
        }
        return res;
    }
}
