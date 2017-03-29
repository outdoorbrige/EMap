package com.gh.emap.listenerB;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.gh.emap.OfflineMapDownloadActivity;
import com.gh.emap.R;

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
            TextView textView = (TextView)view.findViewById(R.id.offline_map_download_city_list_item_title);
            if(textView == null) {
                return;
            }

            String titleText = textView.getText().toString();
            if(titleText == null || titleText.isEmpty()) {
                return;
            }

            int groupSelectedId = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getGroupSelectedId(titleText);
            if(groupSelectedId < 0) {
                return;
            }

            ExpandableListView otherProvincesCitiesList = mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().getOtherProvincesCitiesList();

            // 1、先删除ExpandableListView
            ViewGroup viewGroup = (ViewGroup)otherProvincesCitiesList.getParent();
            if(viewGroup != null) {
                viewGroup.removeView(otherProvincesCitiesList);
            }

            int groupPos = -1;

            boolean isGroupExpanded = otherProvincesCitiesList.isGroupExpanded(groupSelectedId);
            if(isGroupExpanded) {
                groupPos = -1;
                otherProvincesCitiesList.collapseGroup(groupSelectedId); // 折叠
            } else {
                groupPos = groupSelectedId;
                otherProvincesCitiesList.expandGroup(groupSelectedId, true); // 展开
            }

            // 2、重新计算ExpandableListView的高度
            mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListOtherProvincesCitiesLayout().setExpandableListViewHeightBasedOnChildren(otherProvincesCitiesList, groupPos);

            // 3、再添加ExpandableListView
            viewGroup = (ViewGroup)otherProvincesCitiesList.getParent();
            if(viewGroup == null) {
                mOfflineMapDownloadActivity.getMainManager().getLayoutManager().getCityListLayout().getScrollViewLinearLayout().addView(otherProvincesCitiesList);
            }
        }
    }
}
