package com.cheung.emall.service;

import com.cheung.emall.pojo.Category;
// import com.cheung.emall.util.Page4Navigator;

import java.util.List;

public interface CategoryService {
    List<Category> listCategory();

//    Page4Navigator<Category> listCategoryByPaging(int start, int size, int navigatePages);
    void add(Category bean);
    void delete(int id);
    Category get(int id);
    void update(Category category);
}
