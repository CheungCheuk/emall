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
    // frontEnd
    /**
     * 用户名冲突返回true，
     * 否则返回false；
     */
    public Boolean isUserConflict(String name){
        //  数据库中不存在该用户名
        if ( null == userDao.findByName(name) ){
            return false;
        }else{
            return true;
        }
    }
    public void addUser(User user){
        userDao.save(user);
    }

    /**
     * 用户名和密码匹配，返回true。
     * 不匹配，返回 flase
     */
    public Boolean isUserAndPasswordEqual (String name, String password){
        // User user = userDao.findByNameAndPassword(name, password);
        
        if ( null != userDao.findByNameAndPassword(name, password) ){
            return true;
        }else {
            return false;
        }
    }

}