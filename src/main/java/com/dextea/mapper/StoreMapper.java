package com.dextea.mapper;

import com.dextea.pojo.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    List<Store> getAllStore();
    //新增店铺
    int add(Store store);
    //查找店铺
    Store getStoreById(int id);
    //修改营业状态
    int updateOpenState(Store store);
    //删除店铺
    int deleteStoreById(int id);
    //修改店铺信息
    int updateStore(Store store);
}
