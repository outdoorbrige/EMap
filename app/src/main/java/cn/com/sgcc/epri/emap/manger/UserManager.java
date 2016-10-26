package cn.com.sgcc.epri.emap.manger;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.model.UserInfo;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/25.
 * 用户信息管理类
 */
public class UserManager extends MainActivityContext {
    private UserInfo mUserInfo; // 用户信息

    // 构造函数
    public UserManager (MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {

    }

    // 设置用户信息
    public void setUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
    }

    // 获取用户信息
    public UserInfo getUserInfo() {
        return mUserInfo;
    }
}
