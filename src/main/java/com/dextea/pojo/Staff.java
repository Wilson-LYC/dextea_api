package com.dextea.pojo;

import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Staff {
    private int id;
    private String account;
    private String password;
    private int role;//0：超级管理员 1：公司 2：门店
    private int storeId;
    private String auth;
    private String createtime;
    private String updatetime;
}
