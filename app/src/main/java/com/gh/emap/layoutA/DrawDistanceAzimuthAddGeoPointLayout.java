package com.gh.emap.layoutA;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2017/3/7.
 */

public class DrawDistanceAzimuthAddGeoPointLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private AlertDialog mAlertDialog; // 弹出式对话框
    private EditText mLatitude; // 纬度
    private EditText mLongitude; // 经度
    private Button mCancel; // 取消
    private Button mOk; // 确定


    public DrawDistanceAzimuthAddGeoPointLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        View layout = mMainActivity.getLayoutInflater().inflate(R.layout.draw_distance_azimuth_add_geopoint, null, false);

        mLayout = layout.findViewById(R.id.draw_distance_azimuth_add_geopoint);
        mLatitude = (EditText) layout.findViewById(R.id.draw_distance_azimuth_add_geopoint_latitude);
        mLongitude = (EditText) layout.findViewById(R.id.draw_distance_azimuth_add_geopoint_longitude);
        mCancel = (Button) layout.findViewById(R.id.draw_distance_azimuth_add_geopoint_cancel);
        mOk = (Button) layout.findViewById(R.id.draw_distance_azimuth_add_geopoint_ok);

        mCancel.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getDrawDistanceAzimuthAddGeoPointListener());
        mOk.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getDrawDistanceAzimuthAddGeoPointListener());

        mAlertDialog = new AlertDialog.Builder(mMainActivity).create();
        mAlertDialog.setView(layout);
        mAlertDialog.setCancelable(false); // 点击对话框外地方是否不消失
    }

    public String getLatitude() {
        return mLatitude.getText().toString();
    }

    public String getLongitude() {
        return mLongitude.getText().toString();
    }

    // 清空旧数据
    private void clear() {
        mLongitude.setText("");
        mLatitude.setText("");
    }

    // 显示对话框
    public void show() {
        mAlertDialog.show();

        clear();
    }

    // 隐藏对话框
    public void hide() {
        mAlertDialog.hide();
    }

    // 销毁对话框
    public void dismiss() {
        mAlertDialog.dismiss();
    }

    // 获取对话框句柄
    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }
}
