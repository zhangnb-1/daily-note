package com.ningboz.mylearnproject.zhoulei.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DataAfterChange {
    private Indata indata;
    private List<Feiyong> feiyongList;
}
