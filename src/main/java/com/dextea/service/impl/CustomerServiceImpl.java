package com.dextea.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.mapper.CustomerMapper;
import com.dextea.pojo.Customer;
import com.dextea.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    /**
     * 转换customer为json
     * @param customer customer
     * @return json
     */
    @Override
    public JSONObject toJson(Customer customer) {
        JSONObject res= JSONObject.from(customer);
        res.remove("openid");
        return res;
    }

    /**
     * 转换customerList为json
     * @param customerList customerList
     * @return json
     */
    @Override
    public JSONArray toJson(List<Customer> customerList) {
        JSONArray res=new JSONArray();
        for(Customer customer:customerList){
            res.add(toJson(customer));
        }
        return res;
    }

    /**
     * 获取所有顾客v1
     * @return 所有顾客
     */
    @Override
    public JSONObject getAllCustomerV1() {
        JSONObject res=new JSONObject();
        try{
            List<Customer> customers=customerMapper.getAllCustomer();
            res.put("code",200);
            res.put("msg","成功");
            JSONObject data=new JSONObject();
            data.put("customers",toJson(customers));
            res.put("data",data);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","服务器错误");
        }
        return res;
    }

    /**
     * 更新顾客信息v1
     * @param data data
     * @return 更新结果
     */
    @Override
    public JSONObject updateCustomerV1(JSONObject data) {
        JSONObject res=new JSONObject();
        Customer customer=new Customer();
        customer.setId(data.getInteger("id"));
        customer.setName(data.getString("name"));
        customer.setPhone(data.getString("phone"));
        try{
            customerMapper.update(customer);
            res.put("code",200);
            res.put("msg","成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","服务器错误");
        }
        return res;
    }

    /**
     * 搜索顾客v1
     * @param json data
     * @return 搜索结果
     */
    @Override
    public JSONObject searchCustomerV1(JSONObject json) {
        JSONObject res=new JSONObject();
        int id=json.getInteger("id")==null?0:json.getInteger("id");
        String name=json.getString("name")==null?"":json.getString("name");
        String phone=json.getString("phone")==null?"":json.getString("phone");
        try{
            List<Customer> customers=customerMapper.search(id,name,phone);
            JSONObject data=new JSONObject();
            data.put("customers",toJson(customers));
            res.put("code",200);
            res.put("msg","成功");
            res.put("data",data);
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","服务器错误");
            res.put("error",e.getMessage());
        }
        return res;
    }

    /**
     * 删除顾客v1
     * @param id id
     * @return 删除结果
     */
    @Override
    public JSONObject deleteCustomerV1(int id) {
        JSONObject res=new JSONObject();
        try{
            customerMapper.delete(id);
            res.put("code",200);
            res.put("msg","成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","服务器错误");
        }
        return res;
    }
}
