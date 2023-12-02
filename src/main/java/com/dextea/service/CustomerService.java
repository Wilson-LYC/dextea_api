package com.dextea.service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dextea.pojo.Customer;

import java.util.List;

public interface CustomerService {
    //customer转json
    JSONObject toJson(Customer customer);
    //customerList转json
    JSONArray toJson(List<Customer> customerList);
    //获取所有顾客
    JSONObject getAllCustomerV1();
    //更新顾客信息
    JSONObject updateCustomerV1(JSONObject data);
    //搜索顾客
    JSONObject searchCustomerV1(JSONObject data);
    //删除顾客
    JSONObject deleteCustomerV1(int id);
}
