package com.gh.emap.managerA;

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
    private ProvincesCitiesManager mProvincesCitiesManager;
    private RenderOptionManager mRenderOptionManager;
    private MapManager mMapManager;
    private NetworkManager mNetworkManager;
    private LayoutManager mLayoutManager;
    private MyLocationManager mMyLocationManager;
    private UserManager mUserManager;
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

        mProvincesCitiesManager = new ProvincesCitiesManager(mMainActivity);
        mProvincesCitiesManager.init();

        mRenderOptionManager = new RenderOptionManager(mMainActivity);
        mRenderOptionManager.init();

        mMapManager = new MapManager(mMainActivity);
        mMapManager.init();

        mNetworkManager = new NetworkManager(mMainActivity);
        mNetworkManager.init();

        mLayoutManager = new LayoutManager(mMainActivity);
        mLayoutManager.init();

        mMyLocationManager = new MyLocationManager(mMainActivity);
        mMyLocationManager.init();

        mUserManager = new UserManager(mMainActivity);
        mUserManager.init();

        mWebServiceManager = new WebServiceManager(mMainActivity);
        mWebServiceManager.init();

        mMyUserOverlaysManager = new MyUserOverlaysManager(mMainActivity);
        mMyUserOverlaysManager.init();
    }

    public void unInit() {
        mFileManager.unInit();
        mListenerManager.unInit();
        mProvincesCitiesManager.unInit();
        mRenderOptionManager.unInit();
        mMapManager.unInit();
        mUserManager.unInit();
        mWebServiceManager.unInit();
        mMyUserOverlaysManager.unInit();
        mNetworkManager.unInit();
        mLayoutManager.unInit();
        mMyLocationManager.unInit();
        mLogManager.unInit();
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

    public ProvincesCitiesManager getProvincesCitiesManager() {
        return mProvincesCitiesManager;
    }

    public RenderOptionManager getRenderOptionManager() {
        return mRenderOptionManager;
    }

    public MapManager getMapManager() {
        return mMapManager;
    }

    public NetworkManager getNetworkManager() {
        return mNetworkManager;
    }

    public LayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    public MyLocationManager getMyLocationManager() {
        return mMyLocationManager;
    }

    public UserManager getUserManager() {
        return mUserManager;
    }

    public WebServiceManager getWebServiceManager() {
        return mWebServiceManager;
    }

    public MyUserOverlaysManager getMyUserOverlaysManager() {
        return mMyUserOverlaysManager;
    }
}
