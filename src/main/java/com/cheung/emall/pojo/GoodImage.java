

package com.cheung.emall.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * GoodImage
 */

@Entity
@Table(name = "good_image")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class GoodImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "good_id")
    /**
     * 该注释用来表明，关联的属性是双向结合域之间的一部分，它的角色是 “child”（或“back”），
     * 属性类型必须是一个bean，而不能是 Map、Array或者枚举类型
     * 被注释的属性将不会序列化
     * 相反，其父部分将被 @JsonManagedReference 注释，且完成序列化
     */
    @JsonBackReference
    private Good good;

    private String type;

    /**
     * @return the good
     */
    public Good getGood() {
        return good;
    }
    /**
     * @param good the good to set
     */
    public void setGood(Good good) {
        this.good = good;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}















