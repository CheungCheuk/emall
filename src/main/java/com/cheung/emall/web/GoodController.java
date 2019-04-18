package com.cheung.emall.web;

import java.util.Date;
import java.util.List;

import com.cheung.emall.pojo.Good;
import com.cheung.emall.service.CategoryService;
import com.cheung.emall.service.GoodImageService;
import com.cheung.emall.service.GoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * GoodController
 */
@RestController
// @CacheConfig( cacheNames = "category")
public class GoodController {
    @Autowired GoodService goodService;
    @Autowired CategoryService categoryService;
    @Autowired GoodImageService goodImageService;


    @PostMapping("/goods")
    // @CacheEvict(allEntries=true, key = "'home'")
    public Good addgGood( @RequestBody Good good ) throws Exception{
        good.setCreateDate(new Date());
        return goodService.add(good);
    }

    @DeleteMapping("/goods/{id}")
    // @CacheEvict(allEntries=true, key = "'home'")
    public void deleteGood( @PathVariable("id") int id) {
        goodService.delete(id);
    }

    @PutMapping("/goods/{id}")
    // @CacheEvict(allEntries=true, key = "'home'")
    public Good updateGood( @PathVariable("id") int id, @RequestBody Good good) {        
        return goodService.update(good);
    }

    @GetMapping("/categories/{cid}/goods")
    public List<Good> listGoods( @PathVariable("cid") int category_id ) throws Exception{
        List<Good> goods = goodService.list(category_id);
        goodImageService.setMultipleShrinkImage(goods);
        return goods;
    }

    @GetMapping("/goods/{id}")
    public Good getGood( @PathVariable("id") int id) throws Exception{
        return goodService.get(id);
    }

    
}