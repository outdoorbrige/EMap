package com.gh.emap.manager;

import com.gh.emap.MainActivity;
import com.gh.emap.layout.BottomGroundRenderLineLayout;
import com.gh.emap.layout.BottomGroundRenderPointLayout;
import com.gh.emap.layout.MenuLayout;
import com.gh.emap.layout.OperationLayout;
import com.gh.emap.layout.TopDrawAreaGirthLayout;
import com.gh.emap.layout.TopDrawDistanceAzimuthLayout;
import com.gh.emap.layout.TopGroundRenderPlaneLayout;
import com.gh.emap.layout.TopRenderLayout;
import com.gh.emap.layout.TopNormalLayout;
import com.gh.emap.layout.TopGroundRenderLineLayout;
import com.gh.emap.layout.TopGroundRenderPointLayout;
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
    private OperationLayout mOperationLayout;
    private TopRenderLayout mTopRenderLayout;
    private TopGroundRenderPointLayout mTopGroundRenderPointLayout;
    private BottomGroundRenderPointLayout mBottomGroundRenderPointLayout;
    private TopGroundRenderLineLayout mTopGroundRenderLineLayout;
    private BottomGroundRenderLineLayout mBottomGroundRenderLineLayout;
    private TopGroundRenderPlaneLayout mTopGroundRenderPlaneLayout;
    private TopDrawDistanceAzimuthLayout mTopDrawDistanceAzimuthLayout;
    private TopDrawAreaGirthLayout mTopDrawAreaGirthLayout;
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

        mOperationLayout = new OperationLayout(mMainActivity);
        mOperationLayout.init();

        mTopRenderLayout = new TopRenderLayout(mMainActivity);
        mTopRenderLayout.init();

        mTopGroundRenderPointLayout = new TopGroundRenderPointLayout(mMainActivity);
        mTopGroundRenderPointLayout.init();

        mBottomGroundRenderPointLayout = new BottomGroundRenderPointLayout(mMainActivity);
        mBottomGroundRenderPointLayout.init();

        mTopGroundRenderLineLayout = new TopGroundRenderLineLayout(mMainActivity);
        mTopGroundRenderLineLayout.init();

        mBottomGroundRenderLineLayout = new BottomGroundRenderLineLayout(mMainActivity);
        mBottomGroundRenderLineLayout.init();

        mTopGroundRenderPlaneLayout = new TopGroundRenderPlaneLayout(mMainActivity);
        mTopGroundRenderPlaneLayout.init();

        mTopDrawDistanceAzimuthLayout = new TopDrawDistanceAzimuthLayout(mMainActivity);
        mTopDrawDistanceAzimuthLayout.init();

        mTopDrawAreaGirthLayout = new TopDrawAreaGirthLayout(mMainActivity);
        mTopDrawAreaGirthLayout.init();

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

    public OperationLayout getOperationLayout() {
        return mOperationLayout;
    }

    public TopRenderLayout getTopRenderLayout() {
        return mTopRenderLayout;
    }

    public TopGroundRenderPointLayout getTopGroundRenderPointLayout() {
        return mTopGroundRenderPointLayout;
    }

    public BottomGroundRenderPointLayout getBottomGroundRenderPointLayout() {
        return mBottomGroundRenderPointLayout;
    }

    public TopGroundRenderLineLayout getTopGroundRenderLineLayout() {
        return mTopGroundRenderLineLayout;
    }

    public BottomGroundRenderLineLayout getBottomGroundRenderLineLayout() {
        return mBottomGroundRenderLineLayout;
    }

    public TopGroundRenderPlaneLayout getTopGroundRenderPlaneLayout() {
        return mTopGroundRenderPlaneLayout;
    }

    public TopDrawDistanceAzimuthLayout getTopDrawDistanceAzimuthLayout() {
        return mTopDrawDistanceAzimuthLayout;
    }

    public TopDrawAreaGirthLayout getTopDrawAreaGirthLayout() {
        return mTopDrawAreaGirthLayout;
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
