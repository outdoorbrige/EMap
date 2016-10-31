package cn.com.sgcc.epri.emap.file;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import cn.com.sgcc.epri.emap.MainActivity;
import cn.com.sgcc.epri.emap.model.ConfigInfo;
import cn.com.sgcc.epri.emap.util.Log4jLevel;
import cn.com.sgcc.epri.emap.util.PhoneResources;
import cn.com.sgcc.epri.emap.util.MainActivityContext;

/**
 * Created by GuHeng on 2016/10/19.
 * 解析配置文件
 */
public class ConfigFile extends MainActivityContext {
    private ConfigInfo mConfigInfo; // 配置文件对象

    // 构造函数
    public ConfigFile(MainActivity mainActivity) {
        super(mainActivity);
    }

    // 初始化
    public void init() {
        String configFile = PhoneResources.getConfigFile(mMainActivity);
        File file = new File(configFile);
        if(file.exists()) { // 判断文件是否存在
            try {
                FileInputStream fileInputStream = new FileInputStream(file); // 获取输入流对象
                InputStream inputStream = new BufferedInputStream(fileInputStream);

                XmlPullParser parser = Xml.newPullParser(); // 得到PULL解析器
                parser.setInput(inputStream, "UTF-8"); // 设置输入流和编码

                int eventType = parser.getEventType(); // 得到第一个时间类型
                while(eventType != XmlPullParser.END_DOCUMENT) { // 如果事件类型不是文档结束的话，则不断处理事件
                    String tagName = parser.getName(); // 获得解析器当前元素的名称
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT: // 文档开始事件
                            mConfigInfo = new ConfigInfo();
                            break;
                        case XmlPullParser.START_TAG: // 标签开始
                            if("EMap".equals(tagName)) {

                            } else if("WebService".equals(tagName)) {
                                mConfigInfo.setNameSpace(parser.getAttributeValue("", "NameSpace"));
                                mConfigInfo.setServer(parser.getAttributeValue("", "Server"));
                                mConfigInfo.setPort(parser.getAttributeValue("", "Port"));
                                mConfigInfo.setProtocol(parser.getAttributeValue("", "Protocol"));
                                mConfigInfo.setSoapVersion(parser.getAttributeValue("", "SoapVersion"));
                            } else if("Register".equals(tagName)) {
                                mConfigInfo.setRegisterMethodName(parser.getAttributeValue("", "Method"));
                                mConfigInfo.setRegisterUrlPath(parser.getAttributeValue("", "UrlPath"));
                                mConfigInfo.setRegisterMethodParam1Name(parser.getAttributeValue("", "Param1Name"));
                                mConfigInfo.setRegisterMethodReturnParamName(parser.getAttributeValue("", "ReturnParamName"));
                            } else if("Login".equals(tagName)) {
                                mConfigInfo.setLoginMethodName(parser.getAttributeValue("", "Method"));
                                mConfigInfo.setLoginUrlPath(parser.getAttributeValue("", "UrlPath"));
                                mConfigInfo.setLoginMethodParam1Name(parser.getAttributeValue("", "Param1Name"));
                                mConfigInfo.setLoginMethodReturnParamName(parser.getAttributeValue("", "ReturnParamName"));
                            } else{

                            }
                            break;
                        case XmlPullParser.END_TAG: // 标签结束
                            break;
                        default:
                            break;
                    }

                    eventType = parser.next(); // 处理下一个事件
                }

                mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mInfo, mConfigInfo.toString());
            }catch (Exception e) {
                mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mError, e.toString());
            }
        } else {
            mMainActivity.getLog4jManager().log(this.getClass(), Log4jLevel.mError, String.format("配置文件%s不存在", configFile));
        }
    }

    // 获取配置文件信息
    public ConfigInfo getConfigInfo() {
        return mConfigInfo;
    }
}
