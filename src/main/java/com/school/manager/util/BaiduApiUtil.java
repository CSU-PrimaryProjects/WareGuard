package com.school.manager.util;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Objects;

@Component
public class BaiduApiUtil {



    public String appId = "16904942";

    public String appKey = "QjC10QUSNAwGHluK8pKGA46y";

    public String secretKey = "L0ki80kDNPeH3PA1Ue3l8qSL1ri2oiLr";

    public String groupId = "user";

    private AipFace client;

    public static final String IMG_TYPE_URL = "URL";
    public static final String IMG_TYPE_BASE64 = "BASE64";

    @PostConstruct
    public void init() {
        client = new AipFace(appId, appKey, secretKey);
    }

    public JSONObject addGroup(String groupId) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        // 创建用户组
        return client.groupAdd(groupId, options);
    }

    public JSONObject getGroups(Integer start, Integer length) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("start", start.toString());
        options.put("length", length.toString());
        // 组列表查询
        return client.getGroupList(options);
    }

    public JSONObject register(String userInfo, String imageUrl, String imageType, String userId, String groupId) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("user_info", userInfo);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("action_type", "REPLACE");
        String image = imageUrl;
        if (Objects.isNull(groupId)) {
            groupId = groupId;
        }
        // 人脸注册
        return client.addUser(image, imageType, groupId, userId, options);
    }

    public JSONObject deleteFace(String userId, String groupId ,String faceToken) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
       /* options.put("user_info", userId);
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
        options.put("action_type", "REPLACE");*/
        if (Objects.isNull(groupId)) {
            groupId = groupId;
        }
        JSONObject jsonObject = client.deleteUser(groupId, userId, options);
        JSONObject user = client.getGroupUsers(groupId, options);
        System.out.println(jsonObject.toString());
        // 人脸删除
        return client.faceDelete(userId,groupId,faceToken,options);
    }

    public JSONObject getUsers(Integer start, Integer length, String groupId) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("start", start.toString());
        options.put("length", length.toString());
        // 获取用户列表
        return client.getGroupUsers(groupId, options);
    }

    public JSONObject getUserInfo(String userId, String groupId) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        // 用户信息查询
        return client.getUser(userId, groupId, options);
    }

    public JSONObject search(String imageUrl) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("max_face_num", "3");
        options.put("match_threshold", "70");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
//        options.put("user_id", "12345678");
        options.put("max_user_num", "1");

//        String image = "http://forrily.com/linym.jpg";
        String imageType = "URL";
        String groupIdList = groupId;

        // 人脸搜索
        return client.search(imageUrl, imageType, groupIdList, options);

    }

    public JSONObject searchBase64(String img) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<>();
        options.put("max_face_num", "3");
        options.put("match_threshold", "70");
        options.put("quality_control", "NORMAL");
        options.put("liveness_control", "LOW");
//        options.put("user_id", "12345678");
        options.put("max_user_num", "1");

//        String image = "http://forrily.com/linym.jpg";
        String groupIdList = groupId;

        // 人脸搜索
        return client.search(img, IMG_TYPE_BASE64, groupIdList, options);

    }
}
