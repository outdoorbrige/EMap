package com.gh.emap.layoutA;

import android.app.AlertDialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.modelA.GroundRenderPoint;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2016/11/10.
 * 地物绘制-画点 类型布局
 */
public class GroundRenderPointTypeLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private AlertDialog mAlertDialog; // 弹出式对话框
    private WheelView mWheelViewOne; // 滚动选择器
    private WheelView mWheelViewTwo; // 滚动选择器
    private Button mCancel;
    private Button mOk;
    private GroundRenderPoint mGroundRenderPoint; // 地物绘制-画点数据
    private int mDefaultSelectedItemOne; // 滚动选择器数据默认选中项
    private int mDefaultSelectedItemTwo; // 滚动选择器数据默认选中项
    private int mLastSelectedItemOne; // 滚动选择器数据上次选中项
    private int mLastSelectedItemTwo; // 滚动选择器数据上次选中项
    private int mCurrentSelectedItemOne; // 滚动选择器数据选中项
    private int mCurrentSelectedItemTwo; // 滚动选择器数据选中项

    public GroundRenderPointTypeLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        View layout = mMainActivity.getLayoutInflater().inflate(R.layout.ground_render_point_type, null, false);

        mLayout = layout.findViewById(R.id.ground_render_point_type);
        mWheelViewOne = (WheelView)layout.findViewById(R.id.point_type_scroll_one);
        mWheelViewTwo = (WheelView)layout.findViewById(R.id.point_type_scroll_two);
        mCancel = (Button)layout.findViewById(R.id.point_type_cancel);
        mOk = (Button)layout.findViewById(R.id.point_type_ok);

        mGroundRenderPoint = mMainActivity.getMainManager().getFileManager().getGroundRenderPointFile().getShapPoint();
        mDefaultSelectedItemOne = 0;
        mDefaultSelectedItemTwo = 0;
        mLastSelectedItemOne = -1;
        mLastSelectedItemTwo = -1;
        mCurrentSelectedItemOne = -1;
        mCurrentSelectedItemTwo = -1;

        int wheelCount = 3;

        HashMap<String, ArrayList<String>> hashMap = mGroundRenderPoint.getData();
        ArrayList<String> listOne = new ArrayList(hashMap.keySet());

        WheelView.Skin skin = WheelView.Skin.Holo;

        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();

        //style.backgroundColor = ;
        style.holoBorderColor = Color.LTGRAY;
        style.textColor = Color.LTGRAY;
        style.selectedTextColor = Color.BLACK;
        //style.textSize = ;
        //style.selectedTextSize = ;
        //style.textAlpha = ;
        //style.selectedTextZoom = ;

        // 初始化第一个滚动选择器
        mWheelViewOne.setWheelAdapter(new ArrayWheelAdapter(mMainActivity)); // 文本数据源
        mWheelViewOne.setSkin(skin);
        mWheelViewOne.setWheelSize(wheelCount);
        mWheelViewOne.setWheelData(listOne);
        mWheelViewOne.setStyle(style);

        ArrayList<String> listTwo = hashMap.get(mWheelViewOne.getSelectionItem());

        // 初始化第二个滚动选择器并更新类型
        mWheelViewTwo.setWheelAdapter(new ArrayWheelAdapter(mMainActivity)); // 文本数据源
        mWheelViewTwo.setSkin(skin);
        mWheelViewTwo.setWheelSize(wheelCount);
        mWheelViewTwo.setWheelData(listTwo);
        mWheelViewTwo.setStyle(style);

        // 联动关联
        mWheelViewOne.join(mWheelViewTwo);
        mWheelViewOne.joinDatas(hashMap);

        mWheelViewOne.setOnWheelItemSelectedListener(mMainActivity.getMainManager().getListenerManager().getGroundRenderPointTypeListener());
        mWheelViewTwo.setOnWheelItemSelectedListener(mMainActivity.getMainManager().getListenerManager().getGroundRenderPointTypeListener());

        mCancel.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getGroundRenderPointTypeListener());
        mOk.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getGroundRenderPointTypeListener());

        // 初始化画点类型
        ((TextView)mMainActivity.findViewById(R.id.point_type)).setText((String)mWheelViewTwo.getSelectedItem());

        mAlertDialog = new AlertDialog.Builder(mMainActivity).create();
        mAlertDialog.setView(layout);
        mAlertDialog.setCancelable(false); // 点击对话框外地方是否不消失
    }

    // 重设选项
    public void reset(int oneIndex, int twoIndex) {
        mWheelViewOne.setSelection(oneIndex);
        mWheelViewTwo.setSelection(twoIndex);
    }

    // 显示对话框
    public void show(int oneIndex, int twoIndex) {
        mAlertDialog.show();

        reset(oneIndex, twoIndex);
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

    // 获取第一个滚动选择器的内容
    public String getOneWheelViewText() {
        return mWheelViewOne.getSelectionItem().toString();
    }

    // 获取第二个滚动选择器的内容
    public String getWheelViewTwoText() {
        return mWheelViewTwo.getSelectionItem().toString();
    }
}
