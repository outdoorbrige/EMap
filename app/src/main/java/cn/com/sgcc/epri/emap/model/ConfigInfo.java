package cn.com.sgcc.epri.emap.model;

/**
 * Created by GuHeng on 2016/10/18.
 * 配置信息
 */
public class ConfigInfo {
    private String namespace; // 命名空间
    private String server; // 服务器
    private String protocol; // 协议

    private String register_name; // 注册服务名称
    private String register_path; // 注册服务路径
    private String register_var1_name; // 注册服务参数名称
    private String register_result_name; // 注册服务返回值参数名称

    private String login_name; // 登录服务名称
    private String login_path; // 登录服务路径
    private String login_var1_name; // 登录服务参数名称
    private String login_result_name; // 登录服务返回值参数名称

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRegister_name() {
        return register_name;
    }

    public void setRegister_name(String register_name) {
        this.register_name = register_name;
    }

    public String getRegister_path() {
        return register_path;
    }

    public void setRegister_path(String register_path) {
        this.register_path = register_path;
    }

    public String getRegister_var1_name() {
        return register_var1_name;
    }

    public void setRegister_var1_name(String register_var1_name) {
        this.register_var1_name = register_var1_name;
    }

    public String getRegister_result_name() {
        return register_result_name;
    }

    public void setRegister_result_name(String register_result_name) {
        this.register_result_name = register_result_name;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_path() {
        return login_path;
    }

    public void setLogin_path(String login_path) {
        this.login_path = login_path;
    }

    public String getLogin_var1_name() {
        return login_var1_name;
    }

    public void setLogin_var1_name(String login_var1_name) {
        this.login_var1_name = login_var1_name;
    }

    public String getLogin_result_name() {
        return login_result_name;
    }

    public void setLogin_result_name(String login_result_name) {
        this.login_result_name = login_result_name;
    }

    public String toString() {
        return String.format(
                "namespace:%s, " +
                "server:%s, " +
                "protocol:%s, " +
                "register_name:%s, " +
                "register_path:%s, " +
                "register_var1_name:%s, " +
                "register_result_name:%s, " +
                "login_name:%s, " +
                "login_path:%s, " +
                "login_var1_name:%s, " +
                "login_result_name:%s",
                namespace,
                server,
                protocol,

                register_name,
                register_path,
                register_var1_name,
                register_result_name,

                login_name,
                login_path,
                login_var1_name,
                login_result_name);
    }
}
