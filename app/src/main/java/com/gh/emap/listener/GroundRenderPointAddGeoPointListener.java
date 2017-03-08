package com.gh.emap.listener;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.overlay.PointObject;
import com.gh.emap.overlay.PointOverlay;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.Overlay;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * Created by GuHeng on 2017/3/8.
 */

public class GroundRenderPointAddGeoPointListener implements View.OnClickListener {
    public MainActivity mMainActivity;

    public GroundRenderPointAddGeoPointListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ground_render_point_add_geopoint_cancel: // 取消
                onClickedCancel(view);
                break;
            case R.id.ground_render_point_add_geopoint_ok: // 确定
                onClickedOk(view);
                break;
            default:
                break;
        }
    }

    // 取消
    private void onClickedCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getGroundRenderPointAddGeoPointLayout().hide();
    }

    // 确定
    private void onClickedOk(View view) {
        mMainActivity.getMainManager().getLayoutManager().getGroundRenderPointAddGeoPointLayout().hide();

        final String REGEX = "^[-+]?[0-9]+(.[0-9]+)?$";

        String strLatitude = mMainActivity.getMainManager().getLayoutManager().getGroundRenderPointAddGeoPointLayout().getLatitude();
        if(strLatitude == null || strLatitude.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().show(String.format("请输入纬度"));
            return;
        } else {
            Pattern pattern = Pattern.compile(REGEX);
            if(!pattern.matcher(strLatitude).matches()) {
                mMainActivity.getMainManager().getLogManager().show(String.format("输入纬度格式错误"));
                return;
            }
        }

        String strLongitude = mMainActivity.getMainManager().getLayoutManager().getGroundRenderPointAddGeoPointLayout().getLongitude();
        if(strLongitude == null || strLongitude.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().show(String.format("请输入经度"));
            return;
        } else {
            Pattern pattern = Pattern.compile(REGEX);
            if(!pattern.matcher(strLongitude).matches()) {
                mMainActivity.getMainManager().getLogManager().show(String.format("输入经度格式错误"));
                return;
            }
        }

        Overlay overlay = mMainActivity.getMainManager().getMapManager().getLastOverlay();
        if(overlay == null) {
            return;
        }

        if(overlay instanceof PointOverlay) {
            PointOverlay pointOverlay = (PointOverlay)overlay;
            PointObject pointObject = pointOverlay.getPointObject();
            if(pointObject == null) {
                return;
            }

            final int ONE_HUNDRED_THOUSAND = 1000000;

            BigDecimal dLatitude = new BigDecimal(strLatitude);
            dLatitude = dLatitude.multiply(new BigDecimal(ONE_HUNDRED_THOUSAND));
            BigInteger nLatitude = dLatitude.setScale(0, BigDecimal.ROUND_HALF_UP).toBigInteger();

            BigDecimal dLongitude = new BigDecimal(strLongitude);
            dLongitude = dLongitude.multiply(new BigDecimal(ONE_HUNDRED_THOUSAND));
            BigInteger nLongitude = dLongitude.setScale(0, BigDecimal.ROUND_HALF_UP).toBigInteger();

            pointOverlay.getPointObject().setGeoPoint(new GeoPoint(nLatitude.intValue(), nLongitude.intValue()));

            mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
        }
    }
}
