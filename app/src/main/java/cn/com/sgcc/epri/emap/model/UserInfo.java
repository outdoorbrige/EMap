package cn.com.sgcc.epri.emap.model;

import java.util.Date;

import cn.com.sgcc.epri.emap.util.BaseKvmSerializable;

/**
 * Created by GuHeng on 2016/10/14.
 * 用户信息
 */
public class UserInfo extends BaseKvmSerializable {
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
    private String emsg; // 错误信息

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
                "nickname:%s," +
                "telnumber:%s," +
                "email:%s," +
                "viplevel:%s," +
                "createtime:%s," +
                "modifytime:%s," +
                "logintime:%s," +
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
                emsg);
    }
}
