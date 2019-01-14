


package com.cheung.emall.dao;

import java.util.List;

import com.cheung.emall.pojo.Attribute;
import com.cheung.emall.pojo.Category;

import org.springframework.data.domain.Sort;
// import org.springframework.data.domain.Page;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * AttributeDao
 */
public interface AttributeDao extends CrudRepository<Attribute,Integer>{
    List<Attribute> findByCategory(Category category, Sort sort);   //  感觉这句会出问题
}






