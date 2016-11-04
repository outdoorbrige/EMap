package cn.com.sgcc.epri.emap.util;

import com.tianditu.android.maps.GeoPoint;
import com.tianditu.maps.GeoPointEx;

/**
 * Created by GuHeng on 2016/11/3.
 * 默认位置
 */
public class DefaultCenter {
    static public GeoPoint getDefaultCenter() {
        // 默认设置中心点为天安门
        return GeoPointEx.Double2GeoPoint(116.3919236741D, 39.9057789520D);
    }
}
