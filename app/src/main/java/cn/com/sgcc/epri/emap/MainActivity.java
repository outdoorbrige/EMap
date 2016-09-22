package cn.com.sgcc.epri.emap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.tianditu.android.maps.MapController;

public class MainActivity extends AppCompatActivity {

    /**********************************************************************************************/
    // 界面控件成员变量

    public TMapView emap_view; // 天地图地图控件
    public LinearLayout emap_search_bar; // 搜索条布局

    /**********************************************************************************************/
    // 其他成员变量

    public TMyLocationOverlay my_location_overlay;
    public MapController map_controller;

    /**********************************************************************************************/
    // 重载成员方法

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emap_view);

        initWidgetObjects();
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

    /**********************************************************************************************/
    // 自定义成员方法

    // 初始化所有控件对象
    private void initWidgetObjects() {
        emap_view = (TMapView)findViewById(R.id.emap_view);
        emap_search_bar = (LinearLayout)findViewById(R.id.emap_search_bar);
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
}