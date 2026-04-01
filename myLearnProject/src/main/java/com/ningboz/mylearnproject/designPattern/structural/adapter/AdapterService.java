package com.ningboz.mylearnproject.designPattern.structural.adapter;

public interface AdapterService<S,T> {
    T adapter(S source);
}
