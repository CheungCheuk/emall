package com.cheung.emall.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


// import org.springframework.stereotype.Controller;

/**
 * 用于前端页面跳转
 */

@Controller
public class FontEndController {

    @GetMapping(value="/")
    public String index() {
        return "redirect:home";
    }
    @GetMapping("/home")
    public String home() {
        return "frontPage/home";
    }
    

    
}
