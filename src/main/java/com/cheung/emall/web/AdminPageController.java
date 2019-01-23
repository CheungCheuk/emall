package com.cheung.emall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 处理后台管理页面的controller，
 * 负责后台系统页面之间的跳转，非RESTful服务接口
 */

@Controller
public class AdminPageController {
//    等价注解
//    @GetMapping("/admin")
//    @GetMapping(value = "/admin")
//    @RequestMapping(path = "/admin",method = RequestMethod.GET)
    
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String adminRedirect(){
        return "redirect:admin_category";
    }

//    @RequestMapping(path = "/admin_classification",method = RequestMethod.GET)
    
    @GetMapping("/admin_category")
    public String showCategory(){
        //  跳转到 adminCategory.html 页面
        return "adminPage/adminCategory";
    }

    @GetMapping("/edit_category")
    public String editCategory(){
        return "adminPage/editCategory";
    }
    @GetMapping("/admin_attribute")
    public String listAttribute(){
        return "adminPage/adminAttribute";
    }

    @GetMapping("/edit_attribute")
    public String editAttribute(){
        return "adminPage/editAttribute";
    }

    @GetMapping("/admin_good")
    public String listGood(){
        return "adminPage/adminGood";
    }

    @GetMapping("/edit_good")
    public String editGood(){
        return "adminPage/editGood";
    }

    @GetMapping("/admin_goodImage")
    public String listImage(){
        return "adminPage/adminGoodImage";
    }
    

    @GetMapping("/admin_indent")
    public String listIndent(){
        return "adminPage/adminIndent";
    }

    @GetMapping("/admin_user")
    public String listUser(){
        return "adminPage/adminUser";
    }

    @GetMapping("/edit_attributevalue")
    public String listAttributeValue(){
        return "adminPage/editAttributeValue";
    }

    

}
