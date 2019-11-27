package top.jglo.hotel.controller;


import com.arcsoft.face.*;
import com.arcsoft.face.enums.ImageFormat;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.model.FuEngine;
import top.jglo.hotel.model.FuUser;
import top.jglo.hotel.model.result.ServerResult;
import top.jglo.hotel.repository.FuEngineRepository;
import top.jglo.hotel.repository.FuUserRepository;
import top.jglo.hotel.test.FaceEngineTest22;
import top.jglo.hotel.util.BinaryConversion;
import top.jglo.hotel.util.FaceEngineUtil;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

//import com.arcsoft.face.toolkit.ImageInfo;

//import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

/**
 * @author gkirito
 */

@Api(tags = "事例2")
@CrossOrigin
@Controller
@RequestMapping(value = {""})
@Scope(name = "test", description = "session")
public class Test22Controller {

    @Autowired
    private FuUserRepository fuUserRepository;
    @Autowired
    private FuEngineRepository fuEngineRepository;
    @Autowired
    private FaceEngineTest22 faceEngineTest;

//    //新建引擎
//    private FaceEngine faceEngine ;
//
//    @PostConstruct
//    public void init(){
//        faceEngine = new FaceEngine();
//        //激活引擎
//        faceEngine.active(FaceEngineUtil.appId, FaceEngineUtil.sdkKey);
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
//    }


    @PostMapping(value = {"test1"})
    @ApiOperation(value = "测试1", notes = "采用RequestParam的形式", produces = "application/json,application/xml")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "faceDetail", value = "特征值", required = true, dataType = "String", paramType = "query")
    })
    @ResponseBody
    public ServerResult test1(@RequestParam String faceDetail) {
        FaceEngineUtil faceEngineUtil = new FaceEngineUtil();

        ServerResult result=new ServerResult();
        FaceFeature faceFeature = new FaceFeature();
        faceFeature.setFeatureData(BinaryConversion.parseHexStr2Byte(faceDetail));
        FaceFeature faceFeature2 = new FaceFeature();
        faceFeature2.setFeatureData(fuUserRepository.findOne(1).getFaceDetail());
        FaceSimilar faceSimilar = new FaceSimilar();
        faceEngineUtil.faceEngine.compareFaceFeature(faceFeature, faceFeature2, faceSimilar);
        String similar=faceSimilar.toString();
        System.out.println(similar);
        result.setMessage(similar);
        faceEngineUtil.unInit();
        return result;
    }

    @PostMapping(value = {"test2"})
    @ApiOperation(value = "测试1", notes = "采用RequestParam的形式", produces = "application/json,application/xml")
    @ResponseBody
    public ServerResult test2() throws Exception {

        FaceEngineUtil faceEngineUtil = new FaceEngineUtil();

        ServerResult result=new ServerResult();
        FuUser fuUser=new FuUser();
        fuUser.setId(1);
        File file1= faceEngineUtil.newImgFile("https://jglo.top:8091/HotelFuFu/test/DEED1C122F9DCF9878CD0193CE723826.jpg");
        FaceEngineUtil.ImageInfo imageInfo = faceEngineUtil.getRGBData(file1);
        //人脸检测
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        faceEngineUtil.faceEngine.detectFaces(imageInfo.getRgbData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList);

        //提取人脸特征
        FaceFeature faceFeature = new FaceFeature();
        faceEngineUtil.faceEngine.extractFaceFeature(imageInfo.getRgbData(), imageInfo.getWidth(), imageInfo.getHeight(), ImageFormat.CP_PAF_BGR24, faceInfoList.get(0), faceFeature);

        fuUser.setFaceDetail(faceFeature.getFeatureData());
        FuUser fuUser2=fuUserRepository.save(fuUser);
        System.out.println(fuUser2.getFaceDetail());
        System.out.println(Arrays.toString(fuUser2.getFaceDetail()));
        faceEngineUtil.unInit();
        return result;
    }

    @PostMapping(value = {"test3"})
    @ApiOperation(value = "测试3", notes = "采用RequestParam的形式", produces = "application/json")
    @ResponseBody
    public ServerResult test3(@RequestParam String url,@RequestParam int id) throws Exception {
        ServerResult result=new ServerResult();
        //找一个引擎
        FuEngine fuEngine = fuEngineRepository.findByName("yes");
        FuEngine nextFuEngine = fuEngineRepository.findOne(fuEngine.getNextId());
        fuEngine.setName("no");
        nextFuEngine.setName("yes");
        fuEngineRepository.save(fuEngine);
        fuEngineRepository.save(nextFuEngine);
        FuUser fuUser=fuUserRepository.findById(id);
        faceEngineTest.test3(url,fuUser,fuEngine.getSrc());
        return result;
    }

    @PostMapping(value = {"test4"})
    @ApiOperation(value = "测试4", notes = "采用RequestParam的形式", produces = "application/json")
    @ResponseBody
    public ServerResult test4(@RequestParam int id)  {
        ServerResult result=new ServerResult();
        FuUser fuUser=fuUserRepository.findOne(1);
        System.out.println(fuUser.toString());
        result.setMessage(fuUser.toString());
        result.setData(fuUser);
        return result;
    }



    @PostMapping(value = {"test6"})
    @ApiOperation(value = "测试6", notes = "测试FaceEngine", produces = "人脸识别")
    @ResponseBody
    public ServerResult test6(@RequestParam String url,@RequestParam int id) throws Exception {
        ServerResult result=new ServerResult();

        FuUser fuUser=fuUserRepository.findById(id);

        ExecutorService executorService = new ThreadPoolExecutor(
                10, 10, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        Future<String> FutureResult = executorService.submit(new Callable<String>(){
            @Override
            public String call() throws Exception {
                return faceEngineTest.test3(url,fuUser,"").getMessage();
            }
        } );
        System.out.println("aaaaaaaaaaaaa"+FutureResult.get());
        result.setMessage(FutureResult.get());
        return result;
    }

}
