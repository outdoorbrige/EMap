package com.gh.emap.manager;

import com.gh.emap.MainActivity;
import com.gh.emap.listener.BottomGroundRenderLineListener;
import com.gh.emap.listener.BottomGroundRenderPointListener;
import com.gh.emap.listener.DrawListener;
import com.gh.emap.listener.MenuListener;
import com.gh.emap.listener.MyLocationListener;
import com.gh.emap.listener.OperationListener;
import com.gh.emap.listener.TopDrawAreaGirthListener;
import com.gh.emap.listener.TopDrawDistanceAzimuthListener;
import com.gh.emap.listener.TopGroundRenderLineListener;
import com.gh.emap.listener.TopGroundRenderPlaneListener;
import com.gh.emap.listener.TopRenderListener;
import com.gh.emap.listener.GroundRenderListener;
import com.gh.emap.listener.TopGroundRenderPointListener;
import com.gh.emap.listener.TopNormalListener;
import com.gh.emap.listener.UserLoginListener;
import com.gh.emap.listener.UserLogoutListener;
import com.gh.emap.listener.UserRegisterListener;

/**
 * Created by GuHeng on 2016/11/9.
 * 监听管理类
 */
public class ListenerManager {
    private MainActivity mMainActivity;
    private MyLocationListener mMyLocationListener;
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
    private BottomGroundRenderPointListener mBottomGroundRenderPointListener;
    private TopGroundRenderLineListener mTopGroundRenderLineListener;
    private BottomGroundRenderLineListener mBottomGroundRenderLineListener;
    private TopGroundRenderPlaneListener mTopGroundRenderPlaneListener;
    private TopDrawDistanceAzimuthListener mTopDrawDistanceAzimuthListener;
    private TopDrawAreaGirthListener mTopDrawAreaGirthListener;

    public ListenerManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mMyLocationListener = new MyLocationListener(mMainActivity);
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
        mBottomGroundRenderPointListener = new BottomGroundRenderPointListener(mMainActivity);
        mTopGroundRenderLineListener = new TopGroundRenderLineListener(mMainActivity);
        mBottomGroundRenderLineListener = new BottomGroundRenderLineListener(mMainActivity);
        mTopGroundRenderPlaneListener = new TopGroundRenderPlaneListener(mMainActivity);
        mTopDrawDistanceAzimuthListener = new TopDrawDistanceAzimuthListener(mMainActivity);
        mTopDrawAreaGirthListener = new TopDrawAreaGirthListener(mMainActivity);
    }

    public MyLocationListener getMyLocationListener() {
        return mMyLocationListener;
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

    public BottomGroundRenderPointListener getBottomGroundRenderPointListener() {
        return mBottomGroundRenderPointListener;
    }

    public TopGroundRenderLineListener getTopGroundRenderLineListener() {
        return mTopGroundRenderLineListener;
    }

    public BottomGroundRenderLineListener getBottomGroundRenderLineListener() {
        return mBottomGroundRenderLineListener;
    }

    public TopGroundRenderPlaneListener getTopGroundRenderPlaneListener() {
        return mTopGroundRenderPlaneListener;
    }

    public TopDrawDistanceAzimuthListener getTopDrawDistanceAzimuthListener() {
        return mTopDrawDistanceAzimuthListener;
    }

    public TopDrawAreaGirthListener getTopDrawAreaGirthListener() {
        return mTopDrawAreaGirthListener;
    }
}
