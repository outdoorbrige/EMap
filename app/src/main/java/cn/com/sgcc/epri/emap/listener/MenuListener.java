package cn.com.sgcc.epri.emap.listener;

import android.view.View;
import android.widget.Button;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import org.apache.log4j.Logger;

import java.util.ArrayList;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.util.DisplayMetricsUtil;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/9/27.
 */
public class MenuListener extends MainActivityContext implements View.OnClickListener {
    private Logger mLogger; // 日志对象
    private boolean mMenuIsOpen = false; // 控制菜单展开和折叠，默认折叠
    private ArrayList<Button> mArrayListButtons = null; // 展开和折叠菜单中控制的按钮集合
    private int mAnimationRadius = 0; // 动画半径，单位(dp) // 菜单展开半径
    private final int mLeftOffset = 15; // 菜单按钮距离屏幕左边框15dp
    private final int mAnimationCycle = 500; // 动画周期,单位毫秒(ms) // 菜单展开/折叠动画完成的时间

    private static final int ANIMATION_ACTION_CLOSE = 0; // 动画菜单折叠
    private static final int ANIMATION_ACTION_OPEN = 1; // 动画菜单展开

    // 构造函数
    public MenuListener(MainActivity context, ArrayList<Button> arrayListButtons) {
        super(context);
        mLogger = Logger.getLogger(this.getClass());
        this.mArrayListButtons = arrayListButtons;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu: // 菜单按钮
                if(mAnimationRadius == 0) {
                    mAnimationRadius = getAnimationRadius(view);
                }

                if(!mMenuIsOpen) {
                    for(int i = 0; i < mArrayListButtons.size(); i ++) {
                        doAnimateAction(ANIMATION_ACTION_OPEN, mArrayListButtons.get(i), i, mArrayListButtons.size(), mAnimationRadius);
                    }
                } else {
                    for(int i = 0; i < mArrayListButtons.size(); i ++) {
                        doAnimateAction(ANIMATION_ACTION_CLOSE, mArrayListButtons.get(i), i, mArrayListButtons.size(), mAnimationRadius);
                    }
                }

                mMenuIsOpen = !mMenuIsOpen;
                break;
            default:
                break;
        }
    }

    // 计算动画半径
    private int getAnimationRadius(View view) {
        int radius = 0;

        // 获取按钮边长(dp)
        int a = DisplayMetricsUtil.Px2Dp(context, view.getHeight());

        // 按钮对角线长度的一半(dp)
        int c = (int)Math.round(Math.sqrt(a * a * 2) / 2);

        // 一个按钮对应角度的弧度的一半
        double angleRadians = (90 / mArrayListButtons.size() / 2) * Math.PI / 180; // 角度换算成弧度

        // 计算半径(dp)
        radius = (int)Math.round(c / Math.sin(angleRadians));

        mLogger.info(String.format("按钮边长:%d, 斜边长:%d, 按钮个数:%d, 度数:%f, 正弦值:%f, 近似半径:%d, 动画半径:%d", a, c, mArrayListButtons.size(), angleRadians, Math.sin(angleRadians), radius, radius + a));

        return (radius + a);
    }

    /**
     * 菜单动画
     * @param action 动画的动作(展开/折叠)
     * @param view 执行动画的view
     * @param index 在动画序列中的位置
     * @param total 动画序列的个数
     * @param radius 动画半径
     */
    private void doAnimateAction(int action, final View view, int index, int total, int radius) {
        if(view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }

        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = (int)(radius * Math.cos(degree));
        int translationY = -(int)(radius * Math.sin(degree));

        AnimatorSet set = new AnimatorSet();

        if(action == ANIMATION_ACTION_OPEN) {
            // 包含平移、缩放和透明度动画
            set.playTogether(
                    ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                    ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                    ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                    ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                    ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
            );
        } else if(action == ANIMATION_ACTION_CLOSE) {
            // 包含平移、缩放和透明度动画
            set.playTogether(
                    ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                    ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                    ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                    ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                    ObjectAnimator.ofFloat(view, "alpha", 1f,  0f)
            );

            // 为动画加上时间监听，当动画结束的时候，把当前view隐藏
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
        }
        else {
        }

        set.setDuration(mAnimationCycle).start();
    }
}
