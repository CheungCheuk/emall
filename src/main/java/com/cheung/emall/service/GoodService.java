



package com.cheung.emall.service;

import java.util.List;

import com.cheung.emall.dao.GoodDao;
import com.cheung.emall.pojo.Category;
import com.cheung.emall.pojo.Good;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * GoodService
 */
@Service
public class GoodService {
    @Autowired GoodDao goodDao;
    @Autowired CategoryService categoryService;

    public Good add(Good good) {
        return goodDao.save(good);
    }
    public void delete(int id) {
        goodDao.delete(id);
    }
    public Good update(Good good) {
        return goodDao.save(good);
    }
    public Good get(int id) {
        return goodDao.findOne(id);
    }
    public List<Good> list(int category_id) {
        Category category = categoryService.get(category_id);
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        return goodDao.findByCategory(category, sort);
    }
    
}





