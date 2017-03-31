package com.gh.emap.layoutB;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;
import com.gh.emap.modelB.OneCityInfo;

/**
 * Created by GuHeng on 2017/3/31.
 */

public class OfflineMapDownloadTypeSelectLayout {
    private OfflineMapDownloadActivity mOfflineMapDownloadActivity;
    private View mLayout; // 布局
    private AlertDialog mAlertDialog; // 弹出式对话框
    private TextView mTitle;
    private Button mAll;
    private Button mImage;
    private Button mVector;
    private Button mCancel;

    public OfflineMapDownloadTypeSelectLayout(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    public void init() {
        View layout = mOfflineMapDownloadActivity.getLayoutInflater().inflate(R.layout.offline_map_download_type_select, null, false);

        mLayout = layout.findViewById(R.id.offline_map_download_type_select);

        mTitle = (TextView)layout.findViewById(R.id.offline_map_download_title);
        mAll = (Button)layout.findViewById(R.id.offline_map_download_all);
        mImage = (Button)layout.findViewById(R.id.offline_map_download_image);
        mVector = (Button)layout.findViewById(R.id.offline_map_download_vector);
        mCancel = (Button)layout.findViewById(R.id.offline_map_download_cancel);

        mAll.setOnClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getOfflineMapDownloadTypeSelectListener());
        mImage.setOnClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getOfflineMapDownloadTypeSelectListener());
        mVector.setOnClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getOfflineMapDownloadTypeSelectListener());
        mCancel.setOnClickListener(mOfflineMapDownloadActivity.getMainManager().getListenerManager().getOfflineMapDownloadTypeSelectListener());

        mAlertDialog = new AlertDialog.Builder(mOfflineMapDownloadActivity).create();
        mAlertDialog.setView(layout);
        mAlertDialog.setCancelable(false); // 点击对话框外地方是否不消失
    }

    // 清空旧数据
    private void clear() {
        mTitle.setText("");
        mAll.setText("");
        mImage.setText("");
        mVector.setText("");
    }

    // 设置显示的数据
    private void setOneCityInfo(OneCityInfo oneCityInfo) {
        clear();

        if(oneCityInfo == null) {
            return;
        }

        mTitle.setText(oneCityInfo.getCityName());

        String[] imageSizeUnit = {"", ""};
        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().formatByteToSizeAndUnit(oneCityInfo.getImageSize(), imageSizeUnit);

        Double imageSize = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getBytesFromSizeAndUnit(imageSizeUnit);

        String[] vectorSizeUnit = {"", ""};
        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().formatByteToSizeAndUnit(oneCityInfo.getVectorSize(), vectorSizeUnit);

        Double vectorSize = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getBytesFromSizeAndUnit(vectorSizeUnit);

        Double allSize = imageSize + vectorSize;

        String[] allSizeUnit = {"", ""};
        mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().formatByteToSizeAndUnit(allSize.longValue(), allSizeUnit);

        if(allSizeUnit[0].isEmpty() || allSizeUnit[0].equals("0")) {
            mAll.setVisibility(View.GONE);
        } else {
            mAll.setVisibility(View.VISIBLE);
            mAll.setText("下载全部地图" + "(" + allSizeUnit[0] + allSizeUnit[1] + ")");
        }

        if(imageSizeUnit[0].isEmpty() || imageSizeUnit[0].equals("0")) {
            mAll.setVisibility(View.GONE);
            mImage.setVisibility(View.GONE);
        } else {
            mImage.setVisibility(View.VISIBLE);
            mImage.setText("下载影像地图" + "(" + imageSizeUnit[0] + imageSizeUnit[1] + ")");
        }

        if(vectorSizeUnit[0].isEmpty() || vectorSizeUnit[0].equals("0")) {
            mAll.setVisibility(View.GONE);
            mVector.setVisibility(View.GONE);
        } else {
            mVector.setVisibility(View.VISIBLE);
            mVector.setText("下载矢量地图" + "(" + vectorSizeUnit[0] + vectorSizeUnit[1] + ")");
        }
    }

    // 显示对话框
    public void show(OneCityInfo oneCityInfo) {
        mAlertDialog.show();
        setOneCityInfo(oneCityInfo);
    }

    // 隐藏对话框
    public void hide() {
        mAlertDialog.hide();
    }

    // 销毁对话框
    public void dismiss() {
        mAlertDialog.dismiss();
    }

    // 获取对话框句柄
    public AlertDialog getAlertDialog() {
        return mAlertDialog;
    }
}
