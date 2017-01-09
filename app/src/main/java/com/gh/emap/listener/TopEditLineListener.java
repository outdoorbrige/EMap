package com.gh.emap.listener;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

/**
 * Created by GuHeng on 2017/1/9.
 */

public class TopEditLineListener implements View.OnClickListener {
    private Context mContext;

    // 构造函数
    public TopEditLineListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.line_type: // 画线类型
                onClickedLineType(view);
                break;
            case R.id.line_name: // 画线名称
                onClickedLineName(view);
                break;
            case R.id.line_cancel: // 取消
                onClickedLineCancel(view);
                break;
            case R.id.line_save: // 保存
                onClickedLineSave(view);
                break;
            default:
                break;
        }
    }

    // 画线类型
    private void onClickedLineType(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getOperationLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapLineLayout().show();

        ((TextView)((MainActivity)this.mContext).findViewById(R.id.line_type)).setText(
                (String)((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapLineLayout().getWheelViewTwo().getSelectionItem());
    }

    // 画线名称
    private void onClickedLineName(View view) {

    }

    // 取消
    private void onClickedLineCancel(View view) {
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopShapLineLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapLineLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapLineLayout().clear();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getOperationLayout().show();

        // 删除画线覆盖物
//        LineOverlay lineOverlay = ((MainActivity) this.mContext).getMainManager().getOverlayManager().getLineOverlay();
//        ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().removeOverlay(lineOverlay);
//        ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().postInvalidate();
    }

    // 保存
    private void onClickedLineSave(View view) {
        String lineName = ((EditText)((MainActivity)this.mContext).findViewById(R.id.line_name)).getText().toString();
        if(lineName.isEmpty()) {
            ((MainActivity)this.mContext).getMainManager().getLogManager().show(String.format("请输入线-名称"));
            return;
        }

//        // 检查线名是否存在
//        if(LineNameExist(lineName)) {
//            ((MainActivity)this.mContext).getMainManager().getLogManager().show(String.format("线-名称已存在"));
//            return;
//        }
//
//        String lineType = ((TextView)((MainActivity)this.mContext).findViewById(R.id.line_type)).getText().toString();
//        if(lineType.isEmpty()) {
//            ((MainActivity)this.mContext).getMainManager().getLogManager().show(String.format("请选择线-类别"));
//            return;
//        }

//        GeoLine line = ((MainActivity) this.mContext).getMainManager().getOverlayManager().getLineOverlay().getGeoLine();
//        if(line == null) {
//            ((MainActivity)this.mContext).getMainManager().getLogManager().show(String.format("请选择线的位置"));
//            return;
//        }
//
//        String path = ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopShapLineLayout().getShapLinePath();
//        if(path == null || path.isEmpty()) {
//            ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError, String.format("用户未登录!"));
//            return;
//        } else {
//            // 保存线信息到文件
//
//            LineOverlayItem overlay = new LineOverlayItem(line.getLatitudeE6(), line.getLongitudeE6(), "", "", mContext);
//            overlay.getLineObject().setType(lineType);
//            overlay.getLineObject().setName(lineName);
//
//            ((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getLineOverlayItems().put(overlay);
//            ((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getLineOverlayItems().populate();
//
//            RWLineFile.write(path + File.separator + overlay.getLineObject().getName() + ".p", overlay.getLineObject());
//
//            ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().addOverlay(
//                    ((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getLineOverlayItems());
//            ((MainActivity) this.mContext).getMainManager().getMapManager().getMapView().postInvalidate();
//        }

        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopShapLineLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapLineLayout().hide();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getBottomShapLineLayout().clear();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getMenuLayout().show();
        ((MainActivity)this.mContext).getMainManager().getLayoutManager().getOperationLayout().show();
    }

    // 检查线-名称是否存在
//    boolean LineNameExist(String lineName) {
//        List<LineOverlayItem> items = ((MainActivity) this.mContext).getMainManager().getMyUserOverlaysManager().getLineOverlayItems().getItems();
//        if(items == null) {
//            return false;
//        }
//
//        for(int i = 0; i < items.size(); i ++) {
//            LineOverlayItem item = items.get(i);
//            if(item == null) {
//                continue;
//            }
//
//            LineObject lineObject = item.getLineObject();
//            if(lineObject == null) {
//                continue;
//            }
//
//            if(lineObject.getName().equals(lineName)) {
//                return true;
//            }
//        }
//
//        return false;
//    }
}
