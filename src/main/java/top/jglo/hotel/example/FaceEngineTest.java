//package top.jglo.hotel.example;
//
//import com.arcsoft.face.*;
//import com.arcsoft.face.enums.ImageFormat;
//import org.springframework.stereotype.Service;
////import org.junit.Test;
//
//import javax.imageio.ImageIO;
//import java.awt.color.ColorSpace;
//import java.awt.image.BufferedImage;
//import java.awt.image.ColorConvertOp;
//import java.awt.image.DataBufferByte;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FaceEngineTest {
//
//    public  FaceEngine faceEngine;
//
//    public FaceEngineTest() {
//        String appId = "7Dx94XkaRfbsuC7BfdPtApwjeUXjBHeh7TanYUDjAYgQ";
//        String sdkKey = "4NJX6tXzizb3pitgdTU9FXc26ftq7iR9Qc4NrQar6riy";
//
//        faceEngine = new FaceEngine();
//        //激活引擎
//        faceEngine.active(appId, sdkKey);
//
//        EngineConfiguration engineConfiguration = EngineConfiguration.builder().functionConfiguration(
//                FunctionConfiguration.builder()
//                        .supportAge(true)
//                        .supportFace3dAngle(true)
//                        .supportFaceDetect(true)
//                        .supportFaceRecognition(true)
//                        .supportGender(true)
//                        .build()).build();
//        //初始化引擎
//        faceEngine.init(engineConfiguration);
//        System.out.println("初始化引擎！！！！！！！！！！！！！！！！！！！！！！！！");
//    }
//
//    public void aa(){
//
//        ImageInfo imageInfo = getRGBData(new File("src/1.jpg"));
//        ImageInfo imageInfo2 = getRGBData(new File("src/2.jpg"));
//        //人脸检测
//        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
//        faceEngine.detectFaces(imageInfo.getRgbData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);
//
//        //提取人脸特征
//        FaceFeature faceFeature = new FaceFeature();
//        faceEngine.extractFaceFeature(imageInfo.getRgbData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList.get(0), faceFeature);
////        System.out.println(faceFeature.getFeatureData());
////        System.out.println(Arrays.toString(faceFeature.getFeatureData()));
////        System.out.println(faceFeature.toString());
//
//
//        FaceFeature faceFeature2 = new FaceFeature();
//        faceEngine.extractFaceFeature(imageInfo2.getRgbData(), imageInfo2.getWidth(), imageInfo2.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList.get(0), faceFeature2);
////        System.out.println(faceFeature2.getFeatureData());
////        System.out.println(Arrays.toString(faceFeature2.getFeatureData()));
////        System.out.println(faceFeature2.toString());
//
////        FaceSimilar faceSimilar2 = new FaceSimilar();
////        FaceFeature faceFeature3 = new FaceFeature();
////        FaceFeature faceFeature4 = new FaceFeature();
////        faceFeature3.setFeatureData(faceFeature.getFeatureData());
////        faceFeature4.setFeatureData(faceFeature2.getFeatureData());
////
////        faceEngine.compareFaceFeature(faceFeature3, faceFeature4, faceSimilar2);
////        System.out.println(faceSimilar2.toString());
//
//
//        //人脸对比
//        FaceFeature targetFaceFeature = new FaceFeature();
//        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
////        System.out.println(targetFaceFeature.toString());
//
//        FaceFeature sourceFaceFeature = new FaceFeature();
//        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
////        System.out.println(sourceFaceFeature.toString());
//
//
//        FaceSimilar faceSimilar = new FaceSimilar();
//        faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
//        System.out.println(faceSimilar.toString());
//
////        int processResult = faceEngine.process(imageInfo2.getRgbData(), imageInfo2.getWidth(), imageInfo2.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList, FunctionConfiguration.builder().supportAge(true).supportFace3dAngle(true).supportGender(true).build());
////        System.out.println(processResult);
//
////        //性别提取
////        List<GenderInfo> genderInfoList = new ArrayList<GenderInfo>();
////        int genderCode = faceEngine.getGender(genderInfoList);
////        for (int i = 0; i < genderInfoList.size(); i++) {
////            System.out.println(genderInfoList.get(i).getGender());
////        }
////        System.out.println(genderCode);
////        //年龄提取
////        List<AgeInfo> ageInfoList = new ArrayList<AgeInfo>();
////        int ageCode = faceEngine.getAge(ageInfoList);
////        for (int i = 0; i < ageInfoList.size(); i++) {
////            System.out.println(ageInfoList.get(i).getAge());
////        }
////        System.out.println(ageCode);
////
////        //3D信息提取
////        List<Face3DAngle> face3DAngleList = new ArrayList<Face3DAngle>();
////        int face3dCode = faceEngine.getFace3DAngle(face3DAngleList);
////        for (int i = 0; i < face3DAngleList.size(); i++) {
////            System.out.println(face3DAngleList.get(i).toString());
////        }
////        System.out.println(face3dCode);
//    }
//
//
//    public  ImageInfo getRGBData(File file) {
//        if (file == null)
//            return null;
//        ImageInfo imageInfo;
//        try {
//            //将图片文件加载到内存缓冲区
//            BufferedImage image = ImageIO.read(file);
//            imageInfo = bufferedImage2ImageInfo(image);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return imageInfo;
//    }
//
//    private  ImageInfo bufferedImage2ImageInfo(BufferedImage image) {
//        ImageInfo imageInfo = new ImageInfo();
//        int width = image.getWidth();
//        int height = image.getHeight();
//        // 使图片居中
//        width = width & (~3);
//        height = height & (~3);
//        imageInfo.width = width;
//        imageInfo.height = height;
//        //根据原图片信息新建一个图片缓冲区
//        BufferedImage resultImage = new BufferedImage(width, height, image.getType());
//        //得到原图的rgb像素矩阵
//        int[] rgb = image.getRGB(0, 0, width, height, null, 0, width);
//        //将像素矩阵 绘制到新的图片缓冲区中
//        resultImage.setRGB(0, 0, width, height, rgb, 0, width);
//        //进行数据格式化为可用数据
//        BufferedImage dstImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
//        if (resultImage.getType() != BufferedImage.TYPE_3BYTE_BGR) {
//            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_LINEAR_RGB);
//            ColorConvertOp colorConvertOp = new ColorConvertOp(cs, dstImage.createGraphics().getRenderingHints());
//            colorConvertOp.filter(resultImage, dstImage);
//        } else {
//            dstImage = resultImage;
//        }
//
//        //获取rgb数据
//        imageInfo.rgbData = ((DataBufferByte) (dstImage.getRaster().getDataBuffer())).getData();
//        return imageInfo;
//    }
//
//
//    public  class ImageInfo {
//        public byte[] rgbData;
//        public int width;
//        public int height;
//
//        public byte[] getRgbData() {
//            return rgbData;
//        }
//
//        public void setRgbData(byte[] rgbData) {
//            this.rgbData = rgbData;
//        }
//
//        public int getWidth() {
//            return width;
//        }
//
//        public void setWidth(int width) {
//            this.width = width;
//        }
//
//        public int getHeight() {
//            return height;
//        }
//
//        public void setHeight(int height) {
//            this.height = height;
//        }
//    }
//
//}
