package com.gh.emap.layout;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.model.ShapPoint;
import com.gh.emap.listener.TopEditListener;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by GuHeng on 2016/11/10.
 * 地物编辑-画点 底部布局
 */
public class BottomShapPointLayout {
    private Context mContext;
    private View mLayout; // 布局
    private WheelView mWheelViewOne; // 滚动选择器
    private WheelView mWheelViewTwo; // 滚动选择器
    private ShapPoint mShapPoint; // 地物编辑-画点数据
    private int mDefaultSelectedItemOne; // 滚动选择器数据默认选中项
    private int mDefaultSelectedItemTwo; // 滚动选择器数据默认选中项
    private int mLastSelectedItemOne; // 滚动选择器数据上次选中项
    private int mLastSelectedItemTwo; // 滚动选择器数据上次选中项
    private int mCurrentSelectedItemOne; // 滚动选择器数据选中项
    private int mCurrentSelectedItemTwo; // 滚动选择器数据选中项

    public BottomShapPointLayout(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mLayout = ((MainActivity)this.mContext).findViewById(R.id.bottom_shap_point);
        this.mWheelViewOne = (WheelView)((MainActivity)this.mContext).findViewById(R.id.point_scroll_one);
        this.mWheelViewTwo = (WheelView)((MainActivity)this.mContext).findViewById(R.id.point_scroll_two);
        this.mShapPoint = ((MainActivity)this.mContext).getMainManager().getFileManager().getShapPointFile().getShapPoint();
        this.mDefaultSelectedItemOne = 0;
        this.mDefaultSelectedItemTwo = 0;
        this.mLastSelectedItemOne = -1;
        this.mLastSelectedItemTwo = -1;
        this.mCurrentSelectedItemOne = -1;
        this.mCurrentSelectedItemTwo = -1;

        int wheelCount = 3;

        HashMap<String, List<String>> hashMap = this.mShapPoint.getData();
        List<String> listOne = new ArrayList(hashMap.keySet());

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
        this.mWheelViewOne.setWheelAdapter(new ArrayWheelAdapter(this.mContext)); // 文本数据源
        this.mWheelViewOne.setSkin(skin);
        this.mWheelViewOne.setWheelSize(wheelCount);
        this.mWheelViewOne.setWheelData(listOne);
        this.mWheelViewOne.setStyle(style);

        List<String> listTwo = hashMap.get(this.mWheelViewOne.getSelectionItem());

        // 初始化第二个滚动选择器并更新类型
        this.mWheelViewTwo.setWheelAdapter(new ArrayWheelAdapter(this.mContext)); // 文本数据源
        this.mWheelViewTwo.setSkin(skin);
        this.mWheelViewTwo.setWheelSize(wheelCount);
        this.mWheelViewTwo.setWheelData(listTwo);
        this.mWheelViewTwo.setStyle(style);

        // 联动关联
        this.mWheelViewOne.join(this.mWheelViewTwo);
        this.mWheelViewOne.joinDatas(hashMap);

        this.mWheelViewOne.setOnWheelItemSelectedListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopEditListener());
        this.mWheelViewTwo.setOnWheelItemSelectedListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopEditListener());

        // 初始化画点类型
        ((TextView)((MainActivity)this.mContext).findViewById(R.id.point_type)).setText((String)this.mWheelViewTwo.getSelectedItem());
    }

    // 获取第一个滚动选择器
    public WheelView getWheelViewOne() {
        return this.mWheelViewOne;
    }

    // 获取第二个滚动选择器
    public WheelView getWheelViewTwo() {
        return this.mWheelViewTwo;
    }

    // 显示布局
    public void show() {
        this.mLayout.setVisibility(View.VISIBLE);
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置
        this.mLayout.setVisibility(View.GONE);
    }

    // 上次数据清理
    public void clear() {
        this.mWheelViewOne.setSelection(0);
        this.mWheelViewTwo.setSelection(0);
    }
}
