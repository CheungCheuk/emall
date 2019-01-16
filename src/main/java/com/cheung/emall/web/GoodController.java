package com.cheung.emall.web;

import java.util.Date;
import java.util.List;

import com.cheung.emall.pojo.Good;
import com.cheung.emall.service.CategoryService;
import com.cheung.emall.service.GoodService;

import org.springframework.beans.factory.annotation.Autowired;
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
public class GoodController {
    @Autowired GoodService goodService;
    @Autowired CategoryService categoryService;


    @PostMapping("/goods")
    public Good addgGood( @RequestBody Good good ) throws Exception{
        good.setCreateDate(new Date());
        return goodService.add(good);
    }

    @DeleteMapping("/goods/{id}")
    public void deleteGood( @PathVariable("id") int id) {
        goodService.delete(id);
    }


    @PutMapping("/goods/{id}")
    public Good updateGood( @PathVariable("id") int id, @RequestBody Good good) {
        // Good updateBean = goodService.get(id);
        // updateBean.setName(good.getName());
        // updateBean.setOriginalPrice(good.getOriginalPrice());
        // updateBean.setPromotePrice(good.getPromotePrice());
        // updateBean.setStock(good.getStock());
        // updateBean.setSubTitle(good.getSubTitle());
        
        return goodService.update(good);
    }



    @GetMapping("/categories/{cid}/goods")
    public List<Good> listGoods( @PathVariable("cid") int cid ) throws Exception{
        return goodService.list(cid);
    }

    @GetMapping("/goods/{id}")
    public Good getGood( @PathVariable("id") int id) throws Exception{
        return goodService.get(id);
    }

    
}