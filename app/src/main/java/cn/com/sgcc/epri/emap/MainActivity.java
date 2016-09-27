package cn.com.sgcc.epri.emap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.com.sgcc.epri.emap.manager.LayoutMgr;
import cn.com.sgcc.epri.emap.manager.LoggerMgr;

/**
 * Created by GuHeng on 2016/9/18.
 * 主类
 */
public class MainActivity extends AppCompatActivity{
    private LoggerMgr logger; // 日志管理类
    private LayoutMgr layout; // 布局管理类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emap_view);

        logger = new LoggerMgr(this);
        layout = new LayoutMgr(this);

        logger.init();
        layout.init();
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
}