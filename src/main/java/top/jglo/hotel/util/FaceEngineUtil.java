package top.jglo.hotel.util;

import com.arcsoft.face.*;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.enums.ImageFormat;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class FaceEngineUtil {


    public static String appId = "7Dx94XkaRfbsuC7BfdPtApwjeUXjBHeh7TanYUDjAYgQ";
    public static String sdkKey = "4NJX6tXzizb3pitgdTU9FXc1xZKg5ejSjUDKz3QYQTpc";

    public FaceEngine faceEngine ;

    public FaceEngine getFaceEngine() {
        return faceEngine;
    }

    public void setFaceEngine(FaceEngine faceEngine) {
        this.faceEngine = faceEngine;
    }

    public FaceEngineUtil(){
//        faceEngine= new FaceEngine();
////        //激活引擎
////        faceEngine.active(appId, sdkKey);
//
//        EngineConfiguration engineConfiguration = EngineConfiguration.builder().functionConfiguration(
//                FunctionConfiguration.builder()
//                        .supportFaceDetect(true)
//                        .supportFaceRecognition(true)
//                        .build()).build();
//        //初始化引擎
//        faceEngine.init(engineConfiguration);
//
//        System.out.println("初始化引擎！！！！！！！！！！！！！！！！！！！！！！！！");
    }
    public FaceEngineUtil(boolean all){
        //激活引擎
        //2.2
//        faceEngine.activeOnline(appId, sdkKey);
        //2.0
        faceEngine.active(appId, sdkKey);

        EngineConfiguration engineConfiguration = EngineConfiguration.builder().functionConfiguration(
                FunctionConfiguration.builder()
                        .supportAge(true)
                        .supportFace3dAngle(true)
                        .supportFaceDetect(true)
                        .supportFaceRecognition(true)
                        .supportGender(true)
                        .build()).build();
        //初始化引擎
        faceEngine.init(engineConfiguration);
//        int initCode = faceEngine.init(engineConfiguration);
//        if (initCode != ErrorInfo.MOK.getValue()) {
//            System.out.println("初始化引擎失败"+initCode);
//        }
    }


    public File newImgFile(String urlString) throws Exception {
        String file="";
        //new一个URL对象
        URL url = new URL(urlString);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        //生成随机数
        String randomStr = UUID.randomUUID().toString().substring(0, 5);
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        File imageFile = new File("a" + randomStr + ".jpg");
        //创建输出流
        FileOutputStream outStream = new FileOutputStream(imageFile);
        //写入数据
        outStream.write(data);
        //关闭输出流
        outStream.close();
        return imageFile;
    }

    public  byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }


    public  ImageInfo getRGBData(File file) {
        if (file == null){
            return null;
        }
        ImageInfo imageInfo;
        try {
            //将图片文件加载到内存缓冲区
            BufferedImage image = ImageIO.read(file);
            imageInfo = (ImageInfo) bufferedImage2ImageInfo(image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return imageInfo;
    }

    private  ImageInfo bufferedImage2ImageInfo(BufferedImage image) {
        ImageInfo imageInfo = new ImageInfo();
        int width = image.getWidth();
        int height = image.getHeight();
        // 使图片居中
        width = width & (~3);
        height = height & (~3);
        imageInfo.width = width;
        imageInfo.height = height;
        //根据原图片信息新建一个图片缓冲区
        BufferedImage resultImage = new BufferedImage(width, height, image.getType());
        //得到原图的rgb像素矩阵
        int[] rgb = image.getRGB(0, 0, width, height, null, 0, width);
        //将像素矩阵 绘制到新的图片缓冲区中
        resultImage.setRGB(0, 0, width, height, rgb, 0, width);
        //进行数据格式化为可用数据
        BufferedImage dstImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        if (resultImage.getType() != BufferedImage.TYPE_3BYTE_BGR) {
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_LINEAR_RGB);
            ColorConvertOp colorConvertOp = new ColorConvertOp(cs, dstImage.createGraphics().getRenderingHints());
            colorConvertOp.filter(resultImage, dstImage);
        } else {
            dstImage = resultImage;
        }

        //获取rgb数据
        imageInfo.rgbData = ((DataBufferByte) (dstImage.getRaster().getDataBuffer())).getData();
        return imageInfo;
    }


    public  class ImageInfo {
        public byte[] rgbData;
        public int width;
        public int height;

        public byte[] getRgbData() {
            return rgbData;
        }

        public void setRgbData(byte[] rgbData) {
            this.rgbData = rgbData;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public double faceCompare(byte source[], byte target[]) {
        try{
            //人脸对比
            System.out.println("人脸对比");
            FaceFeature sourceFeature = new FaceFeature(source);
            FaceFeature targetFaceFeature = new FaceFeature(target);

            FaceSimilar faceSimilar = new FaceSimilar();
            faceEngine.compareFaceFeature(targetFaceFeature, sourceFeature, faceSimilar);

            System.out.println("人脸相似度: " + faceSimilar.getScore());
            return faceSimilar.getScore();
        }catch (Exception e){
            return -1;
        }

    }

    public void unInit(){
        this.faceEngine.unInit();
        System.out.println("注销");
    }

}
