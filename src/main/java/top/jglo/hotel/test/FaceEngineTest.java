package top.jglo.hotel.test;


import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.enums.ImageFormat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class FaceEngineTest {


    public static void main(String[] args) {


    }
    public FaceEngineTest(){
        String appId = "7Dx94XkaRfbsuC7BfdPtApwjeUXjBHeh7TanYUDjAYgQ";
        String sdkKey = "4NJX6tXzizb3pitgdTU9FXc1xZKg5ejSjUDKz3QYQTpc";

        FaceEngine faceEngine = new FaceEngine();

        int unInitCode = faceEngine.unInit();
    }

}
