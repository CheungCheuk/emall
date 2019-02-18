package com.cheung.emall.comparator;

import java.util.Comparator;

import com.cheung.emall.pojo.Good;

/**
* 根据日期比较
*/
public class DateComparator implements Comparator<Good> { 
    @Override
    public int compare(Good o1, Good o2) {
        // 从新到旧
        // int a = o1.getCreateDate().compareTo( o2.getCreateDate() );
        return o1.getCreateDate().compareTo( o2.getCreateDate() );
        
        // 从旧到新
        // return o2.getCreateDate().compareTo(o1.getCreateDate());
    }
}