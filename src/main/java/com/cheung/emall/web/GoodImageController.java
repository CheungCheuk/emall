

package com.cheung.emall.web;

// import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.cheung.emall.pojo.Good;
import com.cheung.emall.pojo.GoodImage;
import com.cheung.emall.service.GoodImageService;
import com.cheung.emall.service.GoodService;
import com.cheung.emall.util.ImageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * GoodImageController
 */

@RestController
public class GoodImageController {
    @Autowired GoodImageService goodImageService;
    @Autowired GoodService goodService;

    @GetMapping("/goods/{good_id}/goodImages")
    public List<GoodImage> listImage(@PathVariable("good_id") int goodId, @RequestParam("type") String type) throws Exception{
        Good imageAssociatedGood = goodService.get(goodId); //  此处可能出现 bug，因为 jpa 命名策略的缘故

        if (GoodImageService.TYPE_SHRINK.equals(type)) {
            return goodImageService.findShrinkImage(imageAssociatedGood);
        } 
        else 
            if ( GoodImageService.TYPE_DETAIL.equals(type)) {
                return goodImageService.findDetailImage(imageAssociatedGood);
            }
            else{
                return new ArrayList<>();
            }
    }

    @PostMapping("/goodImages")
    public GoodImage addGoodImage(@RequestParam("good_id") int goodId, @RequestParam("type") String type, 
    MultipartFile uploadedImage, HttpServletRequest httpRequest)
    throws Exception{
        Good imageAssociatedGood = goodService.get(goodId);
        GoodImage requiredSavedImage = new GoodImage();
        requiredSavedImage.setGood(imageAssociatedGood);
        requiredSavedImage.setType(type);
        //  如果 goodImageService.add(requiredSavedImage); 不写在这里，先保存在数据库中，生成 id
        // 则 File savedImage = new File(imageSavedFolder,requiredSavedImage.getId()+".jpg"); 
        // 产生的文件id是0；
        GoodImage savedGoodImage = goodImageService.add(requiredSavedImage);   

        String folderLocation = "img/";
        if (GoodImageService.TYPE_SHRINK.equals(requiredSavedImage.getType())) {
            folderLocation += "goodShrink";
        }else if (GoodImageService.TYPE_DETAIL.equals(requiredSavedImage.getType())){
            folderLocation += "goodDetail";
        }

        File imageSavedFolder = new File(httpRequest.getServletContext().getRealPath(folderLocation));
        File savedImage = new File(imageSavedFolder,requiredSavedImage.getId()+".jpg");
        

        if ( !savedImage.getParentFile().exists() ) {
            savedImage.getParentFile().mkdirs();
        }
        try {
            uploadedImage.transferTo(savedImage);
            // BufferedImage reRenderImage = ImageUtil.reRenderImageBuffer(savedImage);
            // ImageIO.write(reRenderImage, "jpg", savedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //  重新将缩略图保存为两种尺寸：small、middle
        if ( GoodImageService.TYPE_SHRINK.equals(requiredSavedImage.getType())) {
            String smallShrinkImageFolderName = httpRequest.getServletContext().getRealPath("img/goodShrink_small");
            String middleShrinkImageFolderName = httpRequest.getServletContext().getRealPath("img/goodShrink_middle");
            
            String savedImageName = savedImage.getName();
            File smallShrinkImage = new File(smallShrinkImageFolderName,savedImageName);
            File middleShrinkImage = new File(middleShrinkImageFolderName,savedImageName);

            smallShrinkImage.getParentFile().mkdirs();
            middleShrinkImage.getParentFile().mkdirs();

            ImageUtil.resizeImage(savedImage, 56, 56, smallShrinkImage);
            ImageUtil.resizeImage(savedImage, 217, 190, middleShrinkImage);
        }
        // return goodImageService.add(requiredSavedImage);
        return savedGoodImage;
    }

    @DeleteMapping("/goodImages/{id}")
    public void deleteImage( @PathVariable("id")int id, HttpServletRequest httpServletRequest)
    throws Exception{
        GoodImage reuqiredDeletedImage = goodImageService.get(id);
        
        String folderLocation = "img/";
        if (GoodImageService.TYPE_SHRINK.equals(reuqiredDeletedImage.getType())) {
            folderLocation += "goodShrink";
        }else if (GoodImageService.TYPE_DETAIL.equals(reuqiredDeletedImage.getType())){
            folderLocation += "goodDetail";
        }

        File imageFolder = new File(httpServletRequest.getServletContext().getRealPath(folderLocation));
        File deletedImage = new File(imageFolder,reuqiredDeletedImage.getId()+".jpg");
        deletedImage.delete();

        if ( GoodImageService.TYPE_SHRINK.equals(reuqiredDeletedImage.getType())) {
            String smallShrinkImageFolderName = httpServletRequest.getServletContext().getRealPath("img/goodShrink_small");
            String middleShrinkImageFolderName = httpServletRequest.getServletContext().getRealPath("img/goodShrink_middle");
            
            String deletedImageName = deletedImage.getName();
            File smallShrinkImage = new File(smallShrinkImageFolderName,deletedImageName);
            File middleShrinkImage = new File(middleShrinkImageFolderName,deletedImageName);

            smallShrinkImage.delete();
            middleShrinkImage.delete();
        }
        goodImageService.delete(id);
    }
}





