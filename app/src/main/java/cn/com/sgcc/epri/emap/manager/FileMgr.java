package cn.com.sgcc.epri.emap.manager;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.file.ConfigFile;
import cn.com.sgcc.epri.emap.model.ConfigInfo;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/19.
 * 文件管理类
 */
public class FileMgr extends TransmitContext {
    private ConfigFile config_file; // 配置文件解析对象

    // 构造函数
    public FileMgr(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        config_file = new ConfigFile(context);
        config_file.init();
    }

    // 获取配置文件信息
    public ConfigInfo getConfigInfo() {
        return config_file.getConfigInfo();
    }
}
