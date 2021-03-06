package com.gh.emap.managerA;

import com.gh.emap.MainActivity;
import com.gh.emap.layoutA.BottomGroundRenderLineMenuLayout;
import com.gh.emap.layoutA.BottomGroundRenderPlaneMenuLayout;
import com.gh.emap.layoutA.BottomLocationInfoLayout;
import com.gh.emap.layoutA.DrawAreaGirthAddGeoPointLayout;
import com.gh.emap.layoutA.DrawDistanceAzimuthAddGeoPointLayout;
import com.gh.emap.layoutA.GroundRenderLineAddGeoPointLayout;
import com.gh.emap.layoutA.GroundRenderLineTypeLayout;
import com.gh.emap.layoutA.BottomGroundRenderPointMenuLayout;
import com.gh.emap.layoutA.GroundRenderPlaneAddGeoPointLayout;
import com.gh.emap.layoutA.GroundRenderPointAddGeoPointLayout;
import com.gh.emap.layoutA.GroundRenderPointTypeLayout;
import com.gh.emap.layoutA.MenuLayout;
import com.gh.emap.layoutA.OperationLayout;
import com.gh.emap.layoutA.BottomDrawAreaGirthMenuLayout;
import com.gh.emap.layoutA.BottomDrawDistanceAzimuthMenuLayout;
import com.gh.emap.layoutA.TopGroundRenderPlaneLayout;
import com.gh.emap.layoutA.TopRenderLayout;
import com.gh.emap.layoutA.TopNormalLayout;
import com.gh.emap.layoutA.TopGroundRenderLineLayout;
import com.gh.emap.layoutA.TopGroundRenderPointLayout;
import com.gh.emap.layoutA.UserLoginLayout;
import com.gh.emap.layoutA.UserLogoutLayout;
import com.gh.emap.layoutA.UserRegisterLayout;

/**
 * Created by GuHeng on 2016/11/9.
 * 布局管理类
 */
public class LayoutManager {
    private MainActivity mMainActivity;
    private BottomLocationInfoLayout mBottomLocationInfoLayout;
    private TopNormalLayout mTopNormalLayout;
    private MenuLayout mMenuLayout;
    private OperationLayout mOperationLayout;
    private TopRenderLayout mTopRenderLayout;
    private TopGroundRenderPointLayout mTopGroundRenderPointLayout;
    private BottomGroundRenderPointMenuLayout mBottomGroundRenderPointMenuLayout;
    private GroundRenderPointTypeLayout mGroundRenderPointTypeLayout;
    private GroundRenderPointAddGeoPointLayout mGroundRenderPointAddGeoPointLayout;
    private TopGroundRenderLineLayout mTopGroundRenderLineLayout;
    private BottomGroundRenderLineMenuLayout mBottomGroundRenderLineMenuLayout;
    private GroundRenderLineTypeLayout mGroundRenderLineTypeLayout;
    private GroundRenderLineAddGeoPointLayout mGroundRenderLineAddGeoPointLayout;
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
        mBottomLocationInfoLayout = new BottomLocationInfoLayout(mMainActivity);
        mBottomLocationInfoLayout.init();

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

        mGroundRenderPointAddGeoPointLayout = new GroundRenderPointAddGeoPointLayout(mMainActivity);
        mGroundRenderPointAddGeoPointLayout.init();

        mTopGroundRenderLineLayout = new TopGroundRenderLineLayout(mMainActivity);
        mTopGroundRenderLineLayout.init();

        mBottomGroundRenderLineMenuLayout = new BottomGroundRenderLineMenuLayout(mMainActivity);
        mBottomGroundRenderLineMenuLayout.init();

        mGroundRenderLineTypeLayout = new GroundRenderLineTypeLayout(mMainActivity);
        mGroundRenderLineTypeLayout.init();

        mGroundRenderLineAddGeoPointLayout = new GroundRenderLineAddGeoPointLayout(mMainActivity);
        mGroundRenderLineAddGeoPointLayout.init();

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
        mGroundRenderPointTypeLayout.dismiss();
        mGroundRenderLineTypeLayout.dismiss();
        mGroundRenderPointAddGeoPointLayout.dismiss();
        mGroundRenderLineAddGeoPointLayout.dismiss();
        mGroundRenderPlaneAddGeoPointLayout.dismiss();
        mDrawDistanceAzimuthAddGeoPointLayout.dismiss();
        mDrawAreaGirthAddGeoPointLayout.dismiss();
        mUserLoginLayout.dismiss();
        mUserRegisterLayout.dismiss();
        mUserLogoutLayout.dismiss();
    }

    public BottomLocationInfoLayout getBottomLocationInfoLayout() {
        return mBottomLocationInfoLayout;
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

    public GroundRenderPointAddGeoPointLayout getGroundRenderPointAddGeoPointLayout() {
        return mGroundRenderPointAddGeoPointLayout;
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

    public GroundRenderLineAddGeoPointLayout getGroundRenderLineAddGeoPointLayout() {
        return mGroundRenderLineAddGeoPointLayout;
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
