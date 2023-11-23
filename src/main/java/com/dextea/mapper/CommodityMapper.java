package com.dextea.mapper;

import com.dextea.pojo.Commodity;

import java.util.List;

public interface CommodityMapper {
    //获取所有商品（略）
    List<Commodity> getAllCommodityBrief();

    int addCommodity(Commodity commodity);
}
