package top.jglo.hotel.test;


import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.enums.ImageFormat;
import com.arcsoft.face.toolkit.ImageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import top.jglo.hotel.model.FuEngine;
import top.jglo.hotel.model.FuUser;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.util.FaceEngineUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.arcsoft.face.toolkit.ImageFactory.getGrayData;
import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

@Service
public class FaceEngineTest22 {

    public FaceEngine faceEngine = new FaceEngine("/usr/local/lib/arcsoft2.2/");

    public static void main(String[] args) {

    }
    public  FaceEngineTest22(int i) {

    }
    public  FaceEngineTest22(){
        String appId = "7Dx94XkaRfbsuC7BfdPtApwjeUXjBHeh7TanYUDjAYgQ";
        String sdkKey = "4NJX6tXzizb3pitgdTU9FXc1xZKg5ejSjUDKz3QYQTpc";

//        FaceEngine faceEngine = new FaceEngine("/usr/local/lib/arcsoft2.2/");
//        FaceEngine faceEngine2 = new FaceEngine("/usr/local/lib/");
        //激活引擎
        int activeCode = faceEngine.activeOnline(appId, sdkKey);
        System.out.println(activeCode);
        if (activeCode != ErrorInfo.MOK.getValue() && activeCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_90_ONLY);

        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();

        //获取年龄信息
        functionConfiguration.setSupportAge(true);
        //获取人脸三维角度信息
        functionConfiguration.setSupportFace3dAngle(true);
        //
        functionConfiguration.setSupportFaceDetect(true);
        //
        functionConfiguration.setSupportFaceRecognition(true);
        //获取性别信息
        functionConfiguration.setSupportGender(true);
        //获取新的RGB活体信息对象
        functionConfiguration.setSupportLiveness(true);
        //获取新的IR活体信息对象
        functionConfiguration.setSupportIRLiveness(true);

        engineConfiguration.setFunctionConfiguration(functionConfiguration);


        //初始化引擎
        int initCode = faceEngine.init(engineConfiguration);
        System.out.println(initCode);
        if (initCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }


        //人脸检测
        ImageInfo imageInfo = getRGBData(new File("/usr/share/nginx/image/HotelFuFu/test/DEED1C122F9DCF9878CD0193CE723826.jpg"));
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        System.out.println(imageInfo.getImageData());
        System.out.println(imageInfo.getWidth());
        System.out.println(imageInfo.getHeight());
        System.out.println();
        int detectCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth()
                , imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);
        System.out.println(detectCode);
        System.out.println(faceInfoList);

        //特征提取
        FaceFeature faceFeature = new FaceFeature();
        int extractCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth()
                , imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList.get(0), faceFeature);
        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);

        //人脸检测2
        ImageInfo imageInfo2 = getRGBData(new File("/usr/share/nginx/image/HotelFuFu/test/DEED1C122F9DCF9878CD0193CE723826.jpg"));
        List<FaceInfo> faceInfoList2 = new ArrayList<FaceInfo>();
        int detectCode2 = faceEngine.detectFaces(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList2);
        System.out.println(faceInfoList);

        //特征提取2
        FaceFeature faceFeature2 = new FaceFeature();
        int extractCode2 = faceEngine.extractFaceFeature(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList2.get(0), faceFeature2);
        System.out.println("特征值大小：" + faceFeature2.getFeatureData().length);

        //特征比对
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
        FaceSimilar faceSimilar = new FaceSimilar();
        int compareCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
        System.out.println("相似度：" + faceSimilar.getScore());


        //人脸属性检测
        FunctionConfiguration configuration = new FunctionConfiguration();
        //年龄
        configuration.setSupportAge(true);
        //角度
        configuration.setSupportFace3dAngle(true);
        //性别
        configuration.setSupportGender(true);
        //活体
        configuration.setSupportLiveness(true);
        int processCode = faceEngine.process(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList, configuration);
        System.out.println("processCode："+processCode );


        //性别检测
        List<GenderInfo> genderInfoList = new ArrayList<GenderInfo>();
        int genderCode = faceEngine.getGender(genderInfoList);
        System.out.println("genderCode："+genderCode );

        //        assertEquals("性别检测失败", genderCode, ErrorInfo.MOK.getValue());
        System.out.println("性别：" + (genderInfoList.get(0).getGender() == 0 ? "男" : "女"));

        //年龄检测
        List<AgeInfo> ageInfoList = new ArrayList<AgeInfo>();
        int ageCode = faceEngine.getAge(ageInfoList);
        System.out.println("ageCode："+ageCode );

        //        assertEquals("年龄检测失败", ageCode, ErrorInfo.MOK.getValue());
        System.out.println("年龄：" + ageInfoList.get(0).getAge());

        //3D信息检测
        List<Face3DAngle> face3DAngleList = new ArrayList<Face3DAngle>();
        int face3dCode = faceEngine.getFace3DAngle(face3DAngleList);
        System.out.println("face3dCode："+face3dCode );

        System.out.println("3D角度：" + face3DAngleList.get(0).getPitch() + "," + face3DAngleList.get(0).getRoll() + "," + face3DAngleList.get(0).getYaw());

//        //活体检测
//        List<LivenessInfo> livenessInfoList = new ArrayList<LivenessInfo>();
//        int livenessCode = faceEngine.getLiveness(livenessInfoList);
//        System.out.println("livenessCode："+livenessCode );
//
//        //RGB活体值，未知=-1 、非活体=0 、活体=1、超出人脸=2
//        System.out.println("活体：" + livenessInfoList.get(0).getLiveness()=="1"?"活体":"其它");
//
//
//        //IR属性处理
//        ImageInfo imageInfoGray = getGrayData(new File("/usr/share/nginx/image/HotelFuFu/test/CD000205C1740C4235ECD97E3CDBCCFA.jpg"));
//        List<FaceInfo> faceInfoListGray = new ArrayList<FaceInfo>();
//        int detectCodeGray = faceEngine.detectFaces(imageInfoGray.getImageData(), imageInfoGray.getWidth(), imageInfoGray.getHeight(), ImageFormat.CP_PAF_GRAY, faceInfoListGray);
//        System.out.println("detectCodeGray："+detectCodeGray );
//
//
//        FunctionConfiguration configuration2 = new FunctionConfiguration();
//        configuration2.setSupportIRLiveness(true);
//        int processCode2 = faceEngine.processIr(imageInfoGray.getImageData(), imageInfoGray.getWidth(), imageInfoGray.getHeight(), ImageFormat.CP_PAF_GRAY, faceInfoListGray, configuration2);
//        System.out.println("detectCodeGray："+processCode2 );
//
//        //IR活体检测
//        List<IrLivenessInfo> irLivenessInfo = new ArrayList<>();
//        int livenessIr = faceEngine.getLivenessIr(irLivenessInfo);
//        System.out.println("livenessIr："+livenessIr );
//
//        System.out.println("IR活体：" + irLivenessInfo.get(0).getLiveness());
//
//
//        //设置活体检测参数
//        int paramCode = faceEngine.setLivenessParam(0.8f, 0.8f);
//
//
//        //获取激活文件信息
//        ActiveFileInfo activeFileInfo = new ActiveFileInfo();
//        int activeFileCode = faceEngine.getActiveFileInfo(activeFileInfo);

//        引擎卸载
//        int unInitCode = faceEngine.unInit();
//        System.out.println(unInitCode);
//        System.out.println("卸载");
    }
    public synchronized ServerResult test3(String url, FuUser fuUser,String src) throws Exception {
        ServerResult result = new ServerResult();
        FaceEngineUtil faceEngineUtil = new FaceEngineUtil();
//        System.out.println("使用引擎：" + src);
//        //引擎配置
//        EngineConfiguration engineConfiguration = new EngineConfiguration();
//        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
//        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_90_ONLY);
//
//        //功能配置
//        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
//        functionConfiguration.setSupportAge(true);//获取年龄信息
//        functionConfiguration.setSupportFace3dAngle(true);//获取人脸三维角度信息
//        functionConfiguration.setSupportFaceDetect(true);
//        functionConfiguration.setSupportFaceRecognition(true);
//        functionConfiguration.setSupportGender(true);//获取性别信息
//        functionConfiguration.setSupportLiveness(true);//获取新的RGB活体信息对象
//        functionConfiguration.setSupportIRLiveness(true);//获取新的IR活体信息对象
//        engineConfiguration.setFunctionConfiguration(functionConfiguration);
//        //初始化引擎
//        System.out.println("初始化引擎！！！！！！！！！！！！！！！！！！！！！！！！");
//        int initCode = faceEngine.init(engineConfiguration);
//        System.out.println(initCode);
//        if (initCode != ErrorInfo.MOK.getValue()) {
//            System.out.println("初始化引擎失败" + initCode);
//        }
        File file1 = faceEngineUtil.newImgFile(url);
        ImageInfo imageInfo = getRGBData(file1);
        file1.delete();
        //人脸检测
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        int detectCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);
        System.out.println(detectCode);
        System.out.println(faceInfoList);
        //特征提取
        FaceFeature faceFeature = new FaceFeature();
        int extractCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList.get(0), faceFeature);
        System.out.println(extractCode);
        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);

        faceFeature.setFeatureData(faceFeature.getFeatureData());

        FaceFeature faceFeature2 = new FaceFeature();
        byte[] target = fuUser.getFaceDetail();
        faceFeature2.setFeatureData(target);
        FaceSimilar faceSimilar = new FaceSimilar();
        faceEngine.compareFaceFeature(faceFeature, faceFeature2, faceSimilar);
        String similar = faceSimilar.toString();
        System.out.println(similar);
        result.setMessage(similar);

//        int unInitCode = faceEngine.unInit();
//        System.out.println("卸载" + unInitCode);
        return result;
    }

    public synchronized String faceSimilar(int i){
        System.out.println(i);
//        String appId = "7Dx94XkaRfbsuC7BfdPtApwjeUXjBHeh7TanYUDjAYgQ";
//        String sdkKey = "4NJX6tXzizb3pitgdTU9FXc1xZKg5ejSjUDKz3QYQTpc";

        FaceEngine faceEngine = new FaceEngine("/usr/local/lib/arcsoft2.2/");
//        FaceEngine faceEngine2 = new FaceEngine("/usr/local/lib/");
//        //激活引擎
//        int activeCode = faceEngine.activeOnline(appId, sdkKey);
//        System.out.println(activeCode);
//        if (activeCode != ErrorInfo.MOK.getValue() && activeCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
//            System.out.println("引擎激活失败");
//        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_90_ONLY);

        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();

        //获取年龄信息
        functionConfiguration.setSupportAge(true);
        //获取人脸三维角度信息
        functionConfiguration.setSupportFace3dAngle(true);
        //
        functionConfiguration.setSupportFaceDetect(true);
        //
        functionConfiguration.setSupportFaceRecognition(true);
        //获取性别信息
        functionConfiguration.setSupportGender(true);
        //获取新的RGB活体信息对象
        functionConfiguration.setSupportLiveness(true);
        //获取新的IR活体信息对象
        functionConfiguration.setSupportIRLiveness(true);

        engineConfiguration.setFunctionConfiguration(functionConfiguration);


        //初始化引擎
        int initCode = faceEngine.init(engineConfiguration);
        System.out.println(initCode);
        if (initCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }


        //人脸检测
        ImageInfo imageInfo = getRGBData(new File("/usr/share/nginx/image/HotelFuFu/test/DEED1C122F9DCF9878CD0193CE723826.jpg"));
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        System.out.println(imageInfo.getImageData());
        System.out.println(imageInfo.getWidth());
        System.out.println(imageInfo.getHeight());
        System.out.println();
        int detectCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth()
                , imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);
        System.out.println(detectCode);
        System.out.println(faceInfoList);

        //特征提取
        FaceFeature faceFeature = new FaceFeature();
        int extractCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth()
                , imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList.get(0), faceFeature);
        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);

        //人脸检测2
        ImageInfo imageInfo2 = getRGBData(new File("/usr/share/nginx/image/HotelFuFu/test/CD000205C1740C4235ECD97E3CDBCCFA.jpg"));
        List<FaceInfo> faceInfoList2 = new ArrayList<FaceInfo>();
        int detectCode2 = faceEngine.detectFaces(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList2);
        System.out.println(faceInfoList);

        //特征提取2
        FaceFeature faceFeature2 = new FaceFeature();
        int extractCode2 = faceEngine.extractFaceFeature(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList2.get(0), faceFeature2);
        System.out.println("特征值大小：" + faceFeature2.getFeatureData().length);

        //特征比对
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
        FaceSimilar faceSimilar = new FaceSimilar();
        int compareCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
        System.out.println("相似度：" + faceSimilar.getScore());


        //人脸属性检测
        FunctionConfiguration configuration = new FunctionConfiguration();
        //年龄
        configuration.setSupportAge(true);
        //角度
        configuration.setSupportFace3dAngle(true);
        //性别
        configuration.setSupportGender(true);
        //活体
        configuration.setSupportLiveness(true);
        int processCode = faceEngine.process(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList, configuration);
        System.out.println("processCode："+processCode );


        //性别检测
        List<GenderInfo> genderInfoList = new ArrayList<GenderInfo>();
        int genderCode = faceEngine.getGender(genderInfoList);
        System.out.println("genderCode："+genderCode );

        //        assertEquals("性别检测失败", genderCode, ErrorInfo.MOK.getValue());
        System.out.println("性别：" + (genderInfoList.get(0).getGender() == 0 ? "男" : "女"));

        //年龄检测
        List<AgeInfo> ageInfoList = new ArrayList<AgeInfo>();
        int ageCode = faceEngine.getAge(ageInfoList);
        System.out.println("ageCode："+ageCode );

        //        assertEquals("年龄检测失败", ageCode, ErrorInfo.MOK.getValue());
        System.out.println("年龄：" + ageInfoList.get(0).getAge());

        //3D信息检测
        List<Face3DAngle> face3DAngleList = new ArrayList<Face3DAngle>();
        int face3dCode = faceEngine.getFace3DAngle(face3DAngleList);
        System.out.println("face3dCode："+face3dCode );

        System.out.println("3D角度：" + face3DAngleList.get(0).getPitch() + "," + face3DAngleList.get(0).getRoll() + "," + face3DAngleList.get(0).getYaw());

//        //活体检测
//        List<LivenessInfo> livenessInfoList = new ArrayList<LivenessInfo>();
//        int livenessCode = faceEngine.getLiveness(livenessInfoList);
//        System.out.println("livenessCode："+livenessCode );
//
//        //RGB活体值，未知=-1 、非活体=0 、活体=1、超出人脸=2
//        System.out.println("活体：" + livenessInfoList.get(0).getLiveness()=="1"?"活体":"其它");
//
//
//        //IR属性处理
//        ImageInfo imageInfoGray = getGrayData(new File("/usr/share/nginx/image/HotelFuFu/test/CD000205C1740C4235ECD97E3CDBCCFA.jpg"));
//        List<FaceInfo> faceInfoListGray = new ArrayList<FaceInfo>();
//        int detectCodeGray = faceEngine.detectFaces(imageInfoGray.getImageData(), imageInfoGray.getWidth(), imageInfoGray.getHeight(), ImageFormat.CP_PAF_GRAY, faceInfoListGray);
//        System.out.println("detectCodeGray："+detectCodeGray );
//
//
//        FunctionConfiguration configuration2 = new FunctionConfiguration();
//        configuration2.setSupportIRLiveness(true);
//        int processCode2 = faceEngine.processIr(imageInfoGray.getImageData(), imageInfoGray.getWidth(), imageInfoGray.getHeight(), ImageFormat.CP_PAF_GRAY, faceInfoListGray, configuration2);
//        System.out.println("detectCodeGray："+processCode2 );
//
//        //IR活体检测
//        List<IrLivenessInfo> irLivenessInfo = new ArrayList<>();
//        int livenessIr = faceEngine.getLivenessIr(irLivenessInfo);
//        System.out.println("livenessIr："+livenessIr );
//
//        System.out.println("IR活体：" + irLivenessInfo.get(0).getLiveness());
//
//
//        //设置活体检测参数
//        int paramCode = faceEngine.setLivenessParam(0.8f, 0.8f);
//
//
//        //获取激活文件信息
//        ActiveFileInfo activeFileInfo = new ActiveFileInfo();
//        int activeFileCode = faceEngine.getActiveFileInfo(activeFileInfo);

//        引擎卸载
        int unInitCode = faceEngine.unInit();
        System.out.println(unInitCode);
        System.out.println("卸载");

        return "相似度：" + faceSimilar.getScore();
    }
}
