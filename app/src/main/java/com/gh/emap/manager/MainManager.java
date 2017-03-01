package com.gh.emap.manager;

import com.gh.emap.MainActivity;

/**
 * Created by GuHeng on 2016/11/9.
 * 主管理类
 */
public class MainManager {
    private MainActivity mMainActivity;
    private LogManager mLogManager;
    private ListenerManager mListenerManager;
    private FileManager mFileManager;
    private RenderOptionManager mRenderOptionManager;
    private MapManager mMapManager;
    private MyLocationManager mMyLocationManager;
    private UserManager mUserManager;
    private LayoutManager mLayoutManager;
    private WebServiceManager mWebServiceManager;
    private MyUserOverlaysManager mMyUserOverlaysManager;

    public MainManager(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLogManager = new LogManager(mMainActivity);
        mLogManager.init();

        mListenerManager = new ListenerManager(mMainActivity);
        mListenerManager.init();

        mFileManager = new FileManager(mMainActivity);
        mFileManager.init();

        mRenderOptionManager = new RenderOptionManager(mMainActivity);
        mRenderOptionManager.init();

        mMapManager = new MapManager(mMainActivity);
        mMapManager.init();

        mMyLocationManager = new MyLocationManager(mMainActivity);
        mMyLocationManager.init();

        mUserManager = new UserManager(mMainActivity);
        mUserManager.init();

        mLayoutManager = new LayoutManager(mMainActivity);
        mLayoutManager.init();

        mWebServiceManager = new WebServiceManager(mMainActivity);
        mWebServiceManager.init();

        mMyUserOverlaysManager = new MyUserOverlaysManager(mMainActivity);
        mMyUserOverlaysManager.init();
    }

    public void unInit() {
        mLayoutManager.getGroundRenderPointTypeLayout().dimiss();
        mLayoutManager.getGroundRenderLineTypeLayout().dimiss();
        mLayoutManager.getUserLoginLayout().dimiss();
        mLayoutManager.getUserRegisterLayout().dimiss();
        mLayoutManager.getUserLogoutLayout().dimiss();
        mMyLocationManager.removeUpdates();
        mLogManager.close();
    }

    public LogManager getLogManager() {
        return mLogManager;
    }

    public ListenerManager getListenerManager() {
        return mListenerManager;
    }

    public FileManager getFileManager() {
        return mFileManager;
    }

    public RenderOptionManager getRenderOptionManager() {
        return mRenderOptionManager;
    }

    public MapManager getMapManager() {
        return mMapManager;
    }

    public MyLocationManager getMyLocationManager() {
        return mMyLocationManager;
    }

    public UserManager getUserManager() {
        return mUserManager;
    }

    public LayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    public WebServiceManager getWebServiceManager() {
        return mWebServiceManager;
    }

    public MyUserOverlaysManager getMyUserOverlaysManager() {
        return mMyUserOverlaysManager;
    }
}
