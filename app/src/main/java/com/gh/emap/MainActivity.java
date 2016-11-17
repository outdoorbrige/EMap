package com.gh.emap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.gh.emap.manager.LogManager;
import com.gh.emap.manager.MainManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private MainManager mMainManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mMainManager = new MainManager(this);
        this.mMainManager.init();

        this.mMainManager.getLogManager().log(this.getClass(), LogManager.LogLevel.mInfo,
                String.format("像素(长*宽):%d * %d, 像素密度:%f, 设备独立像素(长*宽):%d * %d",
                        this.getResources().getDisplayMetrics().widthPixels,
                        this.getResources().getDisplayMetrics().heightPixels,
                        this.getResources().getDisplayMetrics().density,
                        (int)(Math.round(this.getResources().getDisplayMetrics().widthPixels / this.getResources().getDisplayMetrics().density)),
                        (int)(Math.round(this.getResources().getDisplayMetrics().heightPixels / this.getResources().getDisplayMetrics().density))));
    }

    public MainManager getMainManager() {
        return this.mMainManager;
    }

    // 当一个Activity变为显示时被调用
    @Override
    protected void onStart() {
        this.mMainManager.getMapManager().enableTMyLocationOverlay();
        this.mMainManager.getMapManager().updateCurrentGeoPoint();
        super.onStart();
    }

    // 重新启动Activity时调用，总是在onStart方法以后执行
    @Override
    protected void onRestart() {
        this.mMainManager.getMapManager().enableTMyLocationOverlay();
        this.mMainManager.getMapManager().updateCurrentGeoPoint();
        super.onRestart();
    }

    // 暂停Activity时被调用
    @Override
    protected void onPause() {
        this.mMainManager.getMapManager().disableTMyLocationOverlay();
        super.onPause();
    }

    // 当Activity由暂停变为活动状态时调用
    @Override
    protected void onResume() {
        this.mMainManager.getMapManager().enableTMyLocationOverlay();
        this.mMainManager.getMapManager().updateCurrentGeoPoint();
        super.onResume();
    }

    // 停止Activity时被调用
    @Override
    protected void onStop() {
        this.mMainManager.getMapManager().disableTMyLocationOverlay();
        super.onStop();
    }

    // 销毁Activity时被调用
    @Override
    protected void onDestroy() {
        this.mMainManager.getMapManager().disableTMyLocationOverlay();
        this.mMainManager.unInit();
        super.onDestroy();
    }

    // 分发触摸事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            View view = findViewById(R.id.bottom_shap_point);
            if(!isTouchedView(view, motionEvent)) { // 隐藏BottomShapPointLayout布局
                this.getMainManager().getLayoutManager().getBottomShapPointLayout().hide();
                this.getMainManager().getLayoutManager().getMenuLayout().show();
                this.getMainManager().getLayoutManager().getOperationLayout().show();
            }

            return super.dispatchTouchEvent(motionEvent);
        }

        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(motionEvent)) {
            return true;
        }

        return onTouchEvent(motionEvent);
    }

    // 判断是否触摸了View
    public  boolean isTouchedView(View view, MotionEvent motionEvent) {
        if (view != null) {
            int[] leftTop = { 0, 0 };

            //获取View当前的location位置
            view.getLocationInWindow(leftTop);

            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + view.getHeight();
            int right = left + view.getWidth();

            if ((left < motionEvent.getX() && motionEvent.getX() < right) &&
                    (top < motionEvent.getY() && motionEvent.getY() < bottom)) {
                // 点击的是View区域，保留点击View的事件
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    // 获取应用程序名称
    public String getApplationName() {
        return "EMap";
    }

    // 获取当前日期字符串
    public String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        return simpleDateFormat.format(date);
    }
}
