package cn.com.sgcc.epri.emap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.com.sgcc.epri.emap.manager.LayoutMgr;
import cn.com.sgcc.epri.emap.manager.LoggerMgr;
import cn.com.sgcc.epri.emap.manager.MapMgr;

/**
 * Created by GuHeng on 2016/9/18.
 * 主类
 */
public class MainActivity extends AppCompatActivity{
    private LoggerMgr logger_mgr; // 日志管理类
    private LayoutMgr layout_mgr; // 布局管理类
    private MapMgr map_mgr; // 地图管理类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emap_view);

        // 初始化日志管理
        logger_mgr = new LoggerMgr(this);
        logger_mgr.init();

        // 初始化天地图
        map_mgr = new MapMgr(this);
        map_mgr.init();
        map_mgr.setCenter();

        // 初始化布局
        layout_mgr = new LayoutMgr(this, map_mgr);
        layout_mgr.init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        map_mgr.uninit();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    // 获取地图管理类
    public MapMgr getMapMgr() {
        return map_mgr;
    }
}