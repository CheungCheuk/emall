
package com.cheung.emall.service;

import java.util.List;

import com.cheung.emall.dao.IndentItemDao;
import com.cheung.emall.pojo.Good;
import com.cheung.emall.pojo.Indent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cheung.emall.pojo.IndentItem;

/**
 * 作用：
 * 1.计算每个订单中的每个商品的数目。<br>
 * 2.计算每个订单的总金额。<br>
 * 3.展示订单中每个商品的缩略图。<br>
 * 4.为每个订单实体，设置对应的订单项因为数据库中，
 * 订单实体没有对应的订单项属性，而订单和订单项属于一对多关系。<br>
 * @author 张卓宏
 * @version 1.0
 */
@Service
public class IndentItemService {
    @Autowired IndentItemDao indentItemDao;
    @Autowired GoodImageService goodImageService;


    public List<IndentItem> findByIndent(Indent indent) {
        return  indentItemDao.findByIndentOrderByIdDesc(indent);
    }
    /**
     * 为多个订单设置订单项
     * @param indents List
     * @return void
     */
    public void setEachIndent(List<Indent> indents){
        for (Indent indent : indents) {
            setEachIndentItem(indent);
        }
    }
    /**
     * 为单个订单(Indnet)设置订单项(IndnetItem)
     * 1.计算订单的商品数目
     * 2.计算订单总金额
     * 3.设置订单项的商品缩略图
     * @param indent Indent
     * @return void
     */
    public void setEachIndentItem(Indent indent){
        List<IndentItem> indentItems = indentItemDao.findByIndentOrderByIdDesc(indent);
        float eachItemTotalPrice = 0;
        int eachItemAmount = 0;
        
        for (IndentItem indentItem : indentItems) {
            eachItemTotalPrice += indentItem.getNumber() * indentItem.getGood().getPromotePrice();
            eachItemAmount = indentItem.getNumber();
            // goodImageService.findShrinkImage(indentItem.getGood());  bug来源，导致没有传输到图片到客户端
            goodImageService.setOneShrinkImage(indentItem.getGood());
        }
        indent.setIndentItems(indentItems);
        indent.setEachItemAmount(eachItemAmount);
        indent.setEachItemTotalPrice(eachItemTotalPrice);
    }
    // frontend
    public IndentItem add(IndentItem indentItem){
        return indentItemDao.save(indentItem);
    }
    public IndentItem get (int id){
        return indentItemDao.findOne(id);
    }
    public void delete(int id){
        indentItemDao.delete(id);
    }
    public List<IndentItem> getByGood(Good good){
        return indentItemDao.findByGood(good);
    }
    public List<IndentItem> getByIndent(Indent indent){
        return indentItemDao.findByIndentOrderByIdDesc(indent);
    }
    public int getSaleAmount(Good good){
        List<IndentItem> indentItems = indentItemDao.findByGood(good);
        int amount = 0;
        for( IndentItem indentItem : indentItems) {
            // 如果订单存在，则设置销量
            if ( null != indentItem.getIndent() ){
                // 如果订单已付款，则根据订单项的购买数量，累加
                // 一个订单项，对应一个商品
                if ( null != indentItem.getIndent().getPayDate() ){
                    amount+=indentItem.getNumber();
                }
            }
        }
        return amount;
    }
}