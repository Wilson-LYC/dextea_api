package com.dextea.mapper;

import com.dextea.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int addOrder(Order order);

    List<Order> getOrderList(int id);

    Order getOrderDetail(int id);

    int getOrderQueue(int id,String time);

    int getStoreQueue(int id);

    List<Order> getSameState(int sid, String state);

    int updateState(int id, String state);
}
