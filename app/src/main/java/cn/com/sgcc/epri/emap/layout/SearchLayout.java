package cn.com.sgcc.epri.emap.layout;

import android.widget.Button;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.R;
import cn.com.sgcc.epri.emap.listener.SearchListener;
import cn.com.sgcc.epri.emap.base.BaseLayout;

/**
 * Created by GuHeng on 2016/9/27.
 * 查找布局类
 */
public class SearchLayout extends BaseLayout {
    private Button mLoginButton; // 登录按钮
    private Button mSearchButton; // 查找按钮

    // 构造函数
    public SearchLayout(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        setLayout(mMainActivity.findViewById(R.id.search));
        mLoginButton = (Button) mMainActivity.findViewById(R.id.user_login_button);
        mSearchButton = (Button) mMainActivity.findViewById(R.id.search_button);

        SearchListener listener = new SearchListener(mMainActivity);

        mLoginButton.setOnClickListener(listener);
        mSearchButton.setOnClickListener(listener);
    }
}
