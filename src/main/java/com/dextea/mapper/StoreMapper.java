package com.dextea.mapper;

import com.dextea.pojo.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    List<Store> getAllStore();
}
