package com.gh.emap.manager;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.gh.emap.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GuHeng on 2016/11/14.
 * 权限管理
 */
public class MyPermissionManager {
    private Context mContext;
    private PackageManager mPackageManager;

    public MyPermissionManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        try {
            this.mPackageManager = ((MainActivity)this.mContext).getPackageManager();

            PackageInfo packageInfo = this.mPackageManager.getPackageInfo(this.mContext.getPackageName(), PackageManager.GET_PERMISSIONS);

            List<String> permissions = new ArrayList<String>();
            for (String item : packageInfo.requestedPermissions) {
                permissions.add(item);
            }

            ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mInfo,
                    String.format("权限清单：%s", permissions));
        } catch (Exception e) {
            ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                    String.format("%s", e.getStackTrace().toString()));
        }
    }

    public boolean isPermission(String permission) {
        return (PackageManager.PERMISSION_GRANTED == this.mPackageManager.checkPermission(permission, this.mContext.getPackageName()));
    }
}
