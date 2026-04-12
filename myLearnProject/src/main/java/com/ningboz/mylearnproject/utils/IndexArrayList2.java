package com.ningboz.mylearnproject.utils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class IndexArrayList2<T> extends ArrayList<T> implements List<T> {
    private Map<String,Map<String,List<T>>> indexMap;

    public IndexArrayList2() {
        this.indexMap = new HashMap<>();
    }
}
