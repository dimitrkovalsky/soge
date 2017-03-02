package com.liberty.soge.util;

import lombok.Data;

@Data
public class Pair<K, V> {
    
    private K key;
    private V value;
    
    public Pair(K key, V value) {
        super();
        this.key = key;
        this.value = value;
    }
    
}
