package com.gh.emap.listenerA;

import android.view.View;

import com.gh.emap.MainActivity;
import com.gh.emap.R;
import com.gh.emap.overlay.AreaGirthOverlay;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.Overlay;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by GuHeng on 2017/3/6.
 */

public class DrawAreaGirthAddGeoPointListener implements View.OnClickListener {
    public MainActivity mMainActivity;

    public DrawAreaGirthAddGeoPointListener(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.draw_area_girth_add_geopoint_cancel: // 取消
                onClickedCancel(view);
                break;
            case R.id.draw_area_girth_add_geopoint_ok: // 确定
                onClickedOk(view);
                break;
            default:
                break;
        }
    }

    // 取消
    private void onClickedCancel(View view) {
        mMainActivity.getMainManager().getLayoutManager().getDrawAreaGirthAddGeoPointLayout().hide();
    }

    // 确定
    private void onClickedOk(View view) {
        mMainActivity.getMainManager().getLayoutManager().getDrawAreaGirthAddGeoPointLayout().hide();

        final String REGEX = "^[-+]?[0-9]+(.[0-9]+)?$";

        String strLatitude = mMainActivity.getMainManager().getLayoutManager().getDrawAreaGirthAddGeoPointLayout().getLatitude();
        if(strLatitude == null || strLatitude.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("请输入纬度"));
            return;
        } else {
            Pattern pattern = Pattern.compile(REGEX);
            if(!pattern.matcher(strLatitude).matches()) {
                mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("输入纬度格式错误"));
                return;
            }
        }

        String strLongitude = mMainActivity.getMainManager().getLayoutManager().getDrawAreaGirthAddGeoPointLayout().getLongitude();
        if(strLongitude == null || strLongitude.isEmpty()) {
            mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("请输入经度"));
            return;
        } else {
            Pattern pattern = Pattern.compile(REGEX);
            if(!pattern.matcher(strLongitude).matches()) {
                mMainActivity.getMainManager().getLogManager().toastShowShort(String.format("输入经度格式错误"));
                return;
            }
        }

        Overlay overlay = mMainActivity.getMainManager().getMapManager().getLastOverlay();
        if(overlay == null) {
            return;
        }

        if(overlay instanceof AreaGirthOverlay) {
            AreaGirthOverlay areaGirthOverlay = (AreaGirthOverlay)overlay;
            ArrayList<GeoPoint> geoPoints = areaGirthOverlay.getGeoPoints();
            if(geoPoints == null) {
                return;
            }

            final int ONE_HUNDRED_THOUSAND = 1000000;

            BigDecimal dLatitude = new BigDecimal(strLatitude);
            dLatitude = dLatitude.multiply(new BigDecimal(ONE_HUNDRED_THOUSAND));
            BigInteger nLatitude = dLatitude.setScale(0, BigDecimal.ROUND_HALF_UP).toBigInteger();

            BigDecimal dLongitude = new BigDecimal(strLongitude);
            dLongitude = dLongitude.multiply(new BigDecimal(ONE_HUNDRED_THOUSAND));
            BigInteger nLongitude = dLongitude.setScale(0, BigDecimal.ROUND_HALF_UP).toBigInteger();

            areaGirthOverlay.addGeoPoint(new GeoPoint(nLatitude.intValue(), nLongitude.intValue()));

            mMainActivity.getMainManager().getMapManager().getMapView().postInvalidate();
        }
    }
}
