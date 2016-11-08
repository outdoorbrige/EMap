package cn.com.sgcc.epri.emap.listener;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.wx.wheelview.widget.WheelView;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.base.MainActivityContext;

/**
 * Created by GuHeng on 2016/11/2.
 */
public class DrawPointListener extends MainActivityContext
        implements View.OnClickListener,
        WheelView.OnWheelItemSelectedListener<String>,
        View.OnFocusChangeListener,
        View.OnTouchListener
{

    // 构造函数
    public DrawPointListener(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.point_type:
                onClickedPointType();
                break;
            case R.id.point_name:
                onClickedPointName();
                break;
            case R.id.point_save:
                onClickedPointSave();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(int position, String item) {
        if(mMainActivity.getLayoutManger().getDrawPointBottomLayout().getWheelViewOne().isFocused()) {
            ((TextView)mMainActivity.findViewById(R.id.point_type)).setText(
                    (String)mMainActivity.getLayoutManger().getDrawPointBottomLayout().getWheelViewTwo().getSelectionItem());
        } else if(mMainActivity.getLayoutManger().getDrawPointBottomLayout().getWheelViewTwo().isFocused()) {
            ((TextView)mMainActivity.findViewById(R.id.point_type)).setText(
                    (String)mMainActivity.getLayoutManger().getDrawPointBottomLayout().getWheelViewTwo().getSelectionItem());
        } else {

        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        if(!hasFocus) {
            if(!mMainActivity.getLayoutManger().getDrawPointBottomLayout().getWheelViewOne().isFocused() &&
                    !mMainActivity.getLayoutManger().getDrawPointBottomLayout().getWheelViewTwo().isFocused()) {
                mMainActivity.getLayoutManger().getDrawPointBottomLayout().hide();
                mMainActivity.getLayoutManger().getActionLayout().show();
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            default:
                break;
        }

        return true;
    }

    // 画点类型
    private void onClickedPointType() {
        mMainActivity.getLayoutManger().getDrawPointBottomLayout().show();
        mMainActivity.getLayoutManger().getActionLayout().hide();

        ((TextView)mMainActivity.findViewById(R.id.point_type)).setText(
                (String)mMainActivity.getLayoutManger().getDrawPointBottomLayout().getWheelViewTwo().getSelectionItem());
    }

    // 画点名称
    private void onClickedPointName() {

    }

    // 保存
    private void onClickedPointSave() {
        mMainActivity.getLayoutManger().getDrawPointTopLayout().hide();
        mMainActivity.getLayoutManger().getDrawPointBottomLayout().hide();
        mMainActivity.getLayoutManger().getActionLayout().show();
    }
}
