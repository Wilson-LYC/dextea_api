package com.dextea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    private int id;//商品id
    private String name;//商品名称
    private String introduce;//商品介绍
    private String briefIntro;//商品简介
    private String custom;//商品个性化定制选项
    private String img;//商品图片
    private String state;//商品状态 0不可售 1可售
    private String createtime;//创建时间
    private String updatetime;//更新时间
    private String category;//商品所属品类
    private double price;//商品价格
}
