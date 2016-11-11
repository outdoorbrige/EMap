package com.gh.emap.manager;

import android.content.Context;

/**
 * Created by GuHeng on 2016/11/9.
 */
public class MainManager {
    private Context mContext;
    private LogManager mLogManager;
    private FileManager mFileManager;
    private MapManager mMapManager;
    private ListenerManager mListenerManager;
    private UserManager mUserManager;
    private LayoutManager mLayoutManager;
    private WebServiceManager mWebServiceManager;

    public MainManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mLogManager = new LogManager(this.mContext);
        this.mLogManager.init();

        this.mFileManager = new FileManager(this.mContext);
        this.mFileManager.init();

        this.mMapManager = new MapManager(this.mContext);
        this.mMapManager.init();

        this.mListenerManager = new ListenerManager(this.mContext);
        this.mListenerManager.init();

        this.mUserManager = new UserManager(this.mContext);
        this.mUserManager.init();

        this.mLayoutManager = new LayoutManager(this.mContext);
        this.mLayoutManager.init();

        this.mWebServiceManager = new WebServiceManager(this.mContext);
        this.mWebServiceManager.init();
    }

    public void unInit() {
        this.getLayoutManager().getUserLoginLayout().dimiss();
        this.getLayoutManager().getUserRegisterLayout().dimiss();
        this.getLayoutManager().getUserLogoutLayout().dimiss();
    }

    public LogManager getLogManager() {
        return this.mLogManager;
    }

    public FileManager getFileManager() {
        return this.mFileManager;
    }

    public MapManager getMapManager() {
        return this.mMapManager;
    }

    public ListenerManager getListenerManager() {
        return this.mListenerManager;
    }

    public UserManager getUserManager() {
        return this.mUserManager;
    }

    public LayoutManager getLayoutManager() {
        return this.mLayoutManager;
    }

    public WebServiceManager getWebServiceManager() {
        return this.mWebServiceManager;
    }
}
