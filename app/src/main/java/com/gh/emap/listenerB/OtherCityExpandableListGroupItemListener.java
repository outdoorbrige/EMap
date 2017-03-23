package com.gh.emap.listenerB;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.gh.emap.OfflineMapDownloadActivity;

/**
 * Created by GuHeng on 2017/3/22.
 */

public class OtherCityExpandableListGroupItemListener implements AdapterView.OnItemClickListener {
    public OfflineMapDownloadActivity mOfflineMapDownloadActivity;

    public OtherCityExpandableListGroupItemListener(OfflineMapDownloadActivity offlineMapDownloadActivity) {
        mOfflineMapDownloadActivity = offlineMapDownloadActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // id == -1 点击的是headerView或者footerView
        if(-1 < position && position < parent.getCount()) {
            String text = ((TextView)view).getText().toString();
            if(text == null || text.isEmpty()) {
                return;
            }

            int expandableListViewSelectPosition = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getOtherProvincesCitiesKeyIndex(text);
            if(expandableListViewSelectPosition < 0) {
                return;
            }

            boolean isGroupExpanded = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getOtherCityExpandableList().isGroupExpanded(expandableListViewSelectPosition);
            if(isGroupExpanded) {
                mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getOtherCityExpandableList().collapseGroup(expandableListViewSelectPosition); // 折叠
            } else {
                mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getOtherCityExpandableList().expandGroup(expandableListViewSelectPosition, true); // 展开
            }
        }
    }
}
