package com.dextea.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AudioBase64Mapper {
    void add(String code,String base64);
}
