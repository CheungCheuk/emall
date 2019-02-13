package com.cheung.emall.dao;

import java.util.List;

import com.cheung.emall.pojo.Good;
import com.cheung.emall.pojo.Indent;
import com.cheung.emall.pojo.IndentItem;

import org.springframework.data.repository.CrudRepository;

/**
 * IndentItemDao
 */
public interface IndentItemDao extends CrudRepository<IndentItem, Integer>{
    /**
     * 后台系统，根据订单号，展示订单项
     */
    List<IndentItem> findByIndentOrderByIdDesc(Indent indent);
    
    //  前端
    /**
     * 根据商品，显示订单项
     */
    List<IndentItem> findByGood(Good good);
}

