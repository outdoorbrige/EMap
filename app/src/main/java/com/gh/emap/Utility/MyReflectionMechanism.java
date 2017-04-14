package com.gh.emap.utility;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by GuHeng on 2017/4/7.
 *
 * JAVA反射机制
 * 是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
 * 对于任意一个对象，都能够调用它的任意一个方法；
 * 这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
 *
 * Java反射机制主要提供了以下功能：
 * 在运行时判断任意一个对象所属的类；
 * 在运行时构造任意一个类的对象；
 * 在运行时判断任意一个类所具有的成员变量和方法；
 * 在运行时调用任意一个对象的方法；
 * 生成动态代理。
 */

public class MyReflectionMechanism {

    // 得到某个对象的属性
    public static Object getPropertyField(Object owner, String fieldName) throws Exception {
        Class ownerClass = owner.getClass();
        Field field = ownerClass.getDeclaredField(fieldName);
        field.setAccessible(true);

        return field;
    }

    // 得到某个类的静态属性
    public static Object getStaticPropertyField(String className, String fieldName) throws Exception {
        Class ownerClass = Class.forName(className);
        Field field = ownerClass.getDeclaredField(fieldName);
        field.setAccessible(true);

        return field;
    }

    // 执行某对象的方法
    @SuppressWarnings("unchecked")
    public static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
        Class ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        // 获取所有public/private/protected/默认
        // 方法的函数，如果只需要获取public方法，则可以调用getMethod.
        Method method = ownerClass.getDeclaredMethod(methodName,argsClass);
        method.setAccessible(true);

        return method.invoke(owner, args);
    }

    // 执行某个类的静态方法
    @SuppressWarnings("unchecked")
    public static Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception {
        Class ownerClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getDeclaredMethod(methodName,argsClass);
        method.setAccessible(true);

        return method.invoke(null, args);
    }

    // 新建实例
    @SuppressWarnings("unchecked")
    public static Object newInstance(String className, Object[] args) throws Exception {
        Class newOneClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Constructor cons = newOneClass.getDeclaredConstructor(argsClass);
        cons.setAccessible(true);

        return cons.newInstance(args);
    }

    // 判断是否为某个类的实例
    public static boolean isInstance(Object obj, Class cls) {
        return cls.isInstance(obj);
    }

    // 得到数组中的某个元素
    public static Object getByArray(Object array, int index) {
        return Array.get(array,index);
    }
}
