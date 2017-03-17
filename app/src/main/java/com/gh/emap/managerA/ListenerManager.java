package com.gh.emap.managerA;

import com.gh.emap.MainActivity;
import com.gh.emap.listenerA.BottomGroundRenderLineMenuListener;
import com.gh.emap.listenerA.BottomGroundRenderPlaneMenuListener;
import com.gh.emap.listenerA.DrawAreaGirthAddGeoPointListener;
import com.gh.emap.listenerA.DrawDistanceAzimuthAddGeoPointListener;
import com.gh.emap.listenerA.GroundRenderLineAddGeoPointListener;
import com.gh.emap.listenerA.GroundRenderLineTypeListener;
import com.gh.emap.listenerA.BottomGroundRenderPointMenuListener;
import com.gh.emap.listenerA.GroundRenderPlaneAddGeoPointListener;
import com.gh.emap.listenerA.GroundRenderPointAddGeoPointListener;
import com.gh.emap.listenerA.GroundRenderPointTypeListener;
import com.gh.emap.listenerA.DrawListener;
import com.gh.emap.listenerA.MapListener;
import com.gh.emap.listenerA.MenuListener;
import com.gh.emap.listenerA.MyLocationListener;
import com.gh.emap.listenerA.OperationListener;
import com.gh.emap.listenerA.BottomDrawAreaGirthMenuListener;
import com.gh.emap.listenerA.BottomDrawDistanceAzimuthMenuListener;
import com.gh.emap.listenerA.TopGroundRenderLineListener;
import com.gh.emap.listenerA.TopGroundRenderPlaneListener;
import com.gh.emap.listenerA.TopRenderListener;
import com.gh.emap.listenerA.GroundRenderListener;
import com.gh.emap.listenerA.TopGroundRenderPointListener;
import com.gh.emap.listenerA.TopNormalListener;
import com.gh.emap.listenerA.UserLoginListener;
import com.gh.emap.listenerA.UserLogoutListener;
import com.gh.emap.listenerA.UserRegisterListener;

/**
 * Created by GuHeng on 2016/11/9.
 * 监听管理类
 */
public class ListenerManager {
    private MainActivity mMainActivity;
    private MyLocationListener mMyLocationListener;
    private MapListener mMapListener;
    private TopNormalListener mTopNormalListener;
    private MenuListener mMenuListener;
    private OperationListener mOperationListener;
    private UserLoginListener mUserLoginListener;
    private UserRegisterListener mUserRegisterListener;
    private UserLogoutListener mUserLogoutListener;
    private TopRenderListener mTopRenderListener;
    private GroundRenderListener mGroundRenderListener;
    private DrawListener mDrawListener;
    private TopGroundRenderPointListener mTopGroundRenderPointListener;
    private BottomGroundRenderPointMenuListener mBottomGroundRenderPointMenuListener;
    private GroundRenderPointTypeListener mGroundRenderPointTypeListener;
    private GroundRenderPointAddGeoPointListener mGroundRenderPointAddGeoPointListener;
    private TopGroundRenderLineListener mTopGroundRenderLineListener;
    private BottomGroundRenderLineMenuListener mBottomGroundRenderLineMenuListener;
    private GroundRenderLineTypeListener mGroundRenderLineTypeListener;
    private GroundRenderLineAddGeoPointListener mGroundRenderLineAddGeoPointListener;
    private TopGroundRenderPlaneListener mTopGroundRenderPlaneListener;
    private BottomGroundRenderPlaneMenuListener mBottomGroundRenderPlaneMenuListener;
    private GroundRenderPlaneAddGeoPointListener mGroundRenderPlaneAddGeoPointListener;
    private BottomDrawDistanceAzimuthMenuListener mBottomDrawDistanceAzimuthMenuListener;
    private DrawDistanceAzimuthAddGeoPointListener mDrawDistanceAzimuthAddGeoPointListener;
    private BottomDrawAreaGirthMenuListener mBottomDrawAreaGirthMenuListener;
    private DrawAreaGirthAddGeoPointListener mDrawAreaGirthAddGeoPointListener;

    public ListenerManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mMyLocationListener = new MyLocationListener(mMainActivity);
        mMapListener = new MapListener(mMainActivity);
        mTopNormalListener = new TopNormalListener(mMainActivity);
        mMenuListener = new MenuListener(mMainActivity);
        mOperationListener = new OperationListener(mMainActivity);
        mUserLoginListener = new UserLoginListener(mMainActivity);
        mUserRegisterListener = new UserRegisterListener(mMainActivity);
        mUserLogoutListener = new UserLogoutListener(mMainActivity);
        mTopRenderListener = new TopRenderListener(mMainActivity);
        mGroundRenderListener = new GroundRenderListener(mMainActivity);
        mDrawListener = new DrawListener(mMainActivity);
        mTopGroundRenderPointListener = new TopGroundRenderPointListener(mMainActivity);
        mBottomGroundRenderPointMenuListener = new BottomGroundRenderPointMenuListener(mMainActivity);
        mGroundRenderPointTypeListener = new GroundRenderPointTypeListener(mMainActivity);
        mGroundRenderPointAddGeoPointListener = new GroundRenderPointAddGeoPointListener(mMainActivity);
        mTopGroundRenderLineListener = new TopGroundRenderLineListener(mMainActivity);
        mBottomGroundRenderLineMenuListener = new BottomGroundRenderLineMenuListener(mMainActivity);
        mGroundRenderLineTypeListener = new GroundRenderLineTypeListener(mMainActivity);
        mGroundRenderLineAddGeoPointListener = new GroundRenderLineAddGeoPointListener(mMainActivity);
        mTopGroundRenderPlaneListener = new TopGroundRenderPlaneListener(mMainActivity);
        mBottomGroundRenderPlaneMenuListener = new BottomGroundRenderPlaneMenuListener(mMainActivity);
        mGroundRenderPlaneAddGeoPointListener = new GroundRenderPlaneAddGeoPointListener(mMainActivity);
        mBottomDrawDistanceAzimuthMenuListener = new BottomDrawDistanceAzimuthMenuListener(mMainActivity);
        mDrawDistanceAzimuthAddGeoPointListener = new DrawDistanceAzimuthAddGeoPointListener(mMainActivity);
        mBottomDrawAreaGirthMenuListener = new BottomDrawAreaGirthMenuListener(mMainActivity);
        mDrawAreaGirthAddGeoPointListener = new DrawAreaGirthAddGeoPointListener(mMainActivity);
    }

    public void unInit() {

    }

    public MyLocationListener getMyLocationListener() {
        return mMyLocationListener;
    }

    public MapListener getMapListener() {
        return mMapListener;
    }

    public TopNormalListener getTopNormalListener() {
        return mTopNormalListener;
    }

    public MenuListener getMenuListener() {
        return mMenuListener;
    }

    public OperationListener getOperationListener() {
        return mOperationListener;
    }

    public UserLoginListener getUserLoginListener() {
        return mUserLoginListener;
    }

    public UserRegisterListener getUserRegisterListener() {
        return mUserRegisterListener;
    }

    public UserLogoutListener getUserLogoutListener() {
        return mUserLogoutListener;
    }

    public TopRenderListener getTopRenderListener() {
        return mTopRenderListener;
    }

    public GroundRenderListener getGroundRenderListener() {
        return mGroundRenderListener;
    }

    public DrawListener getDrawListener() {
        return mDrawListener;
    }

    public TopGroundRenderPointListener getTopGroundRenderPointListener() {
        return mTopGroundRenderPointListener;
    }

    public BottomGroundRenderPointMenuListener getBottomGroundRenderPointMenuListener() {
        return mBottomGroundRenderPointMenuListener;
    }

    public GroundRenderPointTypeListener getGroundRenderPointTypeListener() {
        return mGroundRenderPointTypeListener;
    }

    public GroundRenderPointAddGeoPointListener getGroundRenderPointAddGeoPointListener() {
        return mGroundRenderPointAddGeoPointListener;
    }

    public TopGroundRenderLineListener getTopGroundRenderLineListener() {
        return mTopGroundRenderLineListener;
    }

    public BottomGroundRenderLineMenuListener getBottomGroundRenderLineMenuListener() {
        return mBottomGroundRenderLineMenuListener;
    }

    public GroundRenderLineTypeListener getGroundRenderLineTypeListener() {
        return mGroundRenderLineTypeListener;
    }

    public GroundRenderLineAddGeoPointListener getGroundRenderLineAddGeoPointListener() {
        return mGroundRenderLineAddGeoPointListener;
    }

    public TopGroundRenderPlaneListener getTopGroundRenderPlaneListener() {
        return mTopGroundRenderPlaneListener;
    }

    public BottomGroundRenderPlaneMenuListener getBottomGroundRenderPlaneMenuListener() {
        return mBottomGroundRenderPlaneMenuListener;
    }

    public GroundRenderPlaneAddGeoPointListener getGroundRenderPlaneAddGeoPointListener() {
        return mGroundRenderPlaneAddGeoPointListener;
    }

    public BottomDrawDistanceAzimuthMenuListener getBottomDrawDistanceAzimuthMenuListener() {
        return mBottomDrawDistanceAzimuthMenuListener;
    }

    public DrawDistanceAzimuthAddGeoPointListener getDrawDistanceAzimuthAddGeoPointListener() {
        return mDrawDistanceAzimuthAddGeoPointListener;
    }

    public BottomDrawAreaGirthMenuListener getBottomDrawAreaGirthMenuListener() {
        return mBottomDrawAreaGirthMenuListener;
    }

    public DrawAreaGirthAddGeoPointListener getDrawAreaGirthAddGeoPointListener() {
        return mDrawAreaGirthAddGeoPointListener;
    }
}
