package com.cheung.emall.web;

// import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.cheung.emall.comparator.CommentComparator;
import com.cheung.emall.comparator.DateComparator;
import com.cheung.emall.comparator.PriceComparator;
import com.cheung.emall.comparator.SaleAmountComparator;
import com.cheung.emall.comparator.SynthesisComparator;
import com.cheung.emall.pojo.AttributeValue;
import com.cheung.emall.pojo.Category;
import com.cheung.emall.pojo.Comment;
import com.cheung.emall.pojo.Good;
import com.cheung.emall.pojo.GoodImage;
import com.cheung.emall.pojo.User;
import com.cheung.emall.service.AttributeValueService;
import com.cheung.emall.service.CategoryService;
import com.cheung.emall.service.CommentService;
import com.cheung.emall.service.GoodImageService;
import com.cheung.emall.service.GoodService;
import com.cheung.emall.service.UserService;
import com.cheung.emall.util.Result;

// import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 返回 json 数据的RestContrller
 */

@RestController
public class ForeRESTController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    GoodService goodService;
    @Autowired
    UserService userService;
    @Autowired
    GoodImageService goodImageService;
    @Autowired
    AttributeValueService attributeValueService;
    @Autowired
    CommentService commentService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        String tempName = user.getName();
        if ( userService.isUserConflict(tempName) ){
            return Result.fail("用户名已存在");
        }
        userService.addUser(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session){
        String tempName = user.getName();
        String tempPassword = user.getPassword();
        if ( userService.isUserAndPasswordEqual(tempName, tempPassword) ){
            session.setAttribute("user", user);
            return Result.success();
        }else{
            return Result.fail("账户密码不匹配");
        }
    }

    @GetMapping("/froegood/{good_id}")
    @CachePut(value = "category", key = " 'good_id:' + #good_id ")
    public Result getGood(@PathVariable int good_id){
        Good good = goodService.get(good_id);
        List<Comment> comments = commentService.findByGood(good);
        List<AttributeValue> attributeValues = attributeValueService.findByGood(good);
        List<GoodImage> shrinkImageList = goodImageService.findShrinkImage(good);
        List<GoodImage> detailImageList = goodImageService.findDetailImage(good);
        good.setShrinkImageList(shrinkImageList);
        good.setDetailImageList(detailImageList);

        goodService.setSaleAndCommentAmount(good);
        goodImageService.setOneShrinkImage(good);   // ??干嘛的

        HashMap<String, Object> goodPageMap = new HashMap<>();
        goodPageMap.put("good", good);
        goodPageMap.put("attributeValues",attributeValues);
        goodPageMap.put("comments", comments);

        return Result.success(goodPageMap);
    }

    @GetMapping("/foreCheckLogin")
    public Result checkLogin(HttpSession httpsession) {
        User use = (User) httpsession.getAttribute("user");
        if ( null != use ){
            return Result.success();
        }else{
            return Result.fail("未登录");
        }
    }


    @GetMapping("/forehome")
    @Cacheable(value = "category", key = "#root.methodName")
    public List<Category> homeListCategories() {
        List<Category> categories = categoryService.listCategory();
        goodService.setCategoryInGood(categories);   
        // goodService.fillMatrixGoods(categories);
        categoryService.avidoStackOverFlow(categories);
        return categories;
    }

    @GetMapping("/foreCategory/{category_id}")
    @Cacheable(value = "category", key = "#root.methodName")
    public Category getCategory(@PathVariable("category_id") int id, @RequestParam("sort") String sort){
        Category category = categoryService.get(id);
        goodService.setCategoryInGood(category);
        List<Good> goodsList = category.getGoods();
        goodService.setSaleAndCommentAmount(goodsList);
        categoryService.avidoStackOverFlow(category);
        // 第一次请求该页面，默认不会根据 sort 字段排序
        // 默认根据 id 排序
        if ( null != sort ){
            switch (sort) {
                case "comment":
                    Collections.sort(goodsList, new CommentComparator());
                    break;
                case "date":
                    Collections.sort(goodsList, new DateComparator());
                case "saleAmount":
                    Collections.sort(goodsList, new SaleAmountComparator());
                case "price":
                    Collections.sort(goodsList, new PriceComparator());
                case "synthesis":
                    Collections.sort(goodsList, new SynthesisComparator());
                default:
                    break;
            }
        }
        category.setGoods(goodsList);
        return category;
    }

    @GetMapping("/foreSearch")
    public List<Good> search(@RequestParam("keyword") String keyword){
        if ( null == keyword ){
            keyword = "";
        }
        // List<Good> goods = goodService.search(keyword);
        List<Good> goods = goodService.esSearch(keyword);
        goodImageService.setMultipleShrinkImage(goods);
        goodService.setSaleAndCommentAmount(goods);
        return goods;
    }

    // @GetMapping("/foreAddCart)
    
}