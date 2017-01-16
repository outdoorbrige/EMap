package com.gh.emap.layout;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.model.GroundRenderPoint;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2016/11/10.
 * 地物绘制-画点 底部布局
 */
public class BottomGroundRenderPointLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private WheelView mWheelViewOne; // 滚动选择器
    private WheelView mWheelViewTwo; // 滚动选择器
    private GroundRenderPoint mGroundRenderPoint; // 地物绘制-画点数据
    private int mDefaultSelectedItemOne; // 滚动选择器数据默认选中项
    private int mDefaultSelectedItemTwo; // 滚动选择器数据默认选中项
    private int mLastSelectedItemOne; // 滚动选择器数据上次选中项
    private int mLastSelectedItemTwo; // 滚动选择器数据上次选中项
    private int mCurrentSelectedItemOne; // 滚动选择器数据选中项
    private int mCurrentSelectedItemTwo; // 滚动选择器数据选中项

    public BottomGroundRenderPointLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.bottom_ground_render_point);
        mWheelViewOne = (WheelView)mMainActivity.findViewById(R.id.point_scroll_one);
        mWheelViewTwo = (WheelView)mMainActivity.findViewById(R.id.point_scroll_two);
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

        mWheelViewOne.setOnWheelItemSelectedListener(mMainActivity.getMainManager().getListenerManager().getBottomGroundRenderPointListener());
        mWheelViewTwo.setOnWheelItemSelectedListener(mMainActivity.getMainManager().getListenerManager().getBottomGroundRenderPointListener());

        // 初始化画点类型
        ((TextView)mMainActivity.findViewById(R.id.point_type)).setText((String)mWheelViewTwo.getSelectedItem());
    }

    // 获取第一个滚动选择器
    public WheelView getWheelViewOne() {
        return mWheelViewOne;
    }

    // 获取第二个滚动选择器
    public WheelView getWheelViewTwo() {
        return mWheelViewTwo;
    }

    // 显示布局
    public void show() {
        mLayout.setVisibility(View.VISIBLE);
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置
        mLayout.setVisibility(View.GONE);
    }

    // 上次数据清理
    public void clear() {
        mWheelViewOne.setSelection(0);
        mWheelViewTwo.setSelection(0);
    }
}
