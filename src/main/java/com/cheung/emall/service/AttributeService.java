package com.cheung.emall.service;

import java.util.List;

import com.cheung.emall.dao.AttributeDao;
import com.cheung.emall.pojo.Attribute;
import com.cheung.emall.pojo.Category;
// import com.cheung.emall.service.AttributeService;
import com.cheung.emall.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * AttributeServiceImpl
 */
@Service
public class AttributeService{
    @Autowired
    AttributeDao attributeDao;

    @Autowired
    CategoryService categoryService;


    public Attribute add(Attribute attribute) {
        return attributeDao.save(attribute);
    }
    public void delete(int id) {
        attributeDao.delete(id);
    }
    
    public Attribute update(Attribute attribute) {
        return attributeDao.save(attribute);
    }
    public Attribute get(int id) {
        return attributeDao.findOne(id);
    }

    public List<Attribute> list(int category_id) {

        Category category = categoryService.get(category_id);
        // Sort sort = new Sort(Sort.Direction.DESC,"id");
        // attributeDao.findAll(example, sort)
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        // return categoryDAO.findAll(sort);
        // return attributeDao.findByCategory(category);
        return attributeDao.findByCategory(category,sort);  //  可能会出 bug

    }
    public List<Attribute> listByCategory(Category category) {
        return attributeDao.findByCategory(category);
    }



    
}