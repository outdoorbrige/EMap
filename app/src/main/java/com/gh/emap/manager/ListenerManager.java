package com.gh.emap.manager;

import android.content.Context;

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
    private Context mContext;
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


    public ListenerManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mMyLocationListener = new MyLocationListener(this.mContext);
        this.mTopNormalListener = new TopNormalListener(this.mContext);
        this.mMenuListener = new MenuListener(this.mContext);
        this.mLayerListener = new LayerListener(this.mContext);
        this.mOperationListener = new OperationListener(this.mContext);
        this.mUserLoginListener = new UserLoginListener(this.mContext);
        this.mUserRegisterListener = new UserRegisterListener(this.mContext);
        this.mUserLogoutListener = new UserLogoutListener(this.mContext);
        this.mTopEditListener = new TopEditListener(this.mContext);
        this.mShapEditListener = new ShapEditListener(this.mContext);
        this.mTopEditPointListener = new TopEditPointListener(this.mContext);
        this.mBottomEditPointListener = new BottomEditPointListener(this.mContext);
        this.mTopEditLineListener = new TopEditLineListener(this.mContext);
        this.mBottomEditLineListener = new BottomEditLineListener(this.mContext);
        this.mMyOverlayListener = new MyOverlayListener(this.mContext);
    }

    public MyLocationListener getMyLocationListener() {
        return this.mMyLocationListener;
    }

    public TopNormalListener getTopNormalListener() {
        return this.mTopNormalListener;
    }

    public MenuListener getMenuListener() {
        return this.mMenuListener;
    }

    public LayerListener getLayerListener() {
        return this.mLayerListener;
    }

    public OperationListener getOperationListener() {
        return this.mOperationListener;
    }

    public UserLoginListener getUserLoginListener() {
        return this.mUserLoginListener;
    }

    public UserRegisterListener getUserRegisterListener() {
        return this.mUserRegisterListener;
    }

    public UserLogoutListener getUserLogoutListener() {
        return this.mUserLogoutListener;
    }

    public TopEditListener getTopEditListener() {
        return this.mTopEditListener;
    }

    public ShapEditListener getShapEditListener() {
        return this.mShapEditListener;
    }

    public TopEditPointListener getTopEditPointListener() {
        return this.mTopEditPointListener;
    }

    public BottomEditPointListener getBottomEditPointListener() {
        return this.mBottomEditPointListener;
    }

    public TopEditLineListener getTopEditLineListener() {
        return this.mTopEditLineListener;
    }

    public BottomEditLineListener getBottomEditLineListener() {
        return this.mBottomEditLineListener;
    }

    public MyOverlayListener getMyOverlayListener() {
        return this.mMyOverlayListener;
    }
}
