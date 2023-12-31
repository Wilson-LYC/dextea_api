package com.dextea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Store {
    private int id;
    private String name;
    private String area;
    private String address;
    private String phone;
    private String openTime;
    private String openState;//0：未开业 1：营业 2：闭店
    private String createtime;
    private String updatetime;
}
