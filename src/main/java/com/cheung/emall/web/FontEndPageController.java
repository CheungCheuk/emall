package com.cheung.emall.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


// import org.springframework.stereotype.Controller;

/**
 * 用于前端页面跳转
 */

@Controller
public class FontEndPageController {

    @GetMapping(value="/")
    public String index() {
        return "redirect:home";
    }
    
    @GetMapping("/home")
    public String home() {
        return "frontPage/home";
    }

    @GetMapping("/register")
    public String register(){
        return "frontPage/register/register";
    }

    @GetMapping("/registerSuccess")
    public String registerSuccess(){
        return "frontPage/register/registerSuccess";
    }

    @GetMapping("/login")
    public String  login(){
        return "frontPage/login";
    }    

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:home";
    }
    @GetMapping("/good")
    public String goodPage(){
        return "frontPage/good";
    }
    @GetMapping("/category")
    public String category(){
        return "frontPage/category";
    }
    @GetMapping("/cart")
    public String cart(){
        return "frontPage/cart";
    }

    @GetMapping("/buy")
    public String buy(){
        return "frontPage/buy";
    }
    @GetMapping("/bought")
    public String bought(){
        return "frontPage/bought";
    }
    @GetMapping("/confirmPay")
    public String confirmPay(){
        return "frontPage/confirmPay";
    }
    @GetMapping("/indentConfirmed")
    public String indentConfirmed(){
        return "frontPage/indentConfirmed";
    }

    @GetMapping("/paid")
    public String paid(){
        return "frontPage/paid";
    }

    @GetMapping("/comment")
    public String comment(){
        return "frontPage/comment";
    }

    @GetMapping("/search")
    public String search(){
        return "frontPage/searchResult";
    }
}
