package com.cheung.emall.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class ImageUtil {
    /**
     * 将指定文件转换为 jpg 格式
     * 原理：先读取指定服务器上的图片，获得图片的像素数组，保存为 DataBuffer 类型的一维数组，
     * 再用 WritableRaster 将 DataBuffer 的像素信息，转换为原图片的二维像素矩阵信息（长度、宽度），
     * 然后构造ColorMode：包含转码后的图片模型信息（例如rgb和不透明度），即根据这个模型来渲染像素。
     * 最后，BufferedImage 根据 ColorMode 构造的图片信息模版，以及图片的像素数组 WritableRaster，
     * 构造出一个新的 可渲染的 图片缓冲数据
     *
     * @param serverImage 保存在服务器上的图片
     * @return BufferedImage：jpgImage
     * @throws InterruptedException
     */
//    public static BufferedImage change2jpg(File f) {
//        try {
//            Image i = Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());
//            PixelGrabber pg = new PixelGrabber(i, 0, 0, -1, -1, true);
//            pg.grabPixels();
//            int width = pg.getWidth(), height = pg.getHeight();
//            final int[] RGB_MASKS = { 0xFF0000, 0xFF00, 0xFF };
//            final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
//            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), pg.getWidth() * pg.getHeight());
//            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
//            BufferedImage img = new BufferedImage(RGB_OPAQUE, raster, false, null);
//            return img;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    public static BufferedImage reRenderImageBuffer(File serverImage){
        try {
            //  获取 image，其像素数据来自指定的文件
            Image image = Toolkit.getDefaultToolkit().createImage(serverImage.getAbsolutePath());
            //  从 image 获取它的像素，位置：x=0，y=0，
            // PixelGrabber pixelGrabber = new PixelGrabber(image,0,0,-1,-1,true);
            PixelGrabber pixelGrabber = new PixelGrabber(image,0,0,1,1,true);
            pixelGrabber.getPixels();   //  获得一个图像像素的数组（字节型或整型）？？
            
            int width = pixelGrabber.getWidth();    //  图像宽度
            int height = pixelGrabber.getHeight();  //  图像高度

            final int[] RGB_MASKS = { 0xFF0000, 0xFF00, 0xFF};  //  红：0xFF0000，绿：0x00FF00，蓝：0x00FF00
            //  ColorModel ：图片参数：转换格式后的图片参数（颜色深度、rgb值）
            //  DirectColorModel：alpha=1，opaque不透明
            final ColorModel unsignedImage = new DirectColorModel(32,RGB_MASKS[0],RGB_MASKS[1],RGB_MASKS[2]);  //
            //  DataBuffer：将像素保存为一维数组
            DataBuffer dataBuffer = new DataBufferInt((int[]) pixelGrabber.getPixels(),
                    pixelGrabber.getWidth() * pixelGrabber.getHeight());


            //  Raster：保存 DataBuffer 像素的矩阵数组
            //  WritableRaster：将 Raster 的像素矩阵变为可读写的
            //  createPackedRaster：创建一个 Raster

            WritableRaster writableRaster = Raster.createPackedRaster(dataBuffer, width, height, width, RGB_MASKS,null);    
            //  java.lang.IllegalArgumentException: Width (-1) and height (-1) must be > 0

            //  转码后的图片
            BufferedImage imagePrototype = new BufferedImage(unsignedImage, writableRaster,false,null);

            return imagePrototype;
        }
        catch (Exception  e){    //  InterruptedException is never thrown in corresponding try block
            e.printStackTrace();
            return null;
        }
    }

    public static void resizeImage(File srcFile, int width, int height, File targetFile){
        try {
            if ( ! targetFile.getParentFile().exists()){
                targetFile.getParentFile().mkdirs();
            }

            Image srcImage = ImageIO.read(srcFile);
            srcImage = resizeMethod(srcImage, width, height);
            ImageIO.write((RenderedImage)srcImage, "jpg", targetFile);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Image resizeMethod(Image srcImage, int width, int height){
        try {
            BufferedImage resizeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            resizeImage
                    .getGraphics()
                    .drawImage(
                            srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH),
                            0,
                            0,
                            null);
            return resizeImage;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
