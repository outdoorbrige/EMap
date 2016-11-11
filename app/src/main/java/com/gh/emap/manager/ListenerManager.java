package com.gh.emap.manager;

import android.content.Context;

import com.gh.emap.listener.LayerListener;
import com.gh.emap.listener.MenuListener;
import com.gh.emap.listener.OperationListener;
import com.gh.emap.listener.TopEditListener;
import com.gh.emap.listener.TopNormalListener;
import com.gh.emap.listener.UserLoginListener;
import com.gh.emap.listener.UserLogoutListener;
import com.gh.emap.listener.UserRegisterListener;

/**
 * Created by GuHeng on 2016/11/9.
 */
public class ListenerManager {
    private Context mContext;
    private TopNormalListener mTopNormalListener;
    private MenuListener mMenuListener;
    private LayerListener mLayerListener;
    private OperationListener mOperationListener;
    private UserLoginListener mUserLoginListener;
    private UserRegisterListener mUserRegisterListener;
    private UserLogoutListener mUserLogoutListener;
    private TopEditListener mTopEditListener;

    public ListenerManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mTopNormalListener = new TopNormalListener(this.mContext);
        this.mMenuListener = new MenuListener(this.mContext);
        this.mLayerListener = new LayerListener(this.mContext);
        this.mOperationListener = new OperationListener(this.mContext);
        this.mUserLoginListener = new UserLoginListener(this.mContext);
        this.mUserRegisterListener = new UserRegisterListener(this.mContext);
        this.mUserLogoutListener = new UserLogoutListener(this.mContext);
        this.mTopEditListener = new TopEditListener(this.mContext);
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
}
