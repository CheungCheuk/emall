package com.cheung.emall.dao;

import java.util.List;

import com.cheung.emall.pojo.Comment;
import com.cheung.emall.pojo.Good;

import org.springframework.data.repository.CrudRepository;

/**
* 
*/
public interface CommentDao extends CrudRepository<Comment, Integer>{
    /**
     * 用于展示每个商品的评论
     */
    List<Comment> findByGoodOrderByIdDesc(Good good);

    /**
     * 根据good_id，选择对应的comment，并计算其数量
     */
    int countByGood(Good good);
}