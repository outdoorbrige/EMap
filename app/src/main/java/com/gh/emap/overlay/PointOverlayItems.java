package com.gh.emap.overlay;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.tianditu.android.maps.ItemizedOverlay;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GuHeng on 2016/12/22.
 * 地物编辑-画点-覆盖物集合
 */

public class PointOverlayItems extends ItemizedOverlay<PointOverlayItem> {
    private Context mContext;
    private Drawable mMarker;
    private HashMap<String, PointOverlayItem> mPointOverlayItems = new HashMap<>();

    // 构造方法
    public PointOverlayItems(Drawable marker, Context context) {
        super(boundCenterBottom(marker));
        this.mMarker = marker;
        this.mContext = context;
    }

    // 初始化
    public void init() {

    }

    // 获取覆盖物集合
    public HashMap<String, PointOverlayItem> getItems() {
        return mPointOverlayItems;
    }

    // 添加覆盖物
    public void put(PointOverlayItem item) {
        if(item != null) {
            item.setMarker(mMarker);
            mPointOverlayItems.put(String.valueOf(mPointOverlayItems.size()), item);

            populate(); // 一旦有了数据，在调用其他方法前，必须首先调用这个方法
        }
    }

    // 添加覆盖物集合
    public void put(ArrayList<PointOverlayItem> items) {
        if(items == null) {
            return;
        }

        int index = this.mPointOverlayItems.size();
        for (int i = 0; i < items.size(); i ++) {
            mPointOverlayItems.put(String.valueOf(index + i), items.get(i));
        }
    }

    // 查找覆盖物
    public PointOverlayItem get(int index) {
        PointOverlayItem overlayItem = mPointOverlayItems.get(String.valueOf(index));
        return overlayItem;
    }

    // 删除覆盖物
    public PointOverlayItem remove(int index) {
        PointOverlayItem overlayItem = mPointOverlayItems.remove(String.valueOf(index));
        return overlayItem;
    }

    // 清空覆盖物
    public void clear() {
        mPointOverlayItems.clear();
    }

    // 创建指定的条目，由父类调用
    @Override
    protected PointOverlayItem createItem(int index) {
        PointOverlayItem overlayItem = mPointOverlayItems.get(String.valueOf(index));
        return overlayItem;
    }

    // 覆盖物数量
    @Override
    public int size() {
        return this.mPointOverlayItems.size();
    }

    // 在某个条目被点击时调用
    @Override
    protected boolean onTap(int index) {
        return true;
    }
}
