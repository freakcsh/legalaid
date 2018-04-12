package com.freak.legalaid.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 思路过程：
 * 1、str.getBytes()：将字符串转化为字节数组。字符串中每个字符转换为对应的ASCII值作为字节数组中的一个元素
 * 2、将字节数组通过固定算法转换为16个元素的有符号哈希值字节数组
 * 3、将哈希字节数组的每个元素通过0xff与运算转换为两位无符号16进制的字符串
 * 4、将不足两位的无符号16进制的字符串前面加0
 * 5、通过StringBuffer.append()函数将16个长度为2的无符号进制字符串合并为一个32位String类型的MD5码
 */
public class MD5 {
    public static String getMD5(String str) {
        //MD5是加密方式
        MessageDigest messageDigest = null;
        try {
            /**
             * 获取MD5加密方式对象
             */
            messageDigest = MessageDigest.getInstance("MD5");
            /**
             * 重新设置摘要以供进一步使用
             */
            messageDigest.reset();
            /**
             * 使用指定的字节组更新摘要
             */
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /**
         * 将二进制的byte数组进行加密
         * 0xFF & byteArray[i]  进行加密 & int值的 255   000...00010000001
         *
         * toHexString   将int值转换为16进制
         *
         * String hexString = Integer.toHexString(result) + 1 ;//这里加1可以进行2次加密
         */
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        //16位加密，从第9位到25位
//        return md5StrBuff.substring(8, 24).toString().toUpperCase();
        return md5StrBuff.substring(0, 32).toString().toLowerCase();
    }
}
