package cn.com.sgcc.epri.emap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.numob.david.util.CrashlyticsTree;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.tianditu.android.maps.MapController;

import java.util.ArrayList;

import com.numob.david.util.CrashHandler;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**********************************************************************************************/
    // 界面控件成员变量

    public TMapView emap_view; // 天地图地图控件

    // 搜索栏
    public LinearLayout emap_search_bar;

    // 配置菜单
    public Button emap_setting_menu_favorite_btn;
    public Button emap_setting_menu_clear_btn;
    public Button emap_setting_menu_set_btn;
    public Button emap_setting_menu_offline_btn;
    public Button emap_setting_menu_tool_btn;
    public Button emap_setting_menu_main_front_btn;

    // 地图类型切换
    public Button emap_meun_layer_btn;

    // 工具栏
    public Button emap_tool_bar_zoomin_btn;
    public Button emap_tool_bar_zoomout_btn;
    public Button emap_tool_bar_locate_btn;

    /**********************************************************************************************/
    // 其他成员变量

    public TMyLocationOverlay my_location_overlay;
    public MapController map_controller;

    public boolean emap_setting_menu_main_is_open = false;
    public ArrayList<Button> list_buttons = new ArrayList<Button>();
    public int animation_radius = 300 - 15; // 动画半径，单位(dp)
    public int animation_cycle = 500; // 动画周期,单位毫秒(ms)

    public static final int ANIMATION_ACTION_OPEN = 1; // 动画菜单展开
    public static final int ANIMATION_ACTION_CLOSE = 0; // 动画菜单折叠

    /**********************************************************************************************/
    // 重载成员方法

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emap_view);

        initLogger();
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
            onClickSettingMenuMainFrontBtn(view);
        } else if(view == emap_meun_layer_btn) {
            onClickMenuLayerBtn(view);
        } else if(view == emap_tool_bar_zoomin_btn) {
            onClickToolBarZoominBtn(view);
        } else if(view == emap_tool_bar_zoomout_btn) {
            onClickToolBarZoomoutBtn(view);
        } else if(view == emap_tool_bar_locate_btn) {
            onClickToolBarLocateBtn(view);
        }
    }

    /**********************************************************************************************/
    // 自定义成员方法
    // 处理按钮点击事件

    public void onClickSettingMenuMainFrontBtn(View view) {
        if(!emap_setting_menu_main_is_open) {
            for(int i = 0; i < list_buttons.size(); i ++) {
                doAnimateAction(ANIMATION_ACTION_OPEN, list_buttons.get(i), i, list_buttons.size(), animation_radius); // (int)(getWidth()/2));
            }
        } else {
            for(int i = 0; i < list_buttons.size(); i ++) {
                doAnimateAction(ANIMATION_ACTION_CLOSE, list_buttons.get(i), i, list_buttons.size(), animation_radius); // (int)(getWidth()/2));
            }
        }

        emap_setting_menu_main_is_open = !emap_setting_menu_main_is_open;
    }

    public void onClickMenuLayerBtn(View view) {

    }

    public void onClickToolBarZoominBtn(View view) {
        if(!map_controller.zoomIn()) {
            emap_tool_bar_zoomin_btn.setClickable(false);
            emap_tool_bar_zoomin_btn.setBackgroundResource(R.mipmap.btn_zoomin_gray);
        }

        emap_tool_bar_zoomout_btn.setClickable(true);
        emap_tool_bar_zoomout_btn.setBackgroundResource(R.mipmap.btn_zoomout);
    }

    public void onClickToolBarZoomoutBtn(View view) {
    if(!map_controller.zoomOut()) {
            emap_tool_bar_zoomout_btn.setClickable(false);
            emap_tool_bar_zoomout_btn.setBackgroundResource(R.mipmap.btn_zoomout_gray);
        }

        emap_tool_bar_zoomin_btn.setClickable(true);
        emap_tool_bar_zoomin_btn.setBackgroundResource(R.mipmap.btn_zoomin);
    }

    public void onClickToolBarLocateBtn(View view) {

    }

    /**********************************************************************************************/
    // 自定义成员方法

    // 初始化Logger日志
    private void initLogger() {
        CrashHandler.getInstance().init(this); // 捕获应用运行时异常

        Logger.initialize(new Settings()
                .isShowMethodLink(true)
                .isShowThreadInfo(false)
                .setMethodOffset(0)
                .setLogPriority(BuildConfig.DEBUG ? Log.VERBOSE : Log.ASSERT));

        if(!BuildConfig.DEBUG) {
            // for release
            Logger.plant(new CrashlyticsTree());
        }
    }

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

        emap_meun_layer_btn = (Button)findViewById(R.id.emap_meun_layer_btn);
        emap_meun_layer_btn.setOnClickListener(this);

        emap_tool_bar_zoomin_btn = (Button)findViewById(R.id.emap_tool_bar_zoomin_btn);
        emap_tool_bar_zoomin_btn.setOnClickListener(this);
        emap_tool_bar_zoomout_btn = (Button)findViewById(R.id.emap_tool_bar_zoomout_btn);
        emap_tool_bar_zoomout_btn.setOnClickListener(this);
        emap_tool_bar_locate_btn = (Button)findViewById(R.id.emap_tool_bar_locate_btn);
        emap_tool_bar_locate_btn.setOnClickListener(this);
    }

    // 天地图控件初始化
    private void initTMapView() {
        map_controller = emap_view.getController();
        //emap_view.setSatellite(true); // 使用影像图
        emap_view.setLogoPos(TMapView.LOGO_RIGHT_BOTTOM); // 设置LOGO位置为右下角

        //my_location_overlay = new TMyLocationOverlay(this, emap_view);
        //my_location_overlay.enableCompass(); // 显示指南针
        //my_location_overlay.enableMyLocation(); // 显示我的位置
        //emap_view.getOverlays().add(my_location_overlay);

        //map_controller.setZoom(10);
        //map_controller.setCenter(my_location_overlay.getMyLocation()); // 设置地图中心点

        //emap_view.setBuiltInZoomControls(true); // 设置启用内置的缩放控件

        //emap_search_bar.setAlpha(0.9F); // 设置搜索条透明度

        Logger.d("屏幕宽度:" + String.valueOf(getWidth()) + "屏幕高度:" + String.valueOf(getHeight()), "kale");
        Toast.makeText(this, "屏幕宽度:" + String.valueOf(getWidth() + "\n屏幕高度:" + String.valueOf(getHeight())), Toast.LENGTH_SHORT).show();
    }

    // 更新经纬度和高程
//    public void updateLatitudeLongitudeHeight() {
//        latitude_longitude_height.setText(
//                "纬度：" + String.valueOf(my_location_overlay.getMyLocation().getLatitudeE6() / 1000000.0D) + " " +
//                "经度：" + String.valueOf(my_location_overlay.getMyLocation().getLongitudeE6() / 1000000.0D) + " " +
//                "高程：");
//    }

    // 获取屏幕的宽度
    private int getWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    // 获取屏幕的高度
    private int getHeight() {
        return getResources().getDisplayMetrics().heightPixels;
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

        set.setDuration(animation_cycle).start();
    }
}