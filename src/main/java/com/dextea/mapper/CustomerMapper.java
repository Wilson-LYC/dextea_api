package com.dextea.mapper;

import com.dextea.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;

import javax.el.MethodInfo;
import java.util.List;

@Mapper
public interface CustomerMapper {
    //根据openid查询顾客
    List<Customer> getByOpenid(String openid);

    int add(Customer customer);

    Customer getCustomerById(int custId);
    //获取所有顾客
    List<Customer> getAllCustomer();
    //更新顾客信息
    int update(Customer customer);
    //搜索顾客
    List<Customer> search(int id, String name, String phone);
    //删除顾客
    int  delete(int id);
}
