package com.cheung.emall.service;

import java.util.List;

import com.cheung.emall.dao.CommentDao;
import com.cheung.emall.pojo.Comment;
import com.cheung.emall.pojo.Good;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* 
*/
@Service
public class CommentService {
    @Autowired
    GoodService goodService;
    @Autowired
    CommentDao commentDao;

    public Comment add(Comment comment){
        return commentDao.save(comment);
    }
    /**
     * 详细展示每条评论
     */
    public List<Comment> findByGood(Good good){
        return commentDao.findByGoodOrderByIdDesc(good);
    }

    /**
     * 计算每个商品的累计评价数量
     */
    public int getCommentAmount(Good good) {
        return commentDao.countByGood(good);
    }
}