package com.cheung.emall.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cheung.emall.service.IndentService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 订单实体，每个订单含多个订单项，每个订单项代表一个商品
 */
@Entity
@Table( name = "indent" )
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Indent {
    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String orderCode;
    private String address;
    private String post;
    private String  receiver;
    private String mobile;
    private String userMessage;
    private Date createDate;
    private Date payDate;
    private Date deliveryDate;
    private Date confirmDate;
    private String status;

    @Transient
    private List<IndentItem> indentItems;
    @Transient
    private float eachItemTotalPrice;
    @Transient
    private int eachItemAmount;
    @Transient
    private String statusSituation;
    
    /**
     * @param statusDesc the statusDesc to set
     */
    public void setStatusSituation(String statusSituation) {
        this.statusSituation = statusSituation;
    }
    /**
     * @return the statusDesc
     */
    
    public String getStatusSituation() {
        if (null != statusSituation) {
            return statusSituation;
        }
        String situation = "状态未知";
        switch (status) {
            case IndentService.WAITING_DELIVERY:
                situation = "待发货";
                break;
            case IndentService.WAITING_PAYMENT:
                situation = "待支付";
                break;
            case IndentService.WAITING_CONFIRMATION:
                situation = "待确认收货";
                break;
            case IndentService.WAITING_COMMENT:
                situation = "待评价";
            case IndentService.FINISH:
                situation = "完成";
            case IndentService.DELETE:
                situation = "删除";
            default:
                situation = "状态未知";
                break;
        }
        statusSituation = situation;
        return statusSituation;
    }



    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost() {
        return this.post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserMessage() {
        return this.userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return this.payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getConfirmDate() {
        return this.confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<IndentItem> getIndentItems() {
        return this.indentItems;
    }

    public void setIndentItems(List<IndentItem> indentItems) {
        this.indentItems = indentItems;
    }
    
    public void setEachItemTotalPrice(float eachItemTotalPrice){
        this.eachItemTotalPrice = eachItemTotalPrice;
    }

    /**
     * @param eachItemAmount the eachItemAmount to set
     */
    public void setEachItemAmount(int eachItemAmount) {
        this.eachItemAmount = eachItemAmount;
    }
    /**
     * @return the eachItemAmount
     */
    public int getEachItemAmount() {
        return eachItemAmount;
    }
    /**
     * @return the eachItemTotalPrice
     */
    public float getEachItemTotalPrice() {
        return eachItemTotalPrice;
    }




}