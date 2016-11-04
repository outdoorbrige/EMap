package cn.com.sgcc.epri.emap.manager;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.file.ConfigFile;
import cn.com.sgcc.epri.emap.file.ShapPointFile;
import cn.com.sgcc.epri.emap.model.ConfigInfo;
import cn.com.sgcc.epri.emap.base.MainActivityContext;
import cn.com.sgcc.epri.emap.model.ShapPointInfo;

/**
 * Created by GuHeng on 2016/10/19.
 * 文件管理类
 */
public class FileManager extends MainActivityContext {
    private ConfigFile mConfigFile; // 配置文件解析对象
    private ShapPointFile mShapPointFile; // 地物编辑-画点文件解析对象

    // 构造函数
    public FileManager(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        mConfigFile = new ConfigFile(mMainActivity);
        mConfigFile.init();

        mShapPointFile = new ShapPointFile(mMainActivity);
        mShapPointFile.init();
    }

    // 获取配置文件信息
    public ConfigInfo getConfigInfo() {
        return mConfigFile.getConfigInfo();
    }

    // 获取地物编辑-画点数据
    public ShapPointInfo getShapPointInfo() {
        return mShapPointFile.getShapPointInfo();
    }
}
