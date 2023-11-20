package com.dextea.mapper;

import com.dextea.pojo.Setting;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SettingMapper {
    Setting get(String key);

    int update(Setting setting);
}
