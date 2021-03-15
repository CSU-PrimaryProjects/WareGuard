package com.school.manager.util;

import java.util.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Img2Base64Util {
    /**
     * 将图片转换成Base64编码
     * @param imgFile 待处理图片
     * @return
     */
     public static String getImgStr(String imgFile){
         //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
          //读取图片字节数组
        try{
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return new String(Base64.getEncoder().encodeToString(data));
     }
}
