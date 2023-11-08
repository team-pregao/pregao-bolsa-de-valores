package com.teampregao.pregaobolsadevalores;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private static final Map<String, Object> objectMap = new HashMap<>();

    public static Object getObject(String s){
        return objectMap.get(s);
    }

    public static void putObject(String s, Object o){
        objectMap.put(s, o);
    }

    public static Map<String, Object> getMap(){
        return objectMap;
    }
}
