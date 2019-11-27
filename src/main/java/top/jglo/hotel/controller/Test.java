package top.jglo.hotel.controller;

import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FaceSimilar;
import com.arcsoft.face.enums.ImageFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.jglo.hotel.example.FaceEngineTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Api(tags = "测试")
@CrossOrigin
@Controller
@RequestMapping(value = {"test"})
public class Test {

    @Autowired
    FaceEngineTest  faceEngineTest;

    @PostMapping(value = {"test1"})
    @ApiOperation(value = "测试1", notes = "测试FaceEngine", produces = "人脸识别")
    @ResponseBody
    public String test1() throws Exception{
//           方法一
//        Thread a = new Thread(){
//            @Override
//            public void run() {
//                String s;
//                s=faceEngineTest.play();
//            }
//        };
//        a.start();
//        String s=a.toString();
//        return s;

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<String> result = executorService.submit( new Callable<String>(){
            public String call(){
                return faceEngineTest.play();
            }
        } );
        System.out.println("aaaaaaaaaaaaa"+result.get());
        return "a";
    }
}
