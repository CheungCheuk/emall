package com.cheung.emall.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * User
 * {@value}anonymousName
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String password;
    private String salt;

    //  匿名
    @Transient
    private String anonymousName;

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
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }
    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }
    /**
     * 生成用户名的匿名格式，
     * 如：原格式为：user 生成格式为： u**r。
     * 当用户名为一个字符时，
     * 生成格式为：*。当用户名字为两个字符时，生成格式为：u*。
     *
     * @return the anonymousName
     */
    public String getAnonymousName() {
        if ( null != anonymousName ) {
            return anonymousName;
        }
        // if( null == name){
        //     anonymousName = null;
        //     return anonymousName;
        // }
        //  使用卫语句进行重构
        if ( name.length() <= 1 ){
            anonymousName = "*";
            return anonymousName;
        }

        if ( name.length() == 2 ){
            anonymousName = name.substring(0, 1) + "*";
            return anonymousName;
        }else{
            char tempChar[] = name.toCharArray();
            for (int i = 1; i < tempChar.length-1; i++) {
                tempChar[i] = '*';
            }
            anonymousName = new String(tempChar);
            return anonymousName;
        }
    }

}