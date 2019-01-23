package com.cheung.emall.service;

import java.util.List;

import com.cheung.emall.dao.AttributeValueDao;
import com.cheung.emall.pojo.Attribute;
import com.cheung.emall.pojo.AttributeValue;
// import com.cheung.emall.pojo.Category;
import com.cheung.emall.pojo.Good;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AttributeValyeService
 */
@Service
public class AttributeValueService {
    @Autowired AttributeValueDao attributeValueDao;
    @Autowired AttributeService attributeService;

    public AttributeValue update(AttributeValue attributeValue) {
        return attributeValueDao.save(attributeValue);
    }

    // public AttributeValue getByGoodAndAttribute(Good good, Attribute attribute) {
    //     return attributeValueDao.getByGoodAndAttribute(good, attribute);
    // }

    public List<AttributeValue> findByGood(Good good) {
        return attributeValueDao.findByGoodOrderByIdDesc(good);
    }

    /**
     * 根据 good 对应的 category，获得该 category 下的所有属性
     * 
     */
    public void initAttributeValue (Good good){
        List<Attribute> attributes = attributeService.listByCategory(good.getCategory());
        for (Attribute attribute  : attributes) {
            AttributeValue attributeValue = attributeValueDao.getByGoodAndAttribute(good, attribute);
            if ( null == attributeValue ) {
                attributeValue = new AttributeValue();
                attributeValue.setAttribute(attribute);
                attributeValue.setGood(good);
                attributeValueDao.save(attributeValue);
            }
        }
    }
    
}