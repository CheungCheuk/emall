package com.cheung.emall.comparator;

import java.util.Comparator;

import com.cheung.emall.pojo.Good;

/**
* 综合比较，比较销量和评论数量
*/
public class SynthesisComparator implements Comparator<Good> { 
    @Override
    public int compare(Good o1, Good o2) {
        return o1.getSaleAmount() * o1.getCommentAmount() - 
            o2.getSaleAmount() * o2.getCommentAmount();
    }
}