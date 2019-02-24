package com.cheung.emall.service;

import java.util.ArrayList;
import java.util.List;

import com.cheung.emall.dao.GoodDao;
import com.cheung.emall.pojo.Category;
import com.cheung.emall.pojo.Good;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * GoodService
 */
@Service
public class GoodService {
    @Autowired GoodDao goodDao;
    @Autowired CategoryService categoryService;
    @Autowired GoodImageService goodImageService;
    @Autowired IndentItemService indentItemService;
    @Autowired CommentService commentService;

    // @CacheEvict(allEntries = true)
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


    /**
     * 为分类填充产品集合：
     * 1.填充每个商品的图片
     * 2.填充每个分类下的商品
     */
    public void setCategoryInGood(Category category) {
        List<Good> goods = goodDao.findByCategoryOrderByIdDesc(category);
        goodImageService.setMultipleShrinkImage(goods);
        category.setGoods(goods);
    }

    /**
     * 为多个分类，填充产品集合
     */
    public void setCategoryInGood(List<Category> categories) {
        for (Category category : categories) {
            setCategoryInGood(category);
        }
    }

    /**
     * 为所有分类，
     * 设置矩阵商品列表
     */
    public void fillMatrixGoods(List<Category> categories) {
        //  矩阵商品列表的每行数目
        int amountOfEachRow = 8;
        //  为每一个分类，设置用于矩阵列表展示的商品
        for (Category category : categories) {
            List<Good> goods = category.getGoods();
            List<List<Good>> matrixGoods = new ArrayList<>();
            
            //  对包含所有商品的 goods 分割，将分割后的商品，分别保存到矩阵列表 martixGoods 每一行的中，
            //  例如，第一行，【0，1，2，3，4，5，6，7】
            // 第二行：【8，9，10，11，12，13，14，15】，以此类推
            // 每次分割
            for (int fromIndex = 0; fromIndex < goods.size(); fromIndex+=amountOfEachRow ) {
                int toIndex = fromIndex + amountOfEachRow;
                // 边界值判断
                if (toIndex > goods.size()) {
                    toIndex = goods.size();
                }
                List<Good> goodsOfEachRow = goods.subList(fromIndex, toIndex);
                matrixGoods.add(goodsOfEachRow);
            }
            category.setMatrixGoods(matrixGoods);
        }
    }

    public void setSaleAndCommentAmount(Good good){
        int saleAmount = indentItemService.getSaleAmount(good);
        good.setSaleAmount(saleAmount);

        int commentAmount = commentService.getCommentAmount(good);
        good.setCommentAmount(commentAmount);
    }

    public void setSaleAndCommentAmount(List<Good> goods){
        for( Good good : goods ){
            setSaleAndCommentAmount(good);
        }
    }

    public List<Good> search(String keyword){
        return goodDao.findByNameLike("%" + keyword + "%");
    }
}





