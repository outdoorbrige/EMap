package cn.com.sgcc.epri.emap.manger;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.file.ConfigFile;
import cn.com.sgcc.epri.emap.model.ConfigInfo;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/19.
 * 文件管理类
 */
public class FileManger extends MainActivityContext {
    private ConfigFile mConfigFile; // 配置文件解析对象

    // 构造函数
    public FileManger(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        mConfigFile = new ConfigFile(context);
        mConfigFile.init();
    }

    // 获取配置文件信息
    public ConfigInfo getConfigInfo() {
        return mConfigFile.getConfigInfo();
    }
}
