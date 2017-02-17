package com.gh.emap.layout;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.model.GroundRenderLine;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2017/1/9.
 * 地物绘制-画线 底部布局
 */

public class BottomGroundRenderLineLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private WheelView mWheelViewOne; // 滚动选择器
    private WheelView mWheelViewTwo; // 滚动选择器
    private GroundRenderLine mGroundRenderLine; // 地物绘制-画线数据
    private int mDefaultSelectedItemOne; // 滚动选择器数据默认选中项
    private int mDefaultSelectedItemTwo; // 滚动选择器数据默认选中项
    private int mLastSelectedItemOne; // 滚动选择器数据上次选中项
    private int mLastSelectedItemTwo; // 滚动选择器数据上次选中项
    private int mCurrentSelectedItemOne; // 滚动选择器数据选中项
    private int mCurrentSelectedItemTwo; // 滚动选择器数据选中项

    public BottomGroundRenderLineLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.bottom_ground_render__line);
        mWheelViewOne = (WheelView)mMainActivity.findViewById(R.id.line_scroll_one);
        mWheelViewTwo = (WheelView)mMainActivity.findViewById(R.id.line_scroll_two);
        mGroundRenderLine = mMainActivity.getMainManager().getFileManager().getGroundRenderLineFile().getShapLine();
        mDefaultSelectedItemOne = 0;
        mDefaultSelectedItemTwo = 0;
        mLastSelectedItemOne = -1;
        mLastSelectedItemTwo = -1;
        mCurrentSelectedItemOne = -1;
        mCurrentSelectedItemTwo = -1;

        int wheelCount = 3;

        HashMap<String, ArrayList<String>> hashMap = mGroundRenderLine.getData();
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

        mWheelViewOne.setOnWheelItemSelectedListener(mMainActivity.getMainManager().getListenerManager().getBottomGroundRenderLineListener());
        mWheelViewTwo.setOnWheelItemSelectedListener(mMainActivity.getMainManager().getListenerManager().getBottomGroundRenderLineListener());

         // 初始化画线类型
        ((TextView)mMainActivity.findViewById(R.id.line_type)).setText((String)mWheelViewTwo.getSelectedItem());
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
        if(mLayout.getVisibility() == View.VISIBLE) {
            return;
        }

        mLayout.setVisibility(View.VISIBLE);
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置

        if(mLayout.getVisibility() == View.GONE) {
            return;
        }

        mLayout.setVisibility(View.GONE);
    }

    // 判断布局的隐藏性
    public int getVisibility() {
        // View.VISIBLE    可见
        // View.INVISIBLE    不可见但是占用布局空间
        // View.GONE    不可见也不占用布局空搜索间
        return mLayout.getVisibility();
    }

    // 上次数据清理
    public void clear() {
        mWheelViewOne.setSelection(0);
        mWheelViewTwo.setSelection(0);
    }
}
