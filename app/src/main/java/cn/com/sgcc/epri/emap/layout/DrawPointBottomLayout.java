package cn.com.sgcc.epri.emap.layout;

import android.graphics.Color;
import android.widget.TextView;

import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.base.BaseLayout;
import cn.com.sgcc.epri.emap.listener.DrawPointListener;
import cn.com.sgcc.epri.emap.model.ShapPointInfo;

/**
 * Created by GuHeng on 2016/11/7.
 * 地物编辑-画点底部布局布局
 */
public class DrawPointBottomLayout extends BaseLayout {
    private WheelView mWheelViewOne; // 滚动选择器
    private WheelView mWheelViewTwo; // 滚动选择器
    private ShapPointInfo mShapPointInfo; // 地物编辑-画点数据
    private int mDefaultSelectedItemOne; // 滚动选择器数据默认选中项
    private int mDefaultSelectedItemTwo; // 滚动选择器数据默认选中项
    private int mLastSelectedItemOne; // 滚动选择器数据上次选中项
    private int mLastSelectedItemTwo; // 滚动选择器数据上次选中项
    private int mCurrentSelectedItemOne; // 滚动选择器数据选中项
    private int mCurrentSelectedItemTwo; // 滚动选择器数据选中项

    // 构造函数
    public DrawPointBottomLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        setLayout(mMainActivity.findViewById(R.id.draw_point_bottom_layout));
        mWheelViewOne = (WheelView)mMainActivity.findViewById(R.id.point_scroll_one);
        mWheelViewTwo = (WheelView)mMainActivity.findViewById(R.id.point_scroll_two);
        mShapPointInfo = mMainActivity.getFileManager().getShapPointInfo();
        mDefaultSelectedItemOne = 0;
        mDefaultSelectedItemTwo = 0;
        mLastSelectedItemOne = -1;
        mLastSelectedItemTwo = -1;
        mCurrentSelectedItemOne = -1;
        mCurrentSelectedItemTwo = -1;

        int wheelCount = 3;

        HashMap<String, List<String>> hashMap = mMainActivity.getFileManager().getShapPointInfo().getData();
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
        mWheelViewOne.setWheelAdapter(new ArrayWheelAdapter(mMainActivity)); // 文本数据源
        mWheelViewOne.setSkin(skin);
        mWheelViewOne.setWheelSize(wheelCount);
        mWheelViewOne.setWheelData(listOne);
        mWheelViewOne.setStyle(style);

        List<String> listTwo = hashMap.get(mWheelViewOne.getSelectionItem());

        // 初始化第二个滚动选择器并更新类型
        mWheelViewTwo.setWheelAdapter(new ArrayWheelAdapter(mMainActivity)); // 文本数据源
        mWheelViewTwo.setSkin(skin);
        mWheelViewTwo.setWheelSize(wheelCount);
        mWheelViewTwo.setWheelData(listTwo);
        mWheelViewTwo.setStyle(style);

        // 联动关联
        mWheelViewOne.join(mWheelViewTwo);
        mWheelViewOne.joinDatas(hashMap);

        // 初始化画点类型
        ((TextView)mMainActivity.findViewById(R.id.point_type)).setText((String)mWheelViewTwo.getSelectedItem());

        DrawPointListener listener = new DrawPointListener(mMainActivity);

        mWheelViewOne.setOnWheelItemSelectedListener(listener);
        mWheelViewTwo.setOnWheelItemSelectedListener(listener);

        mWheelViewOne.setOnFocusChangeListener(listener);
        mWheelViewTwo.setOnFocusChangeListener(listener);
    }

    // 获取第一个滚动选择器
    public WheelView getWheelViewOne() {
        return mWheelViewOne;
    }

    // 获取第二个滚动选择器
    public WheelView getWheelViewTwo() {
        return mWheelViewTwo;
    }

    // 布局显示
    public void show() {
        super.show();
    }

    // 布局隐藏
    public void hide() {
        super.hide();
        clear();
    }

    // 布局数据清理
    private void clear() {
        mWheelViewOne.setSelection(0);
        mWheelViewTwo.setSelection(0);
    }
}
