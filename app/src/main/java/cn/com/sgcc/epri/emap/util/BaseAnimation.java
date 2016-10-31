package cn.com.sgcc.epri.emap.util;

import android.view.View;
import android.widget.Button;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;

import cn.com.sgcc.epri.emap.MainActivity;

/**
 * Created by GuHeng on 2016/10/31.
 * 菜单动画基类
 */
public class BaseAnimation extends MainActivityContext {
    private boolean mMenuOpen = false; // 控制菜单展开和折叠，默认折叠
    private ArrayList<Button> mArrayListButtons = null; // 展开和折叠菜单中控制的按钮集合
    private int mAnimationRadius = 0; // 动画半径，单位(dp) // 菜单展开半径
    private final int mAnimationCycle = 500; // 动画周期,单位毫秒(ms) // 菜单展开/折叠动画完成的时间

    private static final int ANIMATION_ACTION_CLOSE = 0; // 动画菜单折叠
    private static final int ANIMATION_ACTION_OPEN = 1; // 动画菜单展开

    // 构造函数
    protected BaseAnimation(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    protected void init(ArrayList<Button> arrayListButtons) {
        mArrayListButtons = arrayListButtons;
    }

    // 开始菜单动画
    protected void startMenuAnimation(View view) {
        if(mAnimationRadius == 0) {
            mAnimationRadius = calculateAnimationRadius(view);
        }

        if(!mMenuOpen) {
            for(int i = 0; i < mArrayListButtons.size(); i ++) {
                doAnimateAction(ANIMATION_ACTION_OPEN, mArrayListButtons.get(i), i, mArrayListButtons.size(), mAnimationRadius);
            }
        } else {
            for(int i = 0; i < mArrayListButtons.size(); i ++) {
                doAnimateAction(ANIMATION_ACTION_CLOSE, mArrayListButtons.get(i), i, mArrayListButtons.size(), mAnimationRadius);
            }
        }

        mMenuOpen = !mMenuOpen;

    }

    // 计算动画半径
    private int calculateAnimationRadius(View view) {
        int radius = 0;

        // 获取按钮边长(dp)
        int a = DisplayMetricsUtil.Px2Dp(mMainActivity, view.getHeight());

        // 按钮对角线长度的一半(dp)
        int c = (int)Math.round(Math.sqrt(a * a * 2) / 2);

        // 一个按钮对应角度的弧度的一半
        double angleRadians = (90 / mArrayListButtons.size() / 2) * Math.PI / 180; // 角度换算成弧度

        // 计算半径(dp)
        radius = (int)Math.round(c / Math.sin(angleRadians));

        mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mDebug,
                String.format("按钮边长:%d, 斜边长:%d, 按钮个数:%d, 度数:%f, 正弦值:%f, 近似半径:%d, 动画半径:%d",
                        a, c, mArrayListButtons.size(), angleRadians, Math.sin(angleRadians), radius, radius + a));

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
