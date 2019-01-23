package com.cheung.emall.dao;

import java.util.List;

import com.cheung.emall.pojo.Attribute;
import com.cheung.emall.pojo.AttributeValue;
import com.cheung.emall.pojo.Good;

// import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

/**
 * AttributeValueDao
 */
public interface AttributeValueDao extends CrudRepository<AttributeValue, Integer> {
    List<AttributeValue> findByGoodOrderByIdDesc(Good good);
    AttributeValue getByGoodAndAttribute(Good good, Attribute attribute);
}