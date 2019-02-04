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
 * 在Controller
 */
@Service
public class IndentService {
    public static final String WAITING_PAYMENT = "waiting_payment";
    public static final String WAITING_DELIVERY = "waiting_delivery";
    public static final String WAITING_CONFIRMATION = "waiting_confirmation";
    public static final String WAITING_COMMENT = "waiting_comment";
    public static final String FINISH = "finish"; 
    public static final String DELETE = "delete";
    
    @Autowired IndentDao indentDao;

    public List<Indent> listIndent(){
        // Sort sort = new Sort(Sort.Direction.DESC);   bug来源
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<Indent> indents = indentDao.findAll(sort);
        return indents;
    }

    /**
     * 因为每个订单 Indent 中包含多个 IndentItem，
     * 每个 IndentItem 中对应一个 Indent，
     * 使用Restful风格时，这些属性，会被JSON化，
     * 产生无限递归，导致系统崩溃
     * @param Indent
     */
    public void avoidUnlimitedRecursion(List<Indent> indents){
        for (Indent indent : indents) {
            avoidUnlimitedRecursion(indent);
        }
    }

    public void avoidUnlimitedRecursion(Indent indent){
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