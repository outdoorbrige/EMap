package cn.com.sgcc.epri.emap;

import android.content.Context;
import android.location.Location;

import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;

/**
 * Created by A on 2016/9/20.
 */
public class TMyLocationOverlay extends MyLocationOverlay {
    private Context context;

    public TMyLocationOverlay(Context var1, MapView var2) {
        super(var1, var2);
        context = var1;
    }

    public void onLocationChanged(Location var1) {
        super.onLocationChanged(var1);
    }
}
