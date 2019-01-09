package com.cheung.emall.service.impl;

import com.cheung.emall.dao.CategoryDAO;
import com.cheung.emall.pojo.Category;
import com.cheung.emall.service.CategoryService;
// import com.cheung.emall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<Category> listCategory() {
        //  根据 id 倒排序
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        return categoryDAO.findAll(sort);
    }
    @Override
    public void add(Category bean) {
        categoryDAO.save(bean);
    }

    @Override
    public void delete(int id) {
        categoryDAO.delete(id);
    }

//    @Override
//    public Page4Navigator<Category> listCategoryByPaging(int start, int size, int navigatePages) {
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
//        Pageable pageable = new PageRequest(start, size, sort);
//        Page pageFromJPA = categoryDAO.findAll(pageable);
//        return new Page4Navigator<>(pageFromJPA, navigatePages);
//    }

    

}
