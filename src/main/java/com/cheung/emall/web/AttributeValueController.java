package com.cheung.emall.web;

import java.util.List;

import com.cheung.emall.pojo.AttributeValue;
import com.cheung.emall.pojo.Good;
import com.cheung.emall.service.AttributeValueService;
// import com.cheung.emall.service.AttributeValyeService;
import com.cheung.emall.service.GoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * AttributeValueController
 */
@RestController
public class AttributeValueController {
    @Autowired AttributeValueService attributeValueService;
    @Autowired GoodService goodService;


    @GetMapping("/goods/{good_id}/attribute_values")
    public List<AttributeValue> listAttributeValue( @PathVariable("good_id") int goodId) throws Exception{
        Good good = goodService.get(goodId);
        attributeValueService.initAttributeValue(good);
        return attributeValueService.findByGood(good);
    }

    // @PostMapping("/attribute_values")
    @PutMapping("/attribute_values")
    public AttributeValue addAttributeValue( @RequestBody AttributeValue attributeValue)throws Exception{
        return attributeValueService.update(attributeValue);
    }
    
        
    
    
}