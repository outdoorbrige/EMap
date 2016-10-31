package cn.com.sgcc.epri.emap.map;

import android.content.Context;
import android.util.AttributeSet;

import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.TOfflineMapManager;
import com.tianditu.android.maps.TOfflineMapManager.OnGetMapsResult;

import java.util.ArrayList;

import cn.com.sgcc.epri.emap.MainActivity;

/**
 * Created by GuHeng on 2016/9/20.
 */
public class TMapView extends MapView implements OnGetMapsResult {
    private MainActivity mMainActivity;

    public TMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMainActivity = (MainActivity) context;
    }

    public TMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMainActivity = (MainActivity) context;
    }

    public TMapView(Context context, String s) {
        super(context, s);
        this.mMainActivity = (MainActivity) context;
    }

    /**********************************************************************************************/
    // OnGetMapsResult
    @Override
    public void onGetResult(ArrayList<TOfflineMapManager.MapAdminSet> maps, int error) {

    }
}
