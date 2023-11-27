package com.dextea.mapper;

import com.dextea.pojo.BlackIp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlackIpMapper {
    int add(BlackIp blackIp);
    List<BlackIp> getByIp(String ip);
}
