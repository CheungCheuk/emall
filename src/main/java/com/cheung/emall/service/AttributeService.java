package com.cheung.emall.service;

import java.util.List;

import com.cheung.emall.pojo.Attribute;
import com.cheung.emall.pojo.Category;

// import org.springframework.data.domain.Sort;

/**
 * AttributeService
 */
public interface AttributeService {
    Attribute add(Attribute attribute);
    void delete(int id);
    Attribute update(Attribute attribute);
    Attribute get(int id);
    List<Attribute> list(int category_id);
    List<Attribute> listByCategory(Category category);
}