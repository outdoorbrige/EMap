package cn.com.sgcc.epri.emap.map;

import android.content.Context;
import android.location.Location;

import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;

/**
 * Created by GuHeng on 2016/9/20.
 */
public class TMyLocationOverlay extends MyLocationOverlay {
    private Context mContext;

    public TMyLocationOverlay(Context context, MapView mapView) {
        super(context, mapView);
        mContext = context;
    }
}
