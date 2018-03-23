package com.em.baseframe.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @title  MD5加密类
 * @date   2017/06/17
 * @author enmaoFu
 */
public class MD5Util {

    public static String md5(String plainText) {
        String str = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0){
                    i += 256;
                }
                if (i < 16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }
    /**
     * 产生小写MD5值
     * @param plainText
     * @return
     */
    public static String md5min(String plainText) {
        String str = MD5Util.md5(plainText);
        return str.toLowerCase();
    }
    /**
     * 产生大写MD5值
     * @param plainText
     * @return
     */
    public static String md5max(String plainText) {
        String str = MD5Util.md5(plainText);
        return str.toUpperCase();
    }

    /**
     * @Description(描述) md5加密
     * @param inStr MD5加码。32位
     * @return  String
     */
    public static String string2MD5(String inStr) {
        StringBuffer sb = new StringBuffer(32);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(inStr.getBytes("utf-8"));

            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
            }
        } catch (Exception e) {
            return null;
        }

        return sb.toString();
    }

    /**
     * @Description(描述) 对md5 解密
     * @param inStr
     * @return String
     */
    public static String convertMD5(String inStr) {
        return convertMD5_single(convertMD5_single(inStr));
    }

    /**
     * @param inStr
     * @return String
     */
    private static String convertMD5_single(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

}
