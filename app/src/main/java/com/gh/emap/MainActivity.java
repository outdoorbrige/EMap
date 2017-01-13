package com.gh.emap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

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

        mMainManager = new MainManager(this);
        mMainManager.init();

        mMainManager.getLogManager().log(getClass(), LogManager.LogLevel.mInfo,
                String.format("像素(长*宽):%d * %d, 像素密度:%f, 设备独立像素(长*宽):%d * %d",
                        getResources().getDisplayMetrics().widthPixels,
                        getResources().getDisplayMetrics().heightPixels,
                        getResources().getDisplayMetrics().density,
                        (int)(Math.round(getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().density)),
                        (int)(Math.round(getResources().getDisplayMetrics().heightPixels / getResources().getDisplayMetrics().density))));
    }

    public MainManager getMainManager() {
        return mMainManager;
    }

    // 当一个Activity变为显示时被调用
    @Override
    protected void onStart() {
        mMainManager.getMapManager().enableTMyLocationOverlay();
        mMainManager.getMapManager().updateCurrentGeoPoint();
        super.onStart();
    }

    // 重新启动Activity时调用，总是在onStart方法以后执行
    @Override
    protected void onRestart() {
        mMainManager.getMapManager().enableTMyLocationOverlay();
        mMainManager.getMapManager().updateCurrentGeoPoint();
        super.onRestart();
    }

    // 暂停Activity时被调用
    @Override
    protected void onPause() {
        mMainManager.getMapManager().disableTMyLocationOverlay();
        super.onPause();
    }

    // 当Activity由暂停变为活动状态时调用
    @Override
    protected void onResume() {
        mMainManager.getMapManager().enableTMyLocationOverlay();
        mMainManager.getMapManager().updateCurrentGeoPoint();
        super.onResume();
    }

    // 停止Activity时被调用
    @Override
    protected void onStop() {
        mMainManager.getMapManager().disableTMyLocationOverlay();
        super.onStop();
    }

    // 销毁Activity时被调用
    @Override
    protected void onDestroy() {
        mMainManager.getMapManager().disableTMyLocationOverlay();
        mMainManager.unInit();
        super.onDestroy();
    }

    // 分发触摸事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            View view = findViewById(R.id.bottom_shap_point);
            if(!isTouchedView(view, motionEvent)) { // 隐藏BottomShapPointLayout布局
                getMainManager().getLayoutManager().getBottomShapPointLayout().hide();
                getMainManager().getLayoutManager().getMenuLayout().show();
                getMainManager().getLayoutManager().getOperationLayout().show();
            }

            view = findViewById(R.id.bottom_shap_line);
            if(!isTouchedView(view, motionEvent)) { // 隐藏BottomShapLineLayout布局
                getMainManager().getLayoutManager().getBottomShapLineLayout().hide();
                getMainManager().getLayoutManager().getMenuLayout().show();
                getMainManager().getLayoutManager().getOperationLayout().show();
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
