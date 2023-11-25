package com.dextea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommCate {
    private int commId;
    private String commName;
    private int cateId;
    private String cateName;
}
