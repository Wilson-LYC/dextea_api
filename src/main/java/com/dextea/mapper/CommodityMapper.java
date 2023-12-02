package com.dextea.mapper;

import com.dextea.pojo.Commodity;

import java.util.List;

public interface CommodityMapper {
    //获取所有商品
    List<Commodity> getAllCommodity();
    List<Commodity> getAllCommFull();
    //搜索商品
    List<Commodity> searchComm(int id,String name,String state,int cateId);


    //获取所有商品（略）
    List<Commodity> getAllCommBrief();



    int addCommodity(Commodity commodity);

    Commodity getCommById(int id);

    int updateCommodity(Commodity commodity);



    int deleteComm(int id);

    List<Commodity> getCommByCateId(int cateId);

    List<Commodity> getStoreMenu(int id);

    int onsale(int cid, int sid);

    int offsale(int cid, int sid);

    List<Commodity> getMenuByCateId(int sid, int cateId);


}
