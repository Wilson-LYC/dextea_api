package com.dextea.mapper;

import com.dextea.pojo.Setting;
import com.dextea.pojo.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SettingMapper {
    Setting get(String key);

    int update(Setting setting);
}
