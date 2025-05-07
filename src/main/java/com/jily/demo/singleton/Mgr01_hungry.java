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
    //也可以用静态代码块来写，意义一样
//    private static final Mgr01_hungry INSTANCE;
//    static {
//       INSTANCE = new Mgr01_hungry();
//    }


    //new的构造方法设置为private，其他类就new不出来
    //其他方法只能用getInstance()来获取实例
    private Mgr01_hungry(){};

    //获取实例的方法，无论调用多少次，都是返回同一个实例
    public  static  Mgr01_hungry getInstance(){
        return  INSTANCE;
    }
    public void m(){System.out.println("m");}

    //验证，同一个实例
    public static void main(String[] args) {
        //方法1：用hashCode()方法来验证
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                //不同对象的hashCode是不同的
                System.out.println(Mgr01_hungry.getInstance().hashCode());
            }).start();
        }
        //方法2：用equals()方法来验证
        Mgr01_hungry mgr01Hungry1  = Mgr01_hungry.getInstance();
        Mgr01_hungry mgr01Hungry2 = Mgr01_hungry.getInstance();
        System.out.println(mgr01Hungry1.equals(mgr01Hungry2));
        System.out.println(mgr01Hungry1 == mgr01Hungry2);
    }
}
