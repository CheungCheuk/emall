


package com.cheung.emall.dao;

import java.util.List;

import com.cheung.emall.pojo.Good;
import com.cheung.emall.pojo.GoodImage;

import org.springframework.data.repository.CrudRepository;

/**
 * GoodImageDao
 */
public interface GoodImageDao extends CrudRepository<GoodImage, Integer> {
    List<GoodImage> findByGoodAndTypeOrderByIdDesc(Good good, String type);
}

