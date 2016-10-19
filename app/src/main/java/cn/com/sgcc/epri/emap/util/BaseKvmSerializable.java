package cn.com.sgcc.epri.emap.util;

import com.nineoldandroids.util.Property;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Vector;

/**
 * Created by GuHeng on 2016/10/18.
 */
public class BaseKvmSerializable implements KvmSerializable {

    // 将首字母大写
    public static String firstUpperCase(String str) {
        return String.valueOf(str.charAt(0)).toUpperCase().concat(str.substring(1));
    }

    //Returns the property at a specified index (for serialization)
    //通过索引返回特定属性（翻译：返回属性在指定的索引(序列化)）
    @Override
    public Object getProperty(int var1) {
        // 既然是要返回特定索引的属性值，那么我们何不直接通过反射取对应属性放回
        Field[] fields = this.getClass().getDeclaredFields();
        Field field = fields[var1];

        String name = field.getName();
        name = firstUpperCase(name);
        String getMethodName = "get";

        if(field.getType() == boolean.class || field.getType() == Boolean.class) {
            getMethodName = "is";
        }

        getMethodName += name;

        Method getMethod;
        Object object = null;

        try {
            getMethod = this.getClass().getMethod(getMethodName);
            getMethod.setAccessible(true);
            object = getMethod.invoke(this);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return object;
    }

    //return the number of serializable properties
    //返回属性的个数（翻译：返回的数量可序列化的属性）
    @Override
    public int getPropertyCount() {
        // TODO Auto-generated method stub
        //返回固定数量
        return this.getClass().getDeclaredFields().length;
    }

    // Fills the given property info record.
    //给相应索引的属性赋值（翻译：填充给定属性信息记录。）
    @Override
    public void setProperty(int var1, Object var2) {
        Field[] fields = this.getClass().getDeclaredFields();
        Field field = fields[var1];

        String name = field.getName();
        name = firstUpperCase(name);
        String setMethodName = "set" + name;

        Method method;

        try {
            method = this.getClass().getDeclaredMethod(setMethodName, field.getType());
            method.setAccessible(true);
            method.invoke(this, var2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //Sets the property with the given index to the given value.
    //根据index给PropertyInfo赋值参数 （翻译：属性与给定的索引设置为给定值。）
    @Override
    public void getPropertyInfo(int var1, Hashtable var2, PropertyInfo var3) {
        Field[] fields = this.getClass().getDeclaredFields();
        Field field = fields[var1];

        String name = field.getName();

        // 主要是设置type和name其他的需要可以继续添加
        var3.type = getTypeByClass(field.getType());
        var3.name = name;
    }

    /**
     **  根据类别获得 PropertyInfo 特定类别
     **  实际上除了统一类别这个没什么太多用为了心里好过而加
     **  你看下面对于这些类别的的定义就知道了
     **	public static final Class OBJECT_CLASS = new Object().getClass();
     **  public static final Class STRING_CLASS = "".getClass();
     **	public static final Class INTEGER_CLASS = new Integer(0).getClass();
     **	public static final Class LONG_CLASS = new Long(0).getClass();
     **	public static final Class BOOLEAN_CLASS = new Boolean(true).getClass();
     **	public static final Class VECTOR_CLASS = new java.util.Vector().getClass();
     **/
    public Class getTypeByClass(Class cls) {
        if(cls.isAssignableFrom(Boolean.class) || cls.isAssignableFrom(boolean.class)) {
            return PropertyInfo.BOOLEAN_CLASS;
        } else if(cls.isAssignableFrom(String.class)) {
            return PropertyInfo.STRING_CLASS;
        } else if(cls.isAssignableFrom(Integer.class) || cls.isAssignableFrom(int.class) ||
                cls.isAssignableFrom(Byte.class) || cls.isAssignableFrom(byte.class)) {
            return PropertyInfo.INTEGER_CLASS;
        } else if(cls.isAssignableFrom(Vector.class)) {
            return PropertyInfo.VECTOR_CLASS;
        } else if(cls.isAssignableFrom(Long.class) || cls.isAssignableFrom(long.class)) {
            return PropertyInfo.LONG_CLASS;
        } else {
            return PropertyInfo.OBJECT_CLASS;
        }
    }
}























































