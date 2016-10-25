package cn.com.sgcc.epri.emap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.log4j.Logger;

import cn.com.sgcc.epri.emap.manger.DialogManger;
import cn.com.sgcc.epri.emap.manger.FileManger;
import cn.com.sgcc.epri.emap.manger.LayoutManger;
import cn.com.sgcc.epri.emap.manger.Log4jManger;
import cn.com.sgcc.epri.emap.manger.MapManger;
import cn.com.sgcc.epri.emap.manger.UserManager;
import cn.com.sgcc.epri.emap.manger.WebServiceManger;
import cn.com.sgcc.epri.emap.model.ConfigInfo;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.DisplayMetricsUtil;
import cn.com.sgcc.epri.emap.util.PhoneResources;

/**
 * Created by GuHeng on 2016/9/18.
 * 主类
 */
public class MainActivity extends AppCompatActivity{
    private Log4jManger mLog4jManger; // 日志管理类
    private Logger mLogger; // 日志对象
    private UserManager mUserManager; // 用户信息管理类
    private FileManger mFileManger; // 文件管理类
    private DialogManger DialogManger; // 对话框管理类
    private LayoutManger mLayoutManger; // 布局管理类
    private MapManger mMapManger; // 地图管理类
    private WebServiceManger mWebServiceManger; // 服务管理类

    // 创建Activity时调用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // 警告:
        //      程序默认手机必须有内存卡

        // 初始化日志
        mLog4jManger.init(PhoneResources.getLogFile(this));
        mLogger = Logger.getLogger(this.getClass());

        // 初始化用户信息管理类
        mUserManager = new UserManager(this);
        mUserManager.init();

        // 初始化文件管理类
        mFileManger = new FileManger(this);
        mFileManger.init();

        // 初始化天地图
        mMapManger = new MapManger(this);
        mMapManger.init();
        mMapManger.enableFeatures();
        mMapManger.setCenter();

        // 初始化对话框
        DialogManger = new DialogManger(this);
        DialogManger.init();

        // 初始化布局
        mLayoutManger = new LayoutManger(this, mMapManger);
        mLayoutManger.init();

        mLogger.info(String.format("像素(长*宽):%d * %d, 像素密度:%f, 设备独立像素(长*宽):%d * %d",
                DisplayMetricsUtil.getWidthPx(this),
                DisplayMetricsUtil.getHeightPx(this),
                this.getResources().getDisplayMetrics().density,
                DisplayMetricsUtil.getWidthDp(this),
                DisplayMetricsUtil.getHeightDp(this)));

        // 初始化服务
        mWebServiceManger = new WebServiceManger(this);
        mWebServiceManger.init();
    }

    // 当一个Activity变为显示时被调用
    @Override
    protected void onStart() {
        mMapManger.enableFeatures();
        super.onStart();
    }

    // 重新启动Activity时调用，总是在onStart方法以后执行
    @Override
    protected void onRestart() {
        mMapManger.enableFeatures();
        super.onRestart();
    }

    // 暂停Activity时被调用
    @Override
    protected void onPause() {
        mMapManger.disableFeatures();
        super.onPause();
    }

    // 当Activity由暂停变为活动状态时调用
    @Override
    protected void onResume() {
        mMapManger.enableFeatures();
        super.onResume();
    }

    // 停止Activity时被调用
    @Override
    protected void onStop() {
        mMapManger.disableFeatures();
        super.onStop();
    }

    // 销毁Activity时被调用
    @Override
    protected void onDestroy() {
        mMapManger.disableFeatures();
        DialogManger.unInit();
        super.onDestroy();
    }

    // 获取配置文件信息
    public ConfigInfo getConfigInfo() {
        return mFileManger.getConfigInfo();
    }

    // 获取用户信息
    public UserInfo getUserInfo() {
        return mUserManager.getUserInfo();
    }

    // 获取地图管理类
    public MapManger getMapManger() {
        return mMapManger;
    }

    // 获取对话框管理类
    public DialogManger getDialogManger() {
        return DialogManger;
    }

    // 获取服务管理类
    public WebServiceManger getWebServiceManger() {
        return mWebServiceManger;
    }
}