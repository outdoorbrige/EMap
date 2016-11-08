package cn.com.sgcc.epri.emap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.com.sgcc.epri.emap.manager.DialogManager;
import cn.com.sgcc.epri.emap.manager.FileManager;
import cn.com.sgcc.epri.emap.manager.LayoutManager;
import cn.com.sgcc.epri.emap.manager.Log4jManager;
import cn.com.sgcc.epri.emap.manager.MapManager;
import cn.com.sgcc.epri.emap.manager.UserManager;
import cn.com.sgcc.epri.emap.manager.WebServiceManager;
import cn.com.sgcc.epri.emap.util.DisplayMetrics;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.util.PhoneResources;

/**
 * Created by GuHeng on 2016/9/18.
 * 主类
 */
public class MainActivity extends AppCompatActivity{
    private Log4jManager mLog4jManager; // 日志管理类
    private UserManager mUserManager; // 用户信息管理类
    private FileManager mFileManager; // 文件管理类
    private DialogManager mDialogManager; // 对话框管理类
    private LayoutManager mLayoutManger; // 布局管理类
    private MapManager mMapManager; // 地图管理类
    private WebServiceManager mWebServiceManager; // 服务管理类

    // 创建Activity时调用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        // 警告:
        //      程序默认手机必须有内存卡

        // 初始化日志
        mLog4jManager = new Log4jManager(this);
        mLog4jManager.init(PhoneResources.getLogFile(this));

        // 初始化用户信息管理类
        mUserManager = new UserManager(this);
        mUserManager.init();

        // 初始化文件管理类
        mFileManager = new FileManager(this);
        mFileManager.init();

        // 初始化天地图
        mMapManager = new MapManager(this);
        mMapManager.init();

        // 初始化对话框
        mDialogManager = new DialogManager(this);
        mDialogManager.init();

        // 初始化布局
        mLayoutManger = new LayoutManager(this);
        mLayoutManger.init();

        getLog4jManager().log(this.getClass(), Log4jLevel.mInfo,
                String.format("像素(长*宽):%d * %d, 像素密度:%f, 设备独立像素(长*宽):%d * %d",
                DisplayMetrics.getWidthPx(this),
                DisplayMetrics.getHeightPx(this),
                this.getResources().getDisplayMetrics().density,
                DisplayMetrics.getWidthDp(this),
                DisplayMetrics.getHeightDp(this)));

        // 初始化服务
        mWebServiceManager = new WebServiceManager(this);
        mWebServiceManager.init();
    }

    // 当一个Activity变为显示时被调用
    @Override
    protected void onStart() {
        mMapManager.enableTMyLocationOverlay();
        mMapManager.updateCurrentGeoPoint();
        super.onStart();
    }

    // 重新启动Activity时调用，总是在onStart方法以后执行
    @Override
    protected void onRestart() {
        mMapManager.enableTMyLocationOverlay();
        mMapManager.updateCurrentGeoPoint();
        super.onRestart();
    }

    // 暂停Activity时被调用
    @Override
    protected void onPause() {
        mMapManager.disableTMyLocationOverlay();
        super.onPause();
    }

    // 当Activity由暂停变为活动状态时调用
    @Override
    protected void onResume() {
        mMapManager.enableTMyLocationOverlay();
        mMapManager.updateCurrentGeoPoint();
        super.onResume();
    }

    // 停止Activity时被调用
    @Override
    protected void onStop() {
        mMapManager.disableTMyLocationOverlay();
        super.onStop();
    }

    // 销毁Activity时被调用
    @Override
    protected void onDestroy() {
        mMapManager.disableTMyLocationOverlay();
        mDialogManager.unInit();
        super.onDestroy();
    }

    // 获取日志管理类
    public Log4jManager getLog4jManager() {
        return mLog4jManager;
    }

    // 获取文件管理类
    public FileManager getFileManager() {
        return mFileManager;
    }

    // 获取用户信息管理类
    public UserManager getUserManager() {
        return mUserManager;
    }

    // 获取地图管理类
    public MapManager getMapManager() {
        return mMapManager;
    }

    // 获取对话框管理类
    public DialogManager getDialogManager() {
        return mDialogManager;
    }

    // 获取布局管理类
    public LayoutManager getLayoutManger() {
        return mLayoutManger;
    }

    // 获取服务管理类
    public WebServiceManager getWebServiceManager() {
        return mWebServiceManager;
    }
}