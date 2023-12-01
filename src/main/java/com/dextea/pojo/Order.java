package com.dextea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;//订单ID
    private int custId;//顾客ID
    private int storeId;//店铺ID
    private String state;//订单状态
    private double price;//订单总价
    private int num;//订单商品数量
    private String createtime;//订单创建时间
    private String phone;//顾客电话
    private String note;//备注
    private String commodity;//商品
    private String code;//取餐码
}
