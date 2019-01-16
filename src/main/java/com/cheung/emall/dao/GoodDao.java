

package com.cheung.emall.dao;

import java.util.List;

import com.cheung.emall.pojo.Category;
import com.cheung.emall.pojo.Good;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.repository.Repository;

/**
 * GoodDao
 */
public interface GoodDao extends  CrudRepository<Good,Integer>{
    List<Good> findByCategory(Category category, Sort sort);
}
