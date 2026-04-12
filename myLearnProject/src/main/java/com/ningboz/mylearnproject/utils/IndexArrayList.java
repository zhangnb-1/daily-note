package com.ningboz.mylearnproject.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexArrayList<T> extends ArrayList<T> {
    private Map<String,Map<String,List<T>>> indexMap;
    private Class<T> clazz;

    public IndexArrayList(Class<T> clazz) {
        this.clazz = clazz;
        this.indexMap = new HashMap<>();
    }

    public List<T> get(String paramName, String paramValue){
        if(!indexMap.containsKey(paramName)){
            createIndex(paramName);
        }

        return indexMap.get(paramName).get(paramValue);
    }

    private void createIndex(String paramName) {
        Method method = null;
        try {
            method = clazz.getMethod(getGetterMethodName(paramName));
            if(method==null)
                return;
            Map<String, List<T>> index = new HashMap<>();
            indexMap.put(paramName,index);

            for (T t : this) {
                Object val = method.invoke(t);
                if(val==null)
                    continue;
                String valStr = val.toString();
                if(!index.containsKey(valStr))
                    index.put(valStr,new ArrayList<>());
                List<T> list = index.get(valStr);
                list.add(t);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    private String getGetterMethodName(String paramName){
        return "get" + paramName.substring(0, 1).toUpperCase() + paramName.substring(1);
    }

    @Override
    public T set(int index, T element) {
        return super.set(index, element);
    }
}
