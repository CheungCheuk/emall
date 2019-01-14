
package com.cheung.emall.web;

import java.util.List;

// import javax.websocket.server.PathParam;

import com.cheung.emall.pojo.Attribute;
import com.cheung.emall.service.AttributeService;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTful风格的商品类别属性控制器，处理 Ajax请求
 * AttributeController
 */

@RestController
public class AttributeController {
    @Autowired AttributeService attributeService;
    
    @GetMapping("/categories/{cid}/attributes")
    public List<Attribute> listAttribute( @PathVariable("cid")int cid ) throws Exception{
        return attributeService.list(cid);
    }

    @GetMapping("/attributes/{id}")
    public Attribute getAttribute( @PathVariable("id") int id) throws Exception{
        return attributeService.get(id);
    }
    @DeleteMapping("/attributes/{id}")
    public String deleteAttribute( @PathVariable("id")int id )throws Exception{
        attributeService.delete(id);
        return null;
    }

    @PostMapping("/attributes")
    public Attribute addAttribute( @RequestBody Attribute attribute)throws Exception{
        attributeService.add(attribute);
        return attribute;
    }

    @PutMapping("/attributes")
    public Attribute updateAttribute( @RequestBody Attribute attribute )throws Exception{
        attributeService.update(attribute);
        return attribute;
    }

    
    
}




