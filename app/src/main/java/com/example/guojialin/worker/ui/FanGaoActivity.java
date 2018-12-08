package com.example.guojialin.worker.ui;

import android.app.Activity;
import android.os.Bundle;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.bean.AiDiscern;
import com.example.guojialin.worker.bean.PicDiscernBean;
import com.example.guojialin.worker.view.CustomView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class FanGaoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_gao);
        CustomView customView = findViewById(R.id.ai_discern);
        String jsonStr = "{\"data\":{\"imgId\":\"99495ed6-faa8-11e8-b6d3-00163e2e4de6\",\"state\":0,\"text\":\"The Task Success!\",\"imgWidth\":1488,\"imgHeight\":1984,\"imgUrl\":\"http:\\/\\/haoweilai-tob-video.oss-cn-beijing.aliyuncs.com\\/img\\/homework-student\\/dev\\/1544241355910test.jpg\",\"direction\":0,\"startTime\":1544246266.8512394,\"endTime\":1544246267.346136,\"questionImgs\":[{\"quesId\":\"999cfd5c-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":1118,\"topY\":391,\"questionWidth\":161,\"questionHeight\":39,\"questionContext\":\"560\\u00f714=\",\"ansResult\":0},{\"quesId\":\"999cff82-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":1098,\"topY\":240,\"questionWidth\":261,\"questionHeight\":39,\"questionContext\":\"63\\u00f77\\u00d763\\u00f77=\",\"ansResult\":0},{\"quesId\":\"999d0086-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":86,\"topY\":273,\"questionWidth\":181,\"questionHeight\":44,\"questionContext\":\"426+75=\",\"ansResult\":0},{\"quesId\":\"999d016c-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":86,\"topY\":350,\"questionWidth\":181,\"questionHeight\":41,\"questionContext\":\"328-198=\",\"ansResult\":0},{\"quesId\":\"999d0248-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":1118,\"topY\":465,\"questionWidth\":141,\"questionHeight\":34,\"questionContext\":\"330\\u00d75=\",\"ansResult\":0},{\"quesId\":\"999d0324-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":582,\"topY\":696,\"questionWidth\":162,\"questionHeight\":40,\"questionContext\":\"29\\u00d74\\u00d725\",\"ansResult\":0},{\"quesId\":\"999d03f6-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":86,\"topY\":711,\"questionWidth\":162,\"questionHeight\":48,\"questionContext\":\"657-298\",\"ansResult\":0},{\"quesId\":\"999d0554-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":424,\"topY\":266,\"questionWidth\":161,\"questionHeight\":36,\"questionContext\":\"243\\u00f73=\",\"ansResult\":0},{\"quesId\":\"999d063a-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":424,\"topY\":202,\"questionWidth\":121,\"questionHeight\":36,\"questionContext\":\"7\\u00d76=\",\"ansResult\":0},{\"quesId\":\"999d0716-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":761,\"topY\":187,\"questionWidth\":181,\"questionHeight\":39,\"questionContext\":\"445+544=\",\"ansResult\":0},{\"quesId\":\"999d07e8-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":86,\"topY\":428,\"questionWidth\":162,\"questionHeight\":43,\"questionContext\":\"1\\u00d726\\u00d71=\",\"ansResult\":0},{\"quesId\":\"999d08ce-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":86,\"topY\":1059,\"questionWidth\":261,\"questionHeight\":51,\"questionContext\":\"873+284-273\",\"ansResult\":0},{\"quesId\":\"999d09a0-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":1098,\"topY\":1015,\"questionWidth\":161,\"questionHeight\":59,\"questionContext\":\"1001\\u00f777\",\"ansResult\":0},{\"quesId\":\"999d0a72-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":781,\"topY\":474,\"questionWidth\":161,\"questionHeight\":39,\"questionContext\":\"153\\u00f751=\",\"ansResult\":0},{\"quesId\":\"999d0b4e-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":1098,\"topY\":179,\"questionWidth\":142,\"questionHeight\":34,\"questionContext\":\"28+49=\",\"ansResult\":0},{\"quesId\":\"999d0c20-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":424,\"topY\":339,\"questionWidth\":161,\"questionHeight\":37,\"questionContext\":\"55+44=\",\"ansResult\":0},{\"quesId\":\"999d0cf2-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":761,\"topY\":255,\"questionWidth\":161,\"questionHeight\":36,\"questionContext\":\"27\\u00d730=\",\"ansResult\":0},{\"quesId\":\"999d0dc4-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":1058,\"topY\":663,\"questionWidth\":360,\"questionHeight\":53,\"questionContext\":\"111111111\\u00f712345679\",\"ansResult\":0},{\"quesId\":\"999d0e96-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":602,\"topY\":1036,\"questionWidth\":201,\"questionHeight\":57,\"questionContext\":\"157-100\\u00f74\",\"ansResult\":0},{\"quesId\":\"999d0f68-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":424,\"topY\":414,\"questionWidth\":181,\"questionHeight\":40,\"questionContext\":\"111-77=\",\"ansResult\":0},{\"quesId\":\"999d1030-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":1118,\"topY\":1520,\"questionWidth\":340,\"questionHeight\":57,\"questionContext\":\"150\\u00d7(200-100\\u00d72)\",\"ansResult\":0},{\"quesId\":\"999d110c-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":761,\"topY\":325,\"questionWidth\":201,\"questionHeight\":36,\"questionContext\":\"452+111=\",\"ansResult\":0},{\"quesId\":\"999d11d4-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":1098,\"topY\":315,\"questionWidth\":241,\"questionHeight\":38,\"questionContext\":\"30\\u00d7(35-20)=\",\"ansResult\":0},{\"quesId\":\"999d12a6-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":761,\"topY\":402,\"questionWidth\":161,\"questionHeight\":36,\"questionContext\":\"80\\u00f715=\",\"ansResult\":0},{\"quesId\":\"999d1378-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":66,\"topY\":207,\"questionWidth\":182,\"questionHeight\":44,\"questionContext\":\"153-97=\",\"ansResult\":0},{\"quesId\":\"999d144a-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":424,\"topY\":489,\"questionWidth\":181,\"questionHeight\":39,\"questionContext\":\"159-95=\",\"ansResult\":0},{\"quesId\":\"999d1526-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":602,\"topY\":1548,\"questionWidth\":340,\"questionHeight\":65,\"questionContext\":\"64\\u00f7(742-33\\u00d722)\",\"ansResult\":0},{\"quesId\":\"999d1648-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":66,\"topY\":501,\"questionWidth\":142,\"questionHeight\":42,\"questionContext\":\"18\\u00d75=\",\"ansResult\":0},{\"quesId\":\"999d171a-faa8-11e8-b6d3-00163e2e4de6\",\"leftX\":66,\"topY\":1566,\"questionWidth\":360,\"questionHeight\":69,\"questionContext\":\"(36+280\\u00f770)\\u00d727\",\"ansResult\":0}]},\"status_code\":200,\"meta\":{\"msg\":\"success\",\"code\":0,\"timestamp\":1544246267.358557,\"response_time\":0.6869919300079346}}";
        PicDiscernBean picDiscernBean = (PicDiscernBean) new Gson().fromJson(jsonStr,PicDiscernBean.class) ;
//        ArrayList<AiDiscern> aiDiscerns = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            AiDiscern aiDiscern = new AiDiscern();
//            aiDiscern.setLeftX(100 + i * 100 );
//            aiDiscern.setTopY(100 + i * 100);
//            aiDiscern.setQuestionHeight(80);
//            aiDiscern.setQuestionWidth(200);
//            aiDiscern.setAnsResult(false);
//            aiDiscerns.add(aiDiscern);
//        }
        customView.updateCanvasData(picDiscernBean);
    }
}
