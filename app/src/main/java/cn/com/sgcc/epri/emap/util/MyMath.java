package cn.com.sgcc.epri.emap.util;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by GuHeng on 2016/10/24.
 * 算法类
 */
public class MyMath {
    // 计算字符串的MD5值
    public static String md5(String s) {
        StringBuilder hex = null;
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8"));
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

    /*
    1.怎么把经纬度十进制单位转换成标准的度分秒单位计算公式是，
    十进制的经度，纬度数的整数部分就是度数(°)，小数部分乘以60得到的数取整数部分就是分数(′)，再用该数的小数部分乘以60就是秒数(″)。
    如一个经度的十进制为:117.121806，那么:
    第一步：度数(°)117°，
    第二步：分数(′)7′(0.121806×60=7.308360189199448,取整数部分为7),
    第三步:秒数(″)18.501611351966858″(0.30836018919944763×60=18.501611351966858)，即度分秒为117°7′18.501611351966858″。

    2.怎么把经纬度度分秒单位转换成十进制单位
    将度分秒转换为十进制则刚好相反，将秒数(″)除以60，得到的数就是分数(′)的小数部分，将该小数加上分数(′)整数部
    分就是整个分数(′)，再将该分数(′)除以60，得到的小数就是度数(°)的小数部分，在加上度数的整数部分就是经纬度的十进制形式。
    例如，将一个纬度为37°25′19.222″的六十进制转换为十进制的步骤为：
    第一步(对应上面的第三步):19.222/60=0.3203666666666667,0.3203666666666667为分数(′)的小数部分，
    第二步(对应上面的第二步):25+0.3203666666666667=25.3203666666666667，25.3203666666666667分数(′)
    第三步(对应上面的第一步):25.3203666666666667/60=0.4220061111111111，0.4220061111111111为度数(°)的小数部分
    37°25′19.222″转换的最终结果为37+0.4220061111111111=37.4220061111111111
     */

    // 把经纬度十进制单位转换成标准的度分秒单位
    public static String[] toSexagesimalString(int latitude, int longitude) {
        String prefix1 = "";
        String prefix2 = "";
        String[] strings = new String[2];

        if(latitude > 0) { // 北纬
            prefix1 = "北纬N";
        } else { // 南纬
            prefix1 = "南纬S";
        }
        strings[0] = prefix1 + toSexagesimalString(latitude);

        if(longitude > 0) { // 东经
            prefix2 = "东经E";
        } else { // 西经
            prefix2 = "西经W";
        }
        strings[1] = prefix2 + toSexagesimalString(longitude);

        return strings;
    }

    // 把经纬度十进制单位转换成标准的度分秒单位
    public static String toSexagesimalString(int degree) {
        final double factor1 = 1000000.0D;
        final int factor2 = 60;

        degree = Math.abs(degree);

        double hDegree = degree / factor1;
        int h = (int)hDegree; // 度数

        double mDegree = (hDegree - h) * factor2;
        int m = (int)mDegree; // 分数

        double sDegree = (mDegree - m) * factor2;
        int s = (int)sDegree; // 秒数

        return String.format("%d°%d′%d″", h, m, s);
    }
}
