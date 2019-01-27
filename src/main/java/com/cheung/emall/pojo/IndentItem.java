package com.cheung.emall.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * IndentItem
 */
@Entity
@Table(name = "indent_item")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class IndentItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "good_id")
    private Good good;

    @ManyToOne
    @JoinColumn(name = "indent_id")
    private Indent indent;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private User user;

    private int number;
    
    /**
     * @param good the good to set
     */
    public void setGood(Good good) {
        this.good = good;
    }
    /**
     * @return the good
     */
    public Good getGood() {
        return good;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param indent the indent to set
     */
    public void setIndent(Indent indent) {
        this.indent = indent;
    }
    /**
     * @return the indent
     */
    public Indent getIndent() {
        return indent;
    }
    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }
    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }
    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }
    
}