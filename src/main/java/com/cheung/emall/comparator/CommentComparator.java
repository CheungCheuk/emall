package com.cheung.emall.comparator;

import java.util.Comparator;

import com.cheung.emall.pojo.Good;

/**
* 
*/
public class CommentComparator implements Comparator<Good> { 
    @Override
    public int compare(Good o1, Good o2) {
        return o1.getCommentAmount() - o2.getCommentAmount();
    }
}