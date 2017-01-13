package com.gh.emap.layout;

import android.view.View;
import android.widget.Button;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.manager.LogManager;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;

/**
 * Created by GuHeng on 2016/11/9.
 * 地图菜单布局
 */
public class MenuLayout {
    private MainActivity mMainActivity;
    private View mLayout; // 布局
    private Button mEditButton; // 编辑按钮
    private Button mSetButton; // 配置按钮
    private Button mDownloadButton; // 下载按钮
    private Button mMenuButton; // 菜单按钮
    private ArrayList<Button> mArrayListButtons = new ArrayList<>(); // 展开和折叠菜单中控制的按钮集合
    private boolean mMenuOpen = false; // 控制菜单展开和折叠，默认折叠
    private int mAnimationRadius = 0; // 动画半径，单位(dp) // 菜单展开半径
    private final int mAnimationCycle = 500; // 动画周期,单位毫秒(ms) // 菜单展开/折叠动画完成的时间
    private static final int ANIMATION_ACTION_CLOSE = 0; // 动画菜单折叠
    private static final int ANIMATION_ACTION_OPEN = 1; // 动画菜单展开

    public MenuLayout(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mLayout = mMainActivity.findViewById(R.id.menu);
        mEditButton = (Button)mMainActivity.findViewById(R.id.menu_edit);
        mSetButton = (Button)mMainActivity.findViewById(R.id.menu_setting);
        mDownloadButton = (Button)mMainActivity.findViewById(R.id.menu_download);
        mMenuButton = (Button)mMainActivity.findViewById(R.id.menu_main);

        // 注意：
        // 按钮排列数序是从下向上排列的
        // 底部的按钮在List的队首，顶部的按钮在List的队尾
        mArrayListButtons.add(mDownloadButton);
        mArrayListButtons.add(mSetButton);
        mArrayListButtons.add(mEditButton);

        mEditButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getMenuListener());
        mSetButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getMenuListener());
        mDownloadButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getMenuListener());
        mMenuButton.setOnClickListener(mMainActivity.getMainManager().getListenerManager().getMenuListener());
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

    // 运行动画
    public void runMenuAnimation(View view) {
        if(mAnimationRadius == 0) {
            mAnimationRadius = calculateAnimationRadius(view);
        }

        if(!mMenuOpen) {
            openMenuAnimation();
        } else {
            closeMenuAnimation();
        }
    }

    // 展开动画
    private void openMenuAnimation() {
        if(mMenuOpen) {
            return;
        }

        for(int i = 0; i < mArrayListButtons.size(); i ++) {
            doAnimateAction(ANIMATION_ACTION_OPEN, mArrayListButtons.get(i), i, mArrayListButtons.size(), mAnimationRadius);
        }

        mMenuOpen = true;
    }

    // 折叠动画
    private void closeMenuAnimation() {
        if(!mMenuOpen) {
            return;
        }

        for(int i = 0; i < mArrayListButtons.size(); i ++) {
            doAnimateAction(ANIMATION_ACTION_CLOSE, mArrayListButtons.get(i), i, mArrayListButtons.size(), mAnimationRadius);
        }

        mMenuOpen = false;
    }

    // 计算动画半径
    private int calculateAnimationRadius(View view) {
        int radius = 0;

        // 获取按钮边长(dp)
        int a = (int)(Math.round(view.getHeight() / mMainActivity.getResources().getDisplayMetrics().density));

        // 按钮对角线长度的一半(dp)
        int c = (int)Math.round(Math.sqrt(a * a * 2) / 2);

        // 一个按钮对应角度的弧度的一半
        double angleRadians = (90 / mArrayListButtons.size() / 2) * Math.PI / 180; // 角度换算成弧度

        // 计算半径(dp)
        radius = (int)Math.round(c * 1.2 / Math.sin(angleRadians));

        mMainActivity.getMainManager().getLogManager().log(getClass(), LogManager.LogLevel.mInfo,
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
