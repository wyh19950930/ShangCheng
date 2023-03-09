package com.jymj.zhglxt.util;

import android.content.Intent;

import com.jymj.zhglxt.app.AppApplication;
import com.jymj.zhglxt.login.activity.LoginActivity;
import com.parse.codec.binary.Base64;
import com.setsuna.common.commonutils.ToastUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AesEncryptUtils {
    //可配置到Constant中，并读取配置文件注入,16位,自己定义
    private static final String KEY = "1478523698745432";

    //参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param content    加密的字符串
     * @param encryptKey key值
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String encryptKey) throws Exception {

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        byte[] b = cipher.doFinal(content.getBytes("utf-8"));
        // 采用base64算法进行转码,避免出现中文乱码
        return Base64.encodeBase64String(b);

    }

    /**
     * 解密
     *
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        /*if (encryptStr.contains("\"code\":401")){
            ToastUtils.showShort("用户授权信息已过期,请重新登录");

            Intent intentmain = new Intent(AppApplication.getAppContext(), LoginActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            AppApplication.getAppContext().startActivity(intentmain);
            return encryptStr;
        }*/
        if (encryptStr.contains("{")){
            return encryptStr;
        }
        /*if (encryptStr.contains("\"status\":400")){
            return "{\n" +
                    "  \"msg\" : \"\",\n" +
                    "  \"code\" : 400\n" +
                    "}";
        }
        if (encryptStr.contains("405 Not Allowed")){
            return "{\n" +
                    "  \"msg\" : \"\",\n" +
                    "  \"code\" : 405\n" +
                    "}";
        }
        if (encryptStr.contains("\"code\":500")){
            return "{\n" +
                    "  \"msg\" : \"\",\n" +
                    "  \"code\" : 500\n" +
                    "}";
        }*/

        /*if (encryptStr.contains("\"code\":415")){
            return "{\n" +
                    "  \"msg\" : \"\",\n" +
                    "  \"code\" : 415\n" +
                    "}";
        }
        if (encryptStr.contains("\"code\":0")){
            return encryptStr;
        }*/
        try{
            //可能发生错误的程式码
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes("GBK"), "AES"));
            // 采用base64算法进行转码,避免出现中文乱码
            byte[] encryptBytes = Base64.decodeBase64(encryptStr);
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            return new String(decryptBytes);
        }catch(Exception e){
            e.printStackTrace(); //在命令行打印错误信息
            return e.getMessage();
        }
    }

    public static String encrypt(String content) throws Exception {
        return encrypt(content, KEY);
    }

    public static String decrypt(String encryptStr) throws Exception {
        return decrypt(encryptStr, KEY);
    }


/*    public static void main(String[] args) throws Exception {
        Map map=new HashMap<String,String>();
        map.put("key","value");
        map.put("中文","汉字");
        String content = JSONObject.toJSONString(map);
        System.out.println("加密前：" + content);

        String encrypt = encrypt(content, KEY);
        System.out.println("加密后：" + encrypt);

        String decrypt = decrypt(encrypt, KEY);
        System.out.println("解密后：" + decrypt);
}*/
}
