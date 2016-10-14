package cn.com.sgcc.epri.emap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.log4j.Logger;

import cn.com.sgcc.epri.emap.manager.DialogMgr;
import cn.com.sgcc.epri.emap.manager.LayoutMgr;
import cn.com.sgcc.epri.emap.manager.Log4jMgr;
import cn.com.sgcc.epri.emap.manager.MapMgr;
import cn.com.sgcc.epri.emap.manager.WebServiceMgr;
import cn.com.sgcc.epri.emap.util.DisplayMetricsUtil;
import cn.com.sgcc.epri.emap.util.PhoneResources;

/**
 * Created by GuHeng on 2016/9/18.
 * 主类
 */
public class MainActivity extends AppCompatActivity{
    private Log4jMgr log4j_mgr; // 日志管理类
    private Logger logger; // 日志对象
    private DialogMgr dlg_mgr; // 对话框管理类
    private LayoutMgr layout_mgr; // 布局管理类
    private MapMgr map_mgr; // 地图管理类
    private WebServiceMgr service_mgr; // 服务管理类

    // 创建Activity时调用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emap_view);

        // 警告:
        //      程序默认手机必须有内存卡

        // 初始化日志
        log4j_mgr.init(PhoneResources.getLogFile(this));
        logger = Logger.getLogger(this.getClass());

        // 初始化天地图
        map_mgr = new MapMgr(this);
        map_mgr.init();
        map_mgr.enableFeatures();
        map_mgr.setCenter();

        // 初始化对话框
        dlg_mgr = new DialogMgr(this);
        dlg_mgr.init();

        // 初始化布局
        layout_mgr = new LayoutMgr(this, map_mgr);
        layout_mgr.init();

        logger.info(String.format("像素(长*宽):%d * %d, 像素密度:%f, 设备独立像素(长*宽):%d * %d",
                DisplayMetricsUtil.getWidthPx(this),
                DisplayMetricsUtil.getHeightPx(this),
                this.getResources().getDisplayMetrics().density,
                DisplayMetricsUtil.getWidthDp(this),
                DisplayMetricsUtil.getHeightDp(this)));

        // 初始化服务
        service_mgr = new WebServiceMgr(this);
        service_mgr.init();
    }

    // 当一个Activity变为显示时被调用
    @Override
    protected void onStart() {
        map_mgr.enableFeatures();
        super.onStart();
    }

    // 重新启动Activity时调用，总是在onStart方法以后执行
    @Override
    protected void onRestart() {
        map_mgr.enableFeatures();
        super.onRestart();
    }

    // 暂停Activity时被调用
    @Override
    protected void onPause() {
        map_mgr.disableFeatures();
        super.onPause();
    }

    // 当Activity由暂停变为活动状态时调用
    @Override
    protected void onResume() {
        map_mgr.enableFeatures();
        super.onResume();
    }

    // 停止Activity时被调用
    @Override
    protected void onStop() {
        map_mgr.disableFeatures();
        super.onStop();
    }

    // 销毁Activity时被调用
    @Override
    protected void onDestroy() {
        map_mgr.disableFeatures();
        dlg_mgr.uninit();
        super.onDestroy();
    }

    // 获取地图管理类
    public MapMgr getMapMgr() {
        return map_mgr;
    }

    // 获取对话框管理类
    public DialogMgr getDlgMgr() {
        return dlg_mgr;
    }

    // 获取服务管理类
    public WebServiceMgr getServiceMgr() {
        return service_mgr;
    }
}