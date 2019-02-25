package com.cheung.emall.web;

import com.cheung.emall.pojo.Category;
import com.cheung.emall.service.CategoryService;

// import org.hibernate.annotations.Cache;
// import com.cheung.emall.util.ImageUtil;
// import com.cheung.emall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
// import org.springframework.cache.annotation.Caching;
// import org.springframework.cache.annotation.CacheEvict;
// import org.springframework.cache.annotation.CachePut;
// import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
// import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 处理页面的 ajax 请求
 */ 
@RestController
@CacheConfig(cacheNames = "category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/categories")
    @CachePut(key = "#root.methodName + 'Category' ")
    public Category add(Category category, MultipartFile image, HttpServletRequest request)
            throws Exception{
        categoryService.add(category);
        saveImage(category, image, request);//    忘记添加这句代码，无法上传
        //  java.lang.NullPointerException
        // java.lang.IllegalArgumentException: image == null!
        //  Resolved exception caused by Handler execution: java.lang.IllegalArgumentException: image == null!
        return category;
    }

    //  Resolved exception caused by Handler execution:
    //  org.springframework.dao.EmptyResultDataAccessException: 
    //  No class com.cheung.emall.pojo.Category entity with id 91 exists!
    //  @PathVariable 表明 方法的参数应该和 URI 中的模版参数绑定在一起


    @DeleteMapping("/categories/{id}")
    @CacheEvict(allEntries = true)
    public void delete(
        @PathVariable("id") int id,
        HttpServletRequest request
    )throws Exception{
        categoryService.delete(id);
        File imageFolder = new File(request.getServletContext().getRealPath("/img/category"));
        File imagFile = new File(imageFolder, id+".jpg");
        imagFile.delete();
        // return null;
    }

    @PutMapping("/categories/{id}")
    @CachePut(key = "#root.methodName + 'Category' ")
    public Category update(
        @PathVariable("id")int id, 
        MultipartFile image, 
        HttpServletRequest request)throws Exception{
        //  如果上串的图片含有 二进制文件，则不能使用 @RequestBody，
        // 否则报错：Content type 'multipart/form-data;boundary=----WebKitFormBoundaryBbKRwS9TvSpKp7Gs;charset=UTF-8' not supported
        // String categoryName = request.getParameter("name");  //  HttpServletRequest request
        // category.setName(categoryName);
        Category saveCateogy =  categoryService.get(id);    //  这种写法才是正确的 RESTful 风格写法
        saveCateogy.setName(request.getParameter("name"));
        try {
            if ( image != null) {
                saveImage(saveCateogy, image, request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categoryService.update(saveCateogy);
    }


    @GetMapping("/categories/{id}")
    public Category get(@PathVariable("id") int id)throws Exception{
        return categoryService.get(id);
    }


    @GetMapping("/categories")
    public List<Category> listCategory() throws Exception{
        return categoryService.listCategory();
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

    
/**
 * 将文件保存在服务器 /webapp/img/category 目录下
 * @param category
 * @param uploadedImage
 * @param request
 * @throws IOException
 */
    public void saveImage(Category category, MultipartFile uploadedImage, HttpServletRequest request)
            throws IOException{
        //  并没有新建文件！再查查 api
        File serverImageFolder = new File(request.getServletContext().getRealPath("img/category")); //..bug：无法创建 category 目录
        File serverImage = new File(serverImageFolder,category.getId()+".jpg"); //  对图片命名
        //  图片目录不存在，则创建该目录
        // !serverImageFolder.getParentFile().exists()  fix bug
        if (!serverImage.getParentFile().exists() ){
            serverImage.getParentFile().mkdirs();
        }
        //·将上传的图片，保存到服务器的指定位置："img/category"
        uploadedImage.transferTo(serverImage);
        //  此处的 serverImage 转移到了 img/category，导致后面执行的语句，出现 NullPointerException

        //  注释掉这两行，依然可以上传图片，即省略了图片转换步骤
        //  将服务器的图片，转换成 可渲染的 图片缓冲数据
        //  保证 ImageIO.write 转换处的 jpg 文件正常显示
        // BufferedImage renderPrototype = ImageUtil.reRenderImageBuffer(serverImage);
        // BufferedImage renderPrototype = ImageUtil.reRenderImageBuffer(serverImage);


        //  将服务器的图片，转换成jpg格式
        // ImageIO.write(renderPrototype,"jpg",serverImage);    
    }
}
