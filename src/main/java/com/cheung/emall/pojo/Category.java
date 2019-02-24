package com.cheung.emall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import javax.persistence.*;
// @RedisHash("Category")
@Entity                 //  实体类
@Table(name = "category")   //  对应表名
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})   //  忽略无需 json 化的handler属性、hibernateLazyInitializer属性
public class Category {
    @Id                                                 //  主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  AutoIncrement
    @Column(name = "id")                                //  对应的数据库字段名
    int id;
    String name;

    //  一个分类下的所有商品
    @Transient
    List<Good> goods;
    //  矩阵商品列表，用于展示商品页面
    @Transient
    List<List<Good>> matrixGoods;


    public List<Good> getGoods() {
        return this.goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }


    public List<List<Good>> getMatrixGoods() {
        return this.matrixGoods;
    }

    public void setMatrixGoods(List<List<Good>> matrixGoods) {
        this.matrixGoods = matrixGoods;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
