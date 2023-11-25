package com.dextea.mapper;

import com.dextea.pojo.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    //新增店铺
    int add(Store store);
    //删除店铺
    int deleteStoreById(int id);
    //获取所有店铺
    List<Store> getAllStore();
    //查找店铺
    Store getStoreById(int id);
    //搜索店铺
    List<Store> searchStore(Store store);
    //修改店铺信息
    int updateStore(Store store);
    //统计营业区域门店数量
    int countStoreByArea(String area);
}
