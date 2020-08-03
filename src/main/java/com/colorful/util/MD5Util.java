package com.colorful.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String md5(String source) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 将一个byte数组进行加密操作，返回的是一个加密的byte数组，二进制的哈西计算，md5加密的第一步
            byte[] digest = messageDigest.digest(source.getBytes());
            for (byte b : digest) {
                int result = b & 0xff;
                // 将得到的int类型的值转化为16进制的值
                String hexString = Integer.toHexString(result);
                if (hexString.length() < 2) {
                    //系统会自动把0省略，所以添加0
                    stringBuilder.append("0");
                }
                stringBuilder.append(hexString);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String passWord = "123456";
        String s = MD5Util.md5(passWord);
        System.out.println(s);
    }

}
