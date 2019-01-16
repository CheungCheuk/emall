

package com.cheung.emall.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Good
 */
@Entity
@Table(name = "good")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
// @Document(indexName = "emall",type = "good")    //  import org.springframework.data.elasticsearch.annotations.Document;
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;
    private String subTitle;
    private float originalPrice;
    private float promotePrice;
    private int stock;
    private Date createDate;

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }
    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the originalPrice
     */
    public float getOriginalPrice() {
        return originalPrice;
    }
    /**
     * @return the promotePrice
     */
    public float getPromotePrice() {
        return promotePrice;
    }
    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }
    /**
     * @return the subTitle
     */
    public String getSubTitle() {
        return subTitle;
    }
    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }
    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param originalPrice the originalPrice to set
     */
    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }
    /**
     * @param promotePrice the promotePrice to set
     */
    public void setPromotePrice(float promotePrice) {
        this.promotePrice = promotePrice;
    }
    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    /**
     * @param subTitle the subTitle to set
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}


