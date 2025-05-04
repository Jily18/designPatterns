package com.jily.demo.singleton;

/**
 * 饿汉式
 * 类加载都爱内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用
 * 唯一缺点：不论是否使用，类加载时就完成实例化
 */
public class Mgr01_hungry {
    //定义一个静态的实例
    private static final Mgr01_hungry INSTANCE = new Mgr01_hungry();

    //new的构造方法设置为private，其他类就new不出来
    //其他方法只能用getInstance()来获取实例
    private Mgr01_hungry(){};

    public void m(){System.out.println("m");}
}
