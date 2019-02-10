package com.cheung.emall.web;

import java.util.List;

import com.cheung.emall.pojo.Category;
import com.cheung.emall.pojo.User;
import com.cheung.emall.service.CategoryService;
import com.cheung.emall.service.GoodService;
import com.cheung.emall.service.UserService;
import com.cheung.emall.util.Result;

// import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/forehome")
    public List<Category> home() {
        List<Category> categories = categoryService.listCategory();
        goodService.setCategoryInGood(categories);
        goodService.fillMatrixGoods(categories);
        categoryService.avoidUnlimitedRecursionInCategory(categories);
        return categories;
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        String tempName = user.getName();
        if ( userService.isUserConflict(tempName) ){
            return Result.fail("用户名已存在");
        }
        userService.addUser(user);
        return Result.success();

    }
}