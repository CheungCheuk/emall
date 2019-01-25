package com.cheung.emall.service;

import java.util.List;

import com.cheung.emall.dao.UserDao;
import com.cheung.emall.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {
    @Autowired UserDao userDao;

    public List<User> getAllUser() {
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        return userDao.findAll(sort);
    }
    
}