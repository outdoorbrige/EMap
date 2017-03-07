package com.gh.emap.manager;

import com.gh.emap.MainActivity;
import com.gh.emap.layout.BottomGroundRenderLineMenuLayout;
import com.gh.emap.layout.BottomGroundRenderPlaneMenuLayout;
import com.gh.emap.layout.DrawAreaGirthAddGeoPointLayout;
import com.gh.emap.layout.DrawDistanceAzimuthAddGeoPointLayout;
import com.gh.emap.layout.GroundRenderLineTypeLayout;
import com.gh.emap.layout.BottomGroundRenderPointMenuLayout;
import com.gh.emap.layout.GroundRenderPlaneAddGeoPointLayout;
import com.gh.emap.layout.GroundRenderPointTypeLayout;
import com.gh.emap.layout.MenuLayout;
import com.gh.emap.layout.OperationLayout;
import com.gh.emap.layout.BottomDrawAreaGirthMenuLayout;
import com.gh.emap.layout.BottomDrawDistanceAzimuthMenuLayout;
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
    private BottomGroundRenderPointMenuLayout mBottomGroundRenderPointMenuLayout;
    private GroundRenderPointTypeLayout mGroundRenderPointTypeLayout;
    private TopGroundRenderLineLayout mTopGroundRenderLineLayout;
    private BottomGroundRenderLineMenuLayout mBottomGroundRenderLineMenuLayout;
    private GroundRenderLineTypeLayout mGroundRenderLineTypeLayout;
    private TopGroundRenderPlaneLayout mTopGroundRenderPlaneLayout;
    private BottomGroundRenderPlaneMenuLayout mBottomGroundRenderPlaneMenuLayout;
    private GroundRenderPlaneAddGeoPointLayout mGroundRenderPlaneAddGeoPointLayout;
    private BottomDrawDistanceAzimuthMenuLayout mBottomDrawDistanceAzimuthMenuLayout;
    private DrawDistanceAzimuthAddGeoPointLayout mDrawDistanceAzimuthAddGeoPointLayout;
    private BottomDrawAreaGirthMenuLayout mBottomDrawAreaGirthMenuLayout;
    private DrawAreaGirthAddGeoPointLayout mDrawAreaGirthAddGeoPointLayout;
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

        mBottomGroundRenderPointMenuLayout = new BottomGroundRenderPointMenuLayout(mMainActivity);
        mBottomGroundRenderPointMenuLayout.init();

        mGroundRenderPointTypeLayout = new GroundRenderPointTypeLayout(mMainActivity);
        mGroundRenderPointTypeLayout.init();

        mTopGroundRenderLineLayout = new TopGroundRenderLineLayout(mMainActivity);
        mTopGroundRenderLineLayout.init();

        mBottomGroundRenderLineMenuLayout = new BottomGroundRenderLineMenuLayout(mMainActivity);
        mBottomGroundRenderLineMenuLayout.init();

        mGroundRenderLineTypeLayout = new GroundRenderLineTypeLayout(mMainActivity);
        mGroundRenderLineTypeLayout.init();

        mTopGroundRenderPlaneLayout = new TopGroundRenderPlaneLayout(mMainActivity);
        mTopGroundRenderPlaneLayout.init();

        mBottomGroundRenderPlaneMenuLayout = new BottomGroundRenderPlaneMenuLayout(mMainActivity);
        mBottomGroundRenderPlaneMenuLayout.init();

        mGroundRenderPlaneAddGeoPointLayout = new GroundRenderPlaneAddGeoPointLayout(mMainActivity);
        mGroundRenderPlaneAddGeoPointLayout.init();

        mBottomDrawDistanceAzimuthMenuLayout = new BottomDrawDistanceAzimuthMenuLayout(mMainActivity);
        mBottomDrawDistanceAzimuthMenuLayout.init();

        mDrawDistanceAzimuthAddGeoPointLayout = new DrawDistanceAzimuthAddGeoPointLayout(mMainActivity);
        mDrawDistanceAzimuthAddGeoPointLayout.init();

        mBottomDrawAreaGirthMenuLayout = new BottomDrawAreaGirthMenuLayout(mMainActivity);
        mBottomDrawAreaGirthMenuLayout.init();

        mDrawAreaGirthAddGeoPointLayout = new DrawAreaGirthAddGeoPointLayout(mMainActivity);
        mDrawAreaGirthAddGeoPointLayout.init();

        mUserLoginLayout = new UserLoginLayout(mMainActivity);
        mUserLoginLayout.init();

        mUserRegisterLayout = new UserRegisterLayout(mMainActivity);
        mUserRegisterLayout.init();

        mUserLogoutLayout = new UserLogoutLayout(mMainActivity);
        mUserLogoutLayout.init();
    }

    public void unInit() {
        mGroundRenderPointTypeLayout.dimiss();
        mGroundRenderLineTypeLayout.dimiss();
        mGroundRenderPlaneAddGeoPointLayout.dimiss();
        mDrawDistanceAzimuthAddGeoPointLayout.dimiss();
        mDrawAreaGirthAddGeoPointLayout.dimiss();
        mUserLoginLayout.dimiss();
        mUserRegisterLayout.dimiss();
        mUserLogoutLayout.dimiss();
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

    public BottomGroundRenderPointMenuLayout getBottomGroundRenderPointMenuLayout() {
        return mBottomGroundRenderPointMenuLayout;
    }

    public GroundRenderPointTypeLayout getGroundRenderPointTypeLayout() {
        return mGroundRenderPointTypeLayout;
    }

    public TopGroundRenderLineLayout getTopGroundRenderLineLayout() {
        return mTopGroundRenderLineLayout;
    }

    public BottomGroundRenderLineMenuLayout getBottomGroundRenderLineMenuLayout() {
        return mBottomGroundRenderLineMenuLayout;
    }

    public GroundRenderLineTypeLayout getGroundRenderLineTypeLayout() {
        return mGroundRenderLineTypeLayout;
    }

    public TopGroundRenderPlaneLayout getTopGroundRenderPlaneLayout() {
        return mTopGroundRenderPlaneLayout;
    }

    public BottomGroundRenderPlaneMenuLayout getBottomGroundRenderPlaneMenuLayout() {
        return mBottomGroundRenderPlaneMenuLayout;
    }

    public BottomDrawDistanceAzimuthMenuLayout getBottomDrawDistanceAzimuthMenuLayout() {
        return mBottomDrawDistanceAzimuthMenuLayout;
    }

    public GroundRenderPlaneAddGeoPointLayout getGroundRenderPlaneAddGeoPointLayout() {
        return mGroundRenderPlaneAddGeoPointLayout;
    }

    public DrawDistanceAzimuthAddGeoPointLayout getDrawDistanceAzimuthAddGeoPointLayout() {
        return mDrawDistanceAzimuthAddGeoPointLayout;
    }

    public BottomDrawAreaGirthMenuLayout getBottomDrawAreaGirthMenuLayout() {
        return mBottomDrawAreaGirthMenuLayout;
    }

    public DrawAreaGirthAddGeoPointLayout getDrawAreaGirthAddGeoPointLayout() {
        return mDrawAreaGirthAddGeoPointLayout;
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
