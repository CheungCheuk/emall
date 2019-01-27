package com.cheung.emall.web;

import com.cheung.emall.pojo.Indent;
import com.cheung.emall.service.IndentItemService;
import com.cheung.emall.service.IndentService;
import com.cheung.emall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class IndentController {

    @Autowired
    IndentService indentService;
    @Autowired
    IndentItemService indentItemService;

    @GetMapping("/indents")
    public List<Indent> getAllIndent() throws Exception{
        return indentService.listIndent();
    }
    /**
     * 订单发货功能
     */
    @PutMapping("indentDelivery/{indent_id}")
    public Object indentDelivery(@PathVariable("indent_id") int indentId)throws Exception{
        Indent requiredDeliveryIndent = indentService.get(indentId);
        requiredDeliveryIndent.setDeliveryDate(new Date());
        requiredDeliveryIndent.setStatus(IndentService.WAITING_CONFIRMATION);
        indentService.update(requiredDeliveryIndent);
        return Result.success();
    }

}
