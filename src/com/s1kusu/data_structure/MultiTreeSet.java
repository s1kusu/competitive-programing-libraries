package com.s1kusu.data_structure;

import java.util.*;

class MultiTreeSet<K> extends TreeMap<K, Long> {

    public long update(K key, long val){
        if(!contains(key)){
            put(key, val);
            return val;
        }
        long result = get(key) + val;
        if(result == 0) remove(key);
        else put(key, result);
        return result;
    }

    public long push(K key){
        return update(key, 1);
    }

    public long pop(K key){
        return update(key, -1);
    }

    public long count(K key){
        return nvl(get(key));
    }

    public boolean contains(K key){
        return containsKey(key);
    }


    public K first(){
        return firstKey();
    }

    public K last(){
        return lastKey();
    }

    public K floor(K key){
        return floorKey(key);
    }

    public K ceiling(K key){
        return ceilingKey(key);
    }

    public K higher(K key){
        return higherKey(key);
    }

    public K lower(K key){
        return lower(key);
    }

    public K pushFirst(){
        K first = first();
        push(first);
        return first;
    }

    public K pushLast(){
        K last = last();
        push(last);
        return last;
    }

    public K popFirst(){
        K first = first();
        pop(first);
        return first;
    }

    public K popLast(){
        K last = last();
        pop(last);
        return last;
    }


    private long nvl(Long val){
        return val == null ? 0L : val;
    }
}
