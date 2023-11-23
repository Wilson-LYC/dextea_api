package com.dextea.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommCate {
    private int id;
    private int cateId;
    private int commId;
    private String updatetime;
}
