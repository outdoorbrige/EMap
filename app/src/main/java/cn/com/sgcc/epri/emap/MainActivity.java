package cn.com.sgcc.epri.emap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.tianditu.android.maps.MapController;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**********************************************************************************************/
    // 界面控件成员变量

    public TMapView emap_view; // 天地图地图控件

    // 搜索条
    public LinearLayout emap_search_bar;

    // 配置菜单
    public Button emap_setting_menu_favorite_btn;
    public Button emap_setting_menu_clear_btn;
    public Button emap_setting_menu_set_btn;
    public Button emap_setting_menu_offline_btn;
    public Button emap_setting_menu_tool_btn;
    public Button emap_setting_menu_main_front_btn;

    /**********************************************************************************************/
    // 其他成员变量

    public TMyLocationOverlay my_location_overlay;
    public MapController map_controller;

    public boolean emap_setting_menu_main_is_open = false;
    public ArrayList<Button> list_buttons = new ArrayList<Button>();
    private static final int radius = 250;

    /**********************************************************************************************/
    // 重载成员方法

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emap_view);

        initView();
        initTMapView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onClick(View view) {
        if(view == emap_setting_menu_main_front_btn) {
            if(!emap_setting_menu_main_is_open) {
                for(int i = 0; i < list_buttons.size(); i ++) {
                    doAnimateOpen(list_buttons.get(i), i, list_buttons.size(), radius);
                }
            } else {
                for(int i = 0; i < list_buttons.size(); i ++) {
                    doAnimateClose(list_buttons.get(i), i, list_buttons.size(), radius);
                }
            }

            emap_setting_menu_main_is_open = !emap_setting_menu_main_is_open;
        } else {
            //Toast.makeText(this, "你点击了" + view, Toast.LENGTH_SHORT).show();
        }
    }

    /**********************************************************************************************/
    // 自定义成员方法

    // 初始化所有控件对象
    private void initView() {
        emap_view = (TMapView)findViewById(R.id.emap_view);

        emap_search_bar = (LinearLayout)findViewById(R.id.emap_search_bar);

        emap_setting_menu_favorite_btn = (Button)findViewById(R.id.emap_setting_menu_favorite_btn);
        emap_setting_menu_favorite_btn.setOnClickListener(this);
        emap_setting_menu_clear_btn = (Button)findViewById(R.id.emap_setting_menu_clear_btn);
        emap_setting_menu_clear_btn.setOnClickListener(this);
        emap_setting_menu_set_btn = (Button)findViewById(R.id.emap_setting_menu_set_btn);
        emap_setting_menu_set_btn.setOnClickListener(this);
        emap_setting_menu_offline_btn = (Button)findViewById(R.id.emap_setting_menu_offline_btn);
        emap_setting_menu_offline_btn.setOnClickListener(this);
        emap_setting_menu_tool_btn = (Button)findViewById(R.id.emap_setting_menu_tool_btn);
        emap_setting_menu_tool_btn.setOnClickListener(this);
        emap_setting_menu_main_front_btn = (Button)findViewById(R.id.emap_setting_menu_main_btn);
        emap_setting_menu_main_front_btn.setOnClickListener(this);

        list_buttons.add(emap_setting_menu_tool_btn);
        list_buttons.add(emap_setting_menu_offline_btn);
        list_buttons.add(emap_setting_menu_set_btn);
        list_buttons.add(emap_setting_menu_clear_btn);
        list_buttons.add(emap_setting_menu_favorite_btn);
    }

    // 天地图控件初始化
    private void initTMapView() {
        //emap_view.setSatellite(true); // 使用影像图
        emap_view.setLogoPos(TMapView.LOGO_RIGHT_BOTTOM); // 设置LOGO位置为右下角

        my_location_overlay = new TMyLocationOverlay(this, emap_view);
        //my_location_overlay.enableCompass(); // 显示指南针
        my_location_overlay.enableMyLocation(); // 显示我的位置
        emap_view.getOverlays().add(my_location_overlay);

        map_controller = emap_view.getController();
        map_controller.setCenter(my_location_overlay.getMyLocation()); // 设置地图中心点

        //emap_view.setBuiltInZoomControls(true); // 设置启用内置的缩放控件

        //emap_search_bar.setAlpha(0.9F); // 设置搜索条透明度
    }

    // 更新经纬度和高程
//    public void updateLatitudeLongitudeHeight() {
//        latitude_longitude_height.setText(
//                "纬度：" + String.valueOf(my_location_overlay.getMyLocation().getLatitudeE6() / 1000000.0D) + " " +
//                "经度：" + String.valueOf(my_location_overlay.getMyLocation().getLongitudeE6() / 1000000.0D) + " " +
//                "高程：");
//    }

    /**
     * 打开菜单动画
     * @param view 执行动画的view
     * @param index 在动画序列中的位置
     * @param total 动画序列的个数
     * @param radius 动画半径
     */
    private void doAnimateOpen(View view, int index, int total, int radius) {
        if(view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }

        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = (int)(radius * Math.cos(degree));
        int translationY = -(int)(radius * Math.sin(degree));

        AnimatorSet set = new AnimatorSet();
        // 包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        );

        // 动画周期为500ms
        set.setDuration(1 * 500).start();
    }

    /**
     * 关闭菜单动画
     * @param view 执行动画的view
     * @param index 在动画序列中的位置
     * @param total 动画序列的个数
     * @param radius 动画半径
     */
    private void doAnimateClose(final View view, int index, int total, int radius) {
        if(view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }

        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = (int)(radius * Math.cos(degree));
        int translationY = -(int)(radius * Math.sin(degree));

        AnimatorSet set = new AnimatorSet();
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

        // 动画周期为500ms
        set.setDuration(1 * 500).start();
    }
}