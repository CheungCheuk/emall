


package com.cheung.emall.service;

import java.util.List;

// import com.cheung.emall.dao.GoodDao;
import com.cheung.emall.dao.GoodImageDao;
import com.cheung.emall.pojo.Good;
import com.cheung.emall.pojo.GoodImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GoodImageService
 */
@Service
public class GoodImageService {
    @Autowired 
    GoodImageDao goodImageDao;

    public static final String TYPE_SHRINK = "shrink";
    public static final String TYPE_DETAIL = "detail";

    public GoodImage add(GoodImage entity) {
        return goodImageDao.save(entity);
    }

    public void delete(int id) {
        goodImageDao.delete(id);
    }


    public GoodImage get(int id) {
        return goodImageDao.findOne(id);
    }

    public List<GoodImage> findShrinkImage(Good good) {
        return goodImageDao.findByGoodAndTypeOrderByIdDesc(good, TYPE_SHRINK);
    }

    public List<GoodImage> findDetailImage(Good good){
        return goodImageDao.findByGoodAndTypeOrderByIdDesc(good, TYPE_DETAIL);
    }

    /**
     * 为每个 商品（Good）设置单个图片
     */
    public void setOneShrinkImage(Good good) {
        List<GoodImage> shrinkImage = findShrinkImage(good);
        if ( !shrinkImage.isEmpty() ) {
            good.setShrinkImage(shrinkImage.get(0));
        } 
        // 在产品没有设置图片之前，可以在订单管理的订单项中查看对应的图片
        // else {
        //     good.setShrinkImage(new GoodImage());
        // }
    }

    /**
     * 为每个 商品（Good） 设置多个图片
     */
    public void setMultipleShrinkImage(List<Good> goods){
        for (Good good : goods) {
            setOneShrinkImage(good);
        }
    }
}




