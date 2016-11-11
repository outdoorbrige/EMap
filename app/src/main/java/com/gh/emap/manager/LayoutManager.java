package com.gh.emap.manager;

import android.content.Context;

import com.gh.emap.layout.BottomShapPointLayout;
import com.gh.emap.layout.LayerLayout;
import com.gh.emap.layout.MenuLayout;
import com.gh.emap.layout.OperationLayout;
import com.gh.emap.layout.TopEditLayout;
import com.gh.emap.layout.TopNormalLayout;
import com.gh.emap.layout.TopShapPointLayout;
import com.gh.emap.layout.UserLoginLayout;
import com.gh.emap.layout.UserLogoutLayout;
import com.gh.emap.layout.UserRegisterLayout;

/**
 * Created by GuHeng on 2016/11/9.
 */
public class LayoutManager {
    private Context mContext;
    private TopNormalLayout mTopNormalLayout;
    private MenuLayout mMenuLayout;
    private LayerLayout mLayerLayout;
    private OperationLayout mOperationLayout;
    private TopEditLayout mTopEditLayout;
    private TopShapPointLayout mTopShapPointLayout;
    private BottomShapPointLayout mBottomShapPointLayout;
    private UserLoginLayout mUserLoginLayout;
    private UserRegisterLayout mUserRegisterLayout;
    private UserLogoutLayout mUserLogoutLayout;

    public LayoutManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mTopNormalLayout = new TopNormalLayout(this.mContext);
        this.mTopNormalLayout.init();

        this.mMenuLayout = new MenuLayout(this.mContext);
        this.mMenuLayout.init();

        this.mLayerLayout = new LayerLayout(this.mContext);
        this.mLayerLayout.init();

        this.mOperationLayout = new OperationLayout(this.mContext);
        this.mOperationLayout.init();

        this.mTopEditLayout = new TopEditLayout(this.mContext);
        this.mTopEditLayout.init();

        this.mTopShapPointLayout = new TopShapPointLayout(this.mContext);
        this.mTopShapPointLayout.init();

        this.mBottomShapPointLayout = new BottomShapPointLayout(this.mContext);
        this.mBottomShapPointLayout.init();

        this.mUserLoginLayout = new UserLoginLayout(this.mContext);
        this.mUserLoginLayout.init();

        this.mUserRegisterLayout = new UserRegisterLayout(this.mContext);
        this.mUserRegisterLayout.init();

        this.mUserLogoutLayout = new UserLogoutLayout(this.mContext);
        this.mUserLogoutLayout.init();
    }

    public TopNormalLayout getTopNormalLayout() {
        return this.mTopNormalLayout;
    }

    public MenuLayout getMenuLayout() {
        return this.mMenuLayout;
    }

    public LayerLayout getLayerLayout() {
        return this.mLayerLayout;
    }

    public OperationLayout getOperationLayout() {
        return this.mOperationLayout;
    }

    public TopEditLayout getTopEditLayout() {
        return this.mTopEditLayout;
    }

    public TopShapPointLayout getTopShapPointLayout() {
        return this.mTopShapPointLayout;
    }

    public BottomShapPointLayout getBottomShapPointLayout() {
        return this.mBottomShapPointLayout;
    }

    public UserLoginLayout getUserLoginLayout() {
        return this.mUserLoginLayout;
    }

    public UserRegisterLayout getUserRegisterLayout() {
        return this.mUserRegisterLayout;
    }

    public UserLogoutLayout getUserLogoutLayout() {
        return this.mUserLogoutLayout;
    }
}
