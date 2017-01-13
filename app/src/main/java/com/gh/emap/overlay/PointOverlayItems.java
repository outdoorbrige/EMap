package com.gh.emap.overlay;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.tianditu.android.maps.ItemizedOverlay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuHeng on 2016/12/22.
 * 地物编辑-画点-覆盖物集合
 */

public class PointOverlayItems extends ItemizedOverlay<PointOverlayItem> {
    private Context mContext;
    private Drawable mMarker;
    private List<PointOverlayItem> mPointOverlayItems = new ArrayList<>();

    // 构造方法
    public PointOverlayItems(Context context, Drawable marker) {
        super(boundCenterBottom(marker));
        this.mMarker = marker;
        this.mContext = context;
    }

    // 初始化
    public void init() {

    }

    // 创建指定的条目，由父类调用
    @Override
    protected PointOverlayItem createItem(int index) {
        PointOverlayItem overlayItem = mPointOverlayItems.get(index);
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

        super.setFocusID(-1);
        return true;
    }

    // 获取覆盖物集合
    public List<PointOverlayItem> getItems() {
        return mPointOverlayItems;
    }

    // 查找覆盖物
    public PointOverlayItem get(int index) {
        PointOverlayItem overlayItem = mPointOverlayItems.get(index);
        return overlayItem;
    }

    // 添加覆盖物
    // 警告：添加完成后必须调用populate方法
    public void put(PointOverlayItem item) {
        if(item != null) {
            item.setMarker(mMarker);
            mPointOverlayItems.add(item);
        }
    }

    // 添加覆盖物集合
    // 警告：添加完成后必须调用populate方法
    public void put(ArrayList<PointOverlayItem> items) {
        if(items == null) {
            return;
        }

        for(int i = 0; i < items.size(); i ++) {
            this.put(items.get(i));
        }
    }

    // 删除覆盖物
    // 警告：删除完成后必须调用populate方法
    public PointOverlayItem remove(int index) {
        PointOverlayItem overlayItem = mPointOverlayItems.remove(index);
        return overlayItem;
    }

    // 清空覆盖物
    // 警告：清空完成后必须调用populate方法
    public void clear() {
        mPointOverlayItems.clear();
    }

    // 刷新覆盖物列表
    // 警告：添加和删除 覆盖物后必须要调用次方法，才能调用其他方法
    public void populate() {
        super.populate(); // 一旦有了数据，在调用其他方法前，必须首先调用这个方法
    }
}
