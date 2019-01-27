



package com.cheung.emall.service;

import com.cheung.emall.pojo.Indent;
import com.cheung.emall.pojo.IndentItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.cheung.emall.dao.IndentDao;

import java.util.List;

/**
 * IndentService
 */
@Service
public class IndentService {
    public static final String WAITING_PAYMENT = "waiting_payment";
    public static final String WAITING_DELIVERY = "waiting_delivery";
    public static final String WAITING_CONFIRMATION = "waiting_confirmation";
    public static final String WAITING_COMMENT = "waiting_comment";
    public static final String FINISH = "finish"; 
    
    @Autowired IndentDao indentDao;

    public List<Indent> listIndent(){
        Sort sort = new Sort(Sort.Direction.DESC);
        return indentDao.findAll(sort);
    }

    public void removeIndentFromIdentItem(List<Indent> indnets){
        for (Indent indent : indnets) {
            removeIndentFromIdentItem(indent);
        }
    }

    public void removeIndentFromIdentItem(Indent indent){
        List<IndentItem> indentItems = indent.getIndentItems();
        for (IndentItem indentItem : indentItems) {
            indentItem.setIndent(null);
        }
    }

    public Indent get(int id){
        return indentDao.findOne(id);
    }

    public Indent update(Indent indent){
        return indentDao.save(indent);
    }
    
    
    
    
}