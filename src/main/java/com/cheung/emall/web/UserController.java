package com.cheung.emall.web;

import java.util.List;

import com.cheung.emall.pojo.User;
import com.cheung.emall.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@RestController
public class UserController {

    @Autowired UserService userService;

    @GetMapping("/users")
    public List<User> listAllUser () throws Exception{
        return userService.getAllUser();
    }
}