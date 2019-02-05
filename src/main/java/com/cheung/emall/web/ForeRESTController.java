package com.cheung.emall.web;

import java.util.List;

import com.cheung.emall.pojo.Category;
import com.cheung.emall.service.CategoryService;
import com.cheung.emall.service.GoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 返回 json 数据的RestContrller
 */

@RestController
public class ForeRESTController { 
    @Autowired CategoryService categoryService;
    @Autowired GoodService goodService;

    @GetMapping("/forehome")
    public List<Category> home(){
        List<Category> categories = categoryService.listCategory();
        goodService.setCategoryInGood(categories);
        goodService.fillMatrixGoods(categories);
        return categories;
    }
}