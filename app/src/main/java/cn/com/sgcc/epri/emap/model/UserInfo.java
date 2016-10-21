package cn.com.sgcc.epri.emap.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by GuHeng on 2016/10/14.
 * 用户信息
 */
public class UserInfo implements KvmSerializable {
    private String id; // ID
    private String username; // 用户名
    private String password; // 密码
    private String nickname; // 昵称
    private String telnumber; // 电话号码
    private String email; // 邮箱
    private String viplevel; // VIP等级
    private String createtime; // 创建时间
    private String modifytime; // 最后修改时间
    private String logintime; // 登录时间
    private boolean successed; // 操作是否成功
    private String emsg; // 错误信息

    public UserInfo() {
    }

    public UserInfo(String id, String username, String password, String nickname, String telnumber, String email, String viplevel, String createtime, String modifytime, String logintime, boolean successed, String emsg) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.telnumber = telnumber;
        this.email = email;
        this.viplevel = viplevel;
        this.createtime = createtime;
        this.modifytime = modifytime;
        this.logintime = logintime;
        this.successed = successed;
        this.emsg = emsg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelnumber() {
        return telnumber;
    }

    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getViplevel() {
        return viplevel;
    }

    public void setViplevel(String viplevel) {
        this.viplevel = viplevel;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public boolean isSuccessed() {
        return successed;
    }

    public void setSuccessed(boolean successed) {
        this.successed = successed;
    }

    public String getEmsg() {
        return emsg;
    }

    public void setEmsg(String emsg) {
        this.emsg = emsg;
    }

    public String toString() {
        return String.format(
                "id:%s, " +
                "username:%s, " +
                "password:%s, " +
                "nickname:%s, " +
                "telnumber:%s, " +
                "email:%s, " +
                "viplevel:%s, " +
                "createtime:%s, " +
                "modifytime:%s, " +
                "logintime:%s, " +
                "successed:%s, " +
                "emsg:%s",
                id,
                username,
                password,
                nickname,
                telnumber,
                email,
                viplevel,
                createtime,
                modifytime,
                logintime,
                successed,
                emsg);
    }

    @Override
    public Object getProperty(int index) {
        Object object = null;

        switch (index)
        {
            case 0:
                object = id;
                break;
            case 1:
                object = username;
                break;
            case 2:
                object = password;
                break;
            case 3:
                object = nickname;
                break;
            case 4:
                object = telnumber;
                break;
            case 5:
                object = email;
                break;
            case 6:
                object = viplevel;
                break;
            case 7:
                object = createtime;
                break;
            case 8:
                object = modifytime;
                break;
            case 9:
                object = logintime;
                break;
            case 10:
                object = successed;
                break;
            case 11:
                object = emsg;
                break;
            default:
                object = null;
                break;
        }

        return object;
    }

    @Override
    public int getPropertyCount() {

        return 12;
    }

    @Override
    public void setProperty(int index, Object object) {
        switch (index)
        {
            case 0:
                id = object.toString();
                break;
            case 1:
                username = object.toString();
                break;
            case 2:
                password = object.toString();
                break;
            case 3:
                nickname = object.toString();
                break;
            case 4:
                telnumber = object.toString();
                break;
            case 5:
                email = object.toString();
                break;
            case 6:
                viplevel = object.toString();
                break;
            case 7:
                createtime = object.toString();
                break;
            case 8:
                modifytime = object.toString();
                break;
            case 9:
                logintime = object.toString();
                break;
            case 10:
                successed = Boolean.getBoolean(object.toString());
                break;
            case 11:
                emsg = object.toString();
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int index, Hashtable hashtable, PropertyInfo propertyinfo) {
        switch (index)
        {
            case 0:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("id");
                break;
            case 1:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("username");
                break;
            case 2:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("password");
                break;
            case 3:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("nickname");
                break;
            case 4:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("telnumber");
                break;
            case 5:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("email");
                break;
            case 6:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("viplevel");
                break;
            case 7:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("createtime");
                break;
            case 8:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("modifytime");
                break;
            case 9:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("logintime");
                break;
            case 10:
                propertyinfo.setType(PropertyInfo.BOOLEAN_CLASS);
                propertyinfo.setName("successed");
                break;
            case 11:
                propertyinfo.setType(PropertyInfo.STRING_CLASS);
                propertyinfo.setName("emsg");
                break;
            default:
                break;
        }
    }
}
