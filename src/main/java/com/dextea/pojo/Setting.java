package com.dextea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Setting
{
    private int id;
    private String key;
    private String value;
    private String creatTime;
    private String updateTime;
}
