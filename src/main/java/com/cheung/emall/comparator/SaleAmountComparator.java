package com.cheung.emall.comparator;

import java.util.Comparator;

import com.cheung.emall.pojo.Good;

/**
* 销售数量
*/
public class SaleAmountComparator  implements Comparator<Good>{ 
    @Override
    public int compare(Good o1, Good o2) {
        return o1.getSaleAmount() - o2.getSaleAmount();
    }
}