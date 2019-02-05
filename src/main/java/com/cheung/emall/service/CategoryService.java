package com.cheung.emall.service;

import com.cheung.emall.dao.CategoryDAO;
import com.cheung.emall.pojo.Category;
import com.cheung.emall.pojo.Good;
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
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    
    public List<Category> listCategory() {
        //  根据 id 倒排序
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        return categoryDAO.findAll(sort);
    }
    
    public Category add(Category bean) {
        return categoryDAO.save(bean);
    }

    public void delete(int id) {
        categoryDAO.delete(id);
    }

    
    public Category get(int id) {
        return categoryDAO.findOne(id);
    }
    
    
    public Category update(Category category) {
        return categoryDAO.save(category);
    }


//    @Override
//    public Page4Navigator<Category> listCategoryByPaging(int start, int size, int navigatePages) {
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
//        Pageable pageable = new PageRequest(start, size, sort);
//        Page pageFromJPA = categoryDAO.findAll(pageable);
//        return new Page4Navigator<>(pageFromJPA, navigatePages);
//    }

/*C
 * Category 中含有 good，属性
 * Good 又含有 category 属性，
 * 这时在 json 序列化的时候会产生无限递归，
 * 该方法将 该Category 下对应的 Good 的category 属性，设置为null
 */
    public void avoidUnlimitedRecursionInCategory(List<Category> categories) {
        for (Category category : categories) {
            avoidUnlimitedRecursionInCategory(category);
        }
    }

    public void avoidUnlimitedRecursionInCategory(Category category) {
        //  将一个 category 下对应的所有 good
        List<Good> goods = category.getGoods();
        if (null != goods) {
            for (Good good : goods) {
                good.setCategory(null);
            }
        }
        //  将用于展示的矩阵列表，设置为 null
        List<List<Good>> matrixGoods = category.getMatrixGoods();
        if (null != matrixGoods) {
            
            for (List<Good> eachRowGoods : matrixGoods) {
                for (Good good : eachRowGoods) {
                    good.setCategory(null);
                } 
            }
        }
    }

}
