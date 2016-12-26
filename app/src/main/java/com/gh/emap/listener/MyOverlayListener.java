package com.gh.emap.listener;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;

import com.gh.emap.MainActivity;
import com.gh.emap.manager.MyUserOverlaysManager;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.Overlay;

/**
 * Created by GuHeng on 2016/12/21.
 * 覆盖物监听器
 */

public class MyOverlayListener implements MapView.OnOverlayListener {
    private Context mContext;

    public MyOverlayListener(Context context) {
        this.mContext = context;
    }

    public void onAddOverlay(Overlay overlay) {

    }

    public void onRemoveOverlay(Overlay overlay) {

    }

    public void onTouchOverlayUp(Point point, Overlay overlay) {
        MyUserOverlaysManager myUserOverlaysManager = ((MainActivity)this.mContext).getMainManager().getMyUserOverlaysManager();
    }

    public void onTouchOverlayLongPress(Point point, Overlay overlay) {

    }

    public Rect onOverlayVisibilityBound() {
        return null;
    }
}
