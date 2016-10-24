package cn.com.sgcc.epri.emap.util;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by GuHeng on 2016/10/24.
 * 算法类
 */
public class Algorithm {
    // 计算字符串的MD5值
    public static String md5(String var1) {
        StringBuilder hex = null;
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(var1.getBytes("UTF-8"));
            hex = new StringBuilder(hash.length * 2);
            for(byte b : hash) {
                if((b & 0XFF) < 0X10) {
                    hex.append('0');
                }
                hex.append(Integer.toHexString(b & 0XFF));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("系统不支持MD5算法!",e );
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("系统不支持UTF-8编码!",e );
        }

        return hex.toString();
    }
}
