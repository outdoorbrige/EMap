package cn.com.sgcc.epri.emap.util;

/**
 * Created by GuHeng on 2016/10/14.
 * SOAP信息
 */
public class SoapInfo {
    // 命名空间
    public static final String namespace = "http://www.epri.sgcc.com.cn/";

    // 用户注册服务
    public static final String RegisterMethod = "UserRegister";
    public static final String RegisterEndPoint = "http://192.168.200.21/EMapWebServices/EMapDBServices.asmx";
    public static final String RegisterSoapAction = namespace + RegisterMethod;
    public static final String RegisterMethodVarName1 = "username";
    public static final String RegisterMethodVarName2 = "password";
    public static final String RegisterMethodVarName3 = "nickname";
    public static final String RegisterMethodVarName4 = "telnumber";
    public static final String RegisterMethodVarName5 = "email";
    public static final String RegisterMethodResultName = "UserRegisterResult";

    // 用户登录服务
    public static final String LoginMethod = "UserLogin";
    public static final String LoginEndPoint = "http://192.168.200.21/EMapWebServices/EMapDBServices.asmx";
    public static final String LoginSoapAction = namespace + LoginMethod;
    public static final String LoginMethodVarName1 = "username";
    public static final String LoginMethodVarName2 = "password";
    public static final String LoginMethodResultName = "UserLoginResult";
}
