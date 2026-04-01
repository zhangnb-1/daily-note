package com.ningboz.mylearnproject.zhoulei.bean;

import lombok.Data;

import java.util.List;

@Data
public class ResultData {
    private List<OutData1> data1List;
    private List<OutData2> data2List;

    public ResultData(List<OutData1> data1List, List<OutData2> data2List) {
        this.data1List = data1List;
        this.data2List = data2List;
    }
}
