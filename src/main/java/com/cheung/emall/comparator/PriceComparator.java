package com.cheung.emall.comparator;

import java.util.Comparator;

import com.cheung.emall.pojo.Good;

/**
* 
*/
public class PriceComparator implements Comparator<Good>{ 
    @Override
    public int compare(Good o1, Good o2) {
        // 从低到高
        return (int) (o1.getPromotePrice() - o2.getPromotePrice());
    }
}