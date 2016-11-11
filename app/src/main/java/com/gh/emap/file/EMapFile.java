package com.gh.emap.file;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;

import com.gh.emap.MainActivity;
import com.gh.emap.manager.LogManager;
import com.gh.emap.model.EMap;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by GuHeng on 2016/11/9.
 */
public class EMapFile {
    private Context mContext;
    private EMap mEMap;

    public EMapFile(Context context) {
        this.mContext = context;
    }

    public void init() {
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
                            this.mEMap = new EMap();
                            break;
                        case XmlPullParser.START_TAG: // 标签开始
                            if("EMap".equals(tagName)) {

                            } else if("WebService".equals(tagName)) {
                                this.mEMap.setNameSpace(parser.getAttributeValue("", "NameSpace"));
                                this.mEMap.setServer(parser.getAttributeValue("", "Server"));
                                this.mEMap.setPort(parser.getAttributeValue("", "Port"));
                                this.mEMap.setProtocol(parser.getAttributeValue("", "Protocol"));
                                this.mEMap.setSoapVersion(parser.getAttributeValue("", "SoapVersion"));
                            } else if("Register".equals(tagName)) {
                                this.mEMap.setRegisterMethodName(parser.getAttributeValue("", "Method"));
                                this.mEMap.setRegisterUrlPath(parser.getAttributeValue("", "UrlPath"));
                                this.mEMap.setRegisterMethodParam1Name(parser.getAttributeValue("", "Param1Name"));
                                this.mEMap.setRegisterMethodReturnParamName(parser.getAttributeValue("", "ReturnParamName"));
                            } else if("Login".equals(tagName)) {
                                this.mEMap.setLoginMethodName(parser.getAttributeValue("", "Method"));
                                this.mEMap.setLoginUrlPath(parser.getAttributeValue("", "UrlPath"));
                                this.mEMap.setLoginMethodParam1Name(parser.getAttributeValue("", "Param1Name"));
                                this.mEMap.setLoginMethodReturnParamName(parser.getAttributeValue("", "ReturnParamName"));
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

                ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mInfo,
                        this.mEMap.toString());
            }catch (Exception e) {
                ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                        e.toString());
            }
        } else {
            ((MainActivity)this.mContext).getMainManager().getLogManager().log(this.getClass(), LogManager.LogLevel.mError,
                    String.format("配置文件%s不存在", configFile));
        }
    }

    public EMap getEMap() {
        return this.mEMap;
    }

    private String getFile() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString() + File.separator
                    + "EMap" + File.separator
                    + "EMap.config";
        } else {
            return null;
        }
    }
}
