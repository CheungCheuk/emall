package com.cheung.emall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity                 //  实体类
@Table(name = "category")   //  对应表名
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})   //  忽略无需 json 化的handler属性、hibernateLazyInitializer属性
public class Category {
    @Id                                                 //  主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  AutoIncrement
    @Column(name = "id")                                //  对应的数据库字段名
    int id;
    String name;

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
