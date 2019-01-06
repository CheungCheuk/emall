package com.cheung.emall.web;

import com.cheung.emall.pojo.Category;
import com.cheung.emall.service.CategoryService;
import com.cheung.emall.util.ImageUtil;
// import com.cheung.emall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 处理页面的 ajax 请求
 */
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> listCategory() throws Exception{
        return categoryService.listCategory();
    }
    @PostMapping("/categories")
    public Object add(Category category, MultipartFile image, HttpServletRequest request)
            throws Exception{
        categoryService.add(category);
        updateImage(category, image, request);//    忘记添加这句代码，上传补了
        return category;
    }
//    @GetMapping("/categories")
//    public Page4Navigator<Category> listCategoryByPages(
//            @RequestParam(value = "start",defaultValue = "0") int start,
//            @RequestParam(value = "size",defaultValue = "10") int size
//    ) throws Exception{
//        start = start < 0 ? 0:start;
//        Page4Navigator<Category> page4Navigator = categoryService.listCategoryByPaging(start,size,5);
//        return page4Navigator;
//    }

    

    public void updateImage(Category category, MultipartFile uploadedImage, HttpServletRequest request)
            throws IOException{

        File serverImageFolder = new File(request.getServletContext().getRealPath("img/category"));
        File serverImage = new File(serverImageFolder,category.getId()+".jpg");
        if ( !serverImageFolder.getParentFile().exists()){
            serverImageFolder.getParentFile().mkdirs();
        }
        //  将上传的图片，复制到服务器的指定位置，这里是：/img/category
        uploadedImage.transferTo(serverImage);

        //  将服务器的图片，转换成 可渲染的 图片缓冲数据
        BufferedImage renderPrototype = ImageUtil.reRenderImageBuffer(serverImage);
//        BufferedImage renderPrototype = ImageUtil.change2jpg(serverImage);
        //  将服务器的图片，转换成jpg格式
        ImageIO.write(renderPrototype,"jpg",serverImage);
    }
}
