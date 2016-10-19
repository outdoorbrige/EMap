package cn.com.sgcc.epri.emap.file;

import android.util.Log;
import android.util.Xml;

import org.apache.log4j.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.model.ConfigInfo;
import cn.com.sgcc.epri.emap.util.PhoneResources;
import cn.com.sgcc.epri.emap.util.TransmitContext;

/**
 * Created by GuHeng on 2016/10/19.
 * 解析配置文件
 */
public class ConfigFile extends TransmitContext {
    private ConfigInfo config_info; // 配置文件对象

    // 构造函数
    public ConfigFile(MainActivity context) {
        super(context);
    }

    // 初始化
    public void init() {
        String config_file = PhoneResources.getConfigFile(context);
        File file = new File(config_file);
        if(file.exists()) { // 判断文件是否存在
            try {
                FileInputStream file_input_stream = new FileInputStream(file); // 获取输入流对象
                InputStream input_stream = new BufferedInputStream(file_input_stream);

                XmlPullParser parser = Xml.newPullParser(); // 得到PULL解析器
                parser.setInput(input_stream, "UTF-8"); // 设置输入流和编码

                int event_type = parser.getEventType(); // 得到第一个时间类型
                while(event_type != XmlPullParser.END_DOCUMENT) { // 如果事件类型不是文档结束的话，则不断处理事件
                    String tag_name = parser.getName(); // 获得解析器当前元素的名称
                    //Logger.getLogger(this.getClass()).info(tag_name);
                    switch (event_type) {
                        case XmlPullParser.START_DOCUMENT: // 文档开始事件
                            config_info = new ConfigInfo();
                            break;
                        case XmlPullParser.START_TAG: // 标签开始
                            if("EMap".equals(tag_name)) {

                            } else if("WebService".equals(tag_name)) {
                                config_info.setNamespace(parser.getAttributeValue("", "namespace")); // String getAttributeValue(String namespaceUri, String localName) 返回带有名称空间和 localName 的属性的规范化属性值
                                config_info.setServer(parser.getAttributeValue("", "server"));
                                config_info.setProtocol(parser.getAttributeValue("", "protocol"));
                            } else if("Register".equals(tag_name)) {
                                config_info.setRegister_name(parser.getAttributeValue("", "name"));
                                config_info.setRegister_path(parser.getAttributeValue("", "path"));
                                config_info.setRegister_var1_name(parser.getAttributeValue("", "var1name"));
                                config_info.setRegister_result_name(parser.getAttributeValue("", "resultname"));
                            } else if("Login".equals(tag_name)) {
                                config_info.setLogin_name(parser.getAttributeValue("", "name"));
                                config_info.setLogin_path(parser.getAttributeValue("", "path"));
                                config_info.setLogin_var1_name(parser.getAttributeValue("", "var1name"));
                                config_info.setLogin_result_name(parser.getAttributeValue("", "resultname"));
                            } else{

                            }
                            break;
                        case XmlPullParser.END_TAG: // 标签结束
                            break;
                        default:
                            break;
                    }

                    event_type = parser.next(); // 处理下一个事件
                }

                Logger.getLogger(this.getClass()).info(config_info.toString());
            }catch (Exception e) {
                Logger.getLogger(this.getClass()).error(e.toString());
            }
        } else {
            Logger.getLogger(this.getClass()).error(String.format("配置文件%s不存在", config_file));
        }
    }

    // 获取配置文件信息
    public ConfigInfo getConfigInfo() {
        return config_info;
    }
}
