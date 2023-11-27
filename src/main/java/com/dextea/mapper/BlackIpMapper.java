package com.dextea.mapper;

import com.dextea.pojo.BlackIp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlackIpMapper {
    int add(BlackIp blackIp);
    BlackIp getByIp(String ip);
}
