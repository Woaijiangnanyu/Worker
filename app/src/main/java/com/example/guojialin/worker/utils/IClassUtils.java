package com.example.guojialin.worker.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class IClassUtils {

    //反射机制运用
    //获取某个对象的属性
    public Object getPropetry(Object owner, String fieldName) throws Exception {
        //获得该对象的Class
        Class ownerClass = owner.getClass();
        //通过Class得到该对象的属性
        Field field = ownerClass.getField(fieldName);
        //通过属性获得实例
        Object propetry = field.get(owner);
        return propetry;
    }

    //获取某个类的静态属性
    public Object getStaticPropetry(String className, String fieldName) throws Exception {
        Class ownerClass = Class.forName(className);
        Field field = ownerClass.getField(fieldName);
        Object propetry = field.get(ownerClass);
        return propetry;
    }

    //执行某对象的方法
    public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
        Class ownerClass = owner.getClass();
        //配置class 参数数组，作为寻找method的条件
        Class[] argsClass = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argsClass[i] = args[i].getClass();
        }
        //获取要执行的方法
        Method method = ownerClass.getMethod(methodName, argsClass);
        //执行方法的返回值
        return method.invoke(ownerClass, args);
    }

    //执行某个类的静态方法
    public Object invokeStaticMethod(Object owner, String mentodName, Object[] args) throws Exception {
        Class ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = ownerClass.getMethod(mentodName, argsClass);
        //参数为null
        return method.invoke(null, args);
    }

    //构建实例对象
    public Object newInstance(String className, Object[] args) throws Exception {
        Class newOwnClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argsClass[i] = args[i].getClass();
        }
        //得到构造子
        Constructor constructor = newOwnClass.getConstructor(argsClass);
        //返回实例对象
        return constructor.newInstance(args);
    }

    //判断是否为某个类的实例
    public boolean isInstance(Object obj, Class cls) {
        return cls.isInstance(obj);
    }

    //得到数组中的某个元素
    public Object getByArray(Object array, int index) {
        return Array.get(array, index);
    }
}
