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
}
