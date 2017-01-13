package com.gh.emap.manager;

import com.gh.emap.MainActivity;
import com.gh.emap.listener.BottomEditLineListener;
import com.gh.emap.listener.BottomEditPointListener;
import com.gh.emap.listener.LayerListener;
import com.gh.emap.listener.MenuListener;
import com.gh.emap.listener.MyLocationListener;
import com.gh.emap.listener.MyOverlayListener;
import com.gh.emap.listener.OperationListener;
import com.gh.emap.listener.TopEditLineListener;
import com.gh.emap.listener.TopEditListener;
import com.gh.emap.listener.ShapEditListener;
import com.gh.emap.listener.TopEditPointListener;
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
    private LayerListener mLayerListener;
    private OperationListener mOperationListener;
    private UserLoginListener mUserLoginListener;
    private UserRegisterListener mUserRegisterListener;
    private UserLogoutListener mUserLogoutListener;
    private TopEditListener mTopEditListener;
    private ShapEditListener mShapEditListener;
    private TopEditPointListener mTopEditPointListener;
    private BottomEditPointListener mBottomEditPointListener;
    private TopEditLineListener mTopEditLineListener;
    private BottomEditLineListener mBottomEditLineListener;
    private MyOverlayListener mMyOverlayListener;


    public ListenerManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mMyLocationListener = new MyLocationListener(mMainActivity);
        mTopNormalListener = new TopNormalListener(mMainActivity);
        mMenuListener = new MenuListener(mMainActivity);
        mLayerListener = new LayerListener(mMainActivity);
        mOperationListener = new OperationListener(mMainActivity);
        mUserLoginListener = new UserLoginListener(mMainActivity);
        mUserRegisterListener = new UserRegisterListener(mMainActivity);
        mUserLogoutListener = new UserLogoutListener(mMainActivity);
        mTopEditListener = new TopEditListener(mMainActivity);
        mShapEditListener = new ShapEditListener(mMainActivity);
        mTopEditPointListener = new TopEditPointListener(mMainActivity);
        mBottomEditPointListener = new BottomEditPointListener(mMainActivity);
        mTopEditLineListener = new TopEditLineListener(mMainActivity);
        mBottomEditLineListener = new BottomEditLineListener(mMainActivity);
        mMyOverlayListener = new MyOverlayListener(mMainActivity);
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

    public LayerListener getLayerListener() {
        return mLayerListener;
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

    public TopEditListener getTopEditListener() {
        return mTopEditListener;
    }

    public ShapEditListener getShapEditListener() {
        return mShapEditListener;
    }

    public TopEditPointListener getTopEditPointListener() {
        return mTopEditPointListener;
    }

    public BottomEditPointListener getBottomEditPointListener() {
        return mBottomEditPointListener;
    }

    public TopEditLineListener getTopEditLineListener() {
        return mTopEditLineListener;
    }

    public BottomEditLineListener getBottomEditLineListener() {
        return mBottomEditLineListener;
    }

    public MyOverlayListener getMyOverlayListener() {
        return mMyOverlayListener;
    }
}
