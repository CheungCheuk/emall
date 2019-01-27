package com.cheung.emall.dao;

import java.util.List;

import com.cheung.emall.pojo.Indent;
import com.cheung.emall.pojo.IndentItem;

import org.springframework.data.repository.CrudRepository;

/**
 * IndentItemDao
 */
public interface IndentItemDao extends CrudRepository<IndentItem, Integer>{
    List<IndentItem> findByIndentOrderByIdDesc(Indent indent);
    
}

