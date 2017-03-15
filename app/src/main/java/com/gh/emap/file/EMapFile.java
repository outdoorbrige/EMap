package com.gh.emap.file;

import android.os.Environment;
import android.util.Xml;

import com.gh.emap.MainActivity;
import com.gh.emap.managerA.LogManager;
import com.gh.emap.model.EMap;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by GuHeng on 2016/11/9.
 * 解析配置文件
 */
public class EMapFile {
    private MainActivity mMainActivity;
    private EMap mEMap;

    public EMapFile(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    public void init() {
        mEMap = new EMap();

        loadFile();
    }

    public EMap getEMap() {
        return mEMap;
    }

    private String getFile() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString() + File.separator +
                    mMainActivity.getApplationName() + File.separator +
                    "EMap.config";
        } else {
            return null;
        }
    }

    private void loadFile() {
        String configFile = getFile();
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
                            break;
                        case XmlPullParser.START_TAG: // 标签开始
                            if(mMainActivity.getApplationName().equals(tagName)) {

                            } else if("WebService".equals(tagName)) {
                                mEMap.setNameSpace(parser.getAttributeValue("", "NameSpace"));
                                mEMap.setServer(parser.getAttributeValue("", "Server"));
                                mEMap.setPort(parser.getAttributeValue("", "Port"));
                                mEMap.setProtocol(parser.getAttributeValue("", "Protocol"));
                                mEMap.setSoapVersion(parser.getAttributeValue("", "SoapVersion"));
                            } else if("Register".equals(tagName)) {
                                mEMap.setRegisterMethodName(parser.getAttributeValue("", "Method"));
                                mEMap.setRegisterUrlPath(parser.getAttributeValue("", "UrlPath"));
                                mEMap.setRegisterMethodParam1Name(parser.getAttributeValue("", "Param1Name"));
                                mEMap.setRegisterMethodReturnParamName(parser.getAttributeValue("", "ReturnParamName"));
                            } else if("Login".equals(tagName)) {
                                mEMap.setLoginMethodName(parser.getAttributeValue("", "Method"));
                                mEMap.setLoginUrlPath(parser.getAttributeValue("", "UrlPath"));
                                mEMap.setLoginMethodParam1Name(parser.getAttributeValue("", "Param1Name"));
                                mEMap.setLoginMethodReturnParamName(parser.getAttributeValue("", "ReturnParamName"));
                            } else if("Logout".equals(tagName)) {
                                mEMap.setLogoutMethodName(parser.getAttributeValue("", "Method"));
                                mEMap.setLogoutUrlPath(parser.getAttributeValue("", "UrlPath"));
                                mEMap.setLogoutMethodParam1Name(parser.getAttributeValue("", "Param1Name"));
                                mEMap.setLogoutMethodReturnParamName(parser.getAttributeValue("", "ReturnParamName"));
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
                inputStream.close();
                fileInputStream.close();

                mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mInfo,
                        mEMap.toString());
            }catch (Exception e) {
                mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError,
                        e.getStackTrace().toString());
            }
        } else {
            mMainActivity.getMainManager().getLogManager().log(LogManager.LogLevel.mError,
                    String.format("配置文件%s不存在", configFile));
        }
    }
}
