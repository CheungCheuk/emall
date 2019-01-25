package com.cheung.emall.dao;

import java.util.List;

import com.cheung.emall.pojo.User;

import org.springframework.data.domain.Sort;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * UserDao
 */
public interface UserDao extends CrudRepository<User, Integer> {
    List<User> findAll(Sort sort);
}