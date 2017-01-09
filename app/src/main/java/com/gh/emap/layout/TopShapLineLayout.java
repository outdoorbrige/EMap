package com.gh.emap.layout;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gh.emap.MainActivity;
import com.gh.emap.R;

import java.io.File;

/**
 * Created by GuHeng on 2017/1/9.
 * 地物编辑-画线 顶部布局
 */

public class TopShapLineLayout {
    private Context mContext;
    private View mLayout; // 布局
    private TextView mLineType; // 画线的类型
    private EditText mLineName; // 画线的名称
    private Button mLineCancel; // 取消
    private Button mLineSave; // 保存

    public TopShapLineLayout(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mLayout = ((MainActivity)this.mContext).findViewById(R.id.top_shap_line);
        this.mLineType = (TextView)((MainActivity)this.mContext).findViewById(R.id.line_type);
        this.mLineName = (EditText)((MainActivity)this.mContext).findViewById(R.id.line_name);
        this.mLineCancel = (Button)((MainActivity)this.mContext).findViewById(R.id.line_cancel);
        this.mLineSave = (Button)((MainActivity)this.mContext).findViewById(R.id.line_save);

        this.mLineType.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopEditLineListener());
        this.mLineName.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopEditLineListener());
        this.mLineCancel.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopEditLineListener());
        this.mLineSave.setOnClickListener(((MainActivity)this.mContext).getMainManager().getListenerManager().getTopEditLineListener());
    }

    // 获取地物编辑-画点工作目录
    public String getShapLinePath() {
        String path = null;

        String fatherPath = ((MainActivity)this.mContext).getMainManager().getLayoutManager().getTopEditLayout().getShapEditPath();
        if(fatherPath != null) {
            path = fatherPath + "MyLine" + File.separator;

            File dir = new File(path);
            if(!dir.exists()) {
                dir.mkdirs();
            }
        }

        return path;
    }

    // 显示布局
    public void show() {
        this.mLayout.setVisibility(View.VISIBLE);
        clear();
    }

    // 隐藏布局
    public void hide() {
        // View.INVISIBLE   控制该控件面板layout不可见，但是他依旧占用空间;
        //                  设置这个属性后，此位置按键不可见，但下一个按键不会占用它的位置

        // View.GONE        控制该控件面板消失;
        //                  设置这个属性后，相当于这里没有这个布局，下一个按键会向前移动，占用此控件的位置
        this.mLayout.setVisibility(View.GONE);
    }

    // 清理上次数据
    private void clear() {
        this.mLineType.setText("");
        this.mLineName.setText("");
    }
}
