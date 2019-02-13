package com.cheung.emall.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    GoodImageService goodImageService;
    @Autowired
    AttributeValueService attributeValueService;
    @Autowired
    CommentService commentService;

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
    public Result listGood(@PathVariable int good_id){
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
    
}