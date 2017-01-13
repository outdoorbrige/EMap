package com.gh.emap.manager;

import com.gh.emap.MainActivity;
import com.gh.emap.layout.BottomShapLineLayout;
import com.gh.emap.layout.BottomShapPointLayout;
import com.gh.emap.layout.LayerLayout;
import com.gh.emap.layout.MenuLayout;
import com.gh.emap.layout.OperationLayout;
import com.gh.emap.layout.TopEditLayout;
import com.gh.emap.layout.TopNormalLayout;
import com.gh.emap.layout.TopShapLineLayout;
import com.gh.emap.layout.TopShapPointLayout;
import com.gh.emap.layout.UserLoginLayout;
import com.gh.emap.layout.UserLogoutLayout;
import com.gh.emap.layout.UserRegisterLayout;

/**
 * Created by GuHeng on 2016/11/9.
 * 布局管理类
 */
public class LayoutManager {
    private MainActivity mMainActivity;
    private TopNormalLayout mTopNormalLayout;
    private MenuLayout mMenuLayout;
    private LayerLayout mLayerLayout;
    private OperationLayout mOperationLayout;
    private TopEditLayout mTopEditLayout;
    private TopShapPointLayout mTopShapPointLayout;
    private BottomShapPointLayout mBottomShapPointLayout;
    private TopShapLineLayout mTopShapLineLayout;
    private BottomShapLineLayout mBottomShapLineLayout;
    private UserLoginLayout mUserLoginLayout;
    private UserRegisterLayout mUserRegisterLayout;
    private UserLogoutLayout mUserLogoutLayout;

    public LayoutManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mTopNormalLayout = new TopNormalLayout(mMainActivity);
        mTopNormalLayout.init();

        mMenuLayout = new MenuLayout(mMainActivity);
        mMenuLayout.init();

        mLayerLayout = new LayerLayout(mMainActivity);
        mLayerLayout.init();

        mOperationLayout = new OperationLayout(mMainActivity);
        mOperationLayout.init();

        mTopEditLayout = new TopEditLayout(mMainActivity);
        mTopEditLayout.init();

        mTopShapPointLayout = new TopShapPointLayout(mMainActivity);
        mTopShapPointLayout.init();

        mBottomShapPointLayout = new BottomShapPointLayout(mMainActivity);
        mBottomShapPointLayout.init();

        mTopShapLineLayout = new TopShapLineLayout(mMainActivity);
        mTopShapLineLayout.init();

        mBottomShapLineLayout = new BottomShapLineLayout(mMainActivity);
        mBottomShapLineLayout.init();

        mUserLoginLayout = new UserLoginLayout(mMainActivity);
        mUserLoginLayout.init();

        mUserRegisterLayout = new UserRegisterLayout(mMainActivity);
        mUserRegisterLayout.init();

        mUserLogoutLayout = new UserLogoutLayout(mMainActivity);
        mUserLogoutLayout.init();
    }

    public TopNormalLayout getTopNormalLayout() {
        return mTopNormalLayout;
    }

    public MenuLayout getMenuLayout() {
        return mMenuLayout;
    }

    public LayerLayout getLayerLayout() {
        return mLayerLayout;
    }

    public OperationLayout getOperationLayout() {
        return mOperationLayout;
    }

    public TopEditLayout getTopEditLayout() {
        return mTopEditLayout;
    }

    public TopShapPointLayout getTopShapPointLayout() {
        return mTopShapPointLayout;
    }

    public BottomShapPointLayout getBottomShapPointLayout() {
        return mBottomShapPointLayout;
    }

    public TopShapLineLayout getTopShapLineLayout() {
        return mTopShapLineLayout;
    }

    public BottomShapLineLayout getBottomShapLineLayout() {
        return mBottomShapLineLayout;
    }

    public UserLoginLayout getUserLoginLayout() {
        return mUserLoginLayout;
    }

    public UserRegisterLayout getUserRegisterLayout() {
        return mUserRegisterLayout;
    }

    public UserLogoutLayout getUserLogoutLayout() {
        return mUserLogoutLayout;
    }
}
