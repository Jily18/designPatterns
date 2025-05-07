package com.jily.demo.singleton;

//懒汉式，加锁
public class Mgr03_lazy_lock {

    private static Mgr03_lazy_lock INSTANCE;
    private Mgr03_lazy_lock(){
    }

    //实际上只改动了这一句，就是给方法加上synchronized
    // 由于 getInstance() 方法是 static 的，所以 synchronized 锁定的是类对象 Mgr03_lazy_lock.class。
    // 这是因为 static 方法属于类本身，而不是某个具体的实例，所以同步锁作用于整个类对象。
    //但是也并非最佳方法，因为即使在实例已经创建之后 还是加锁，这可能会带来一定的性能开销。
    private static synchronized Mgr03_lazy_lock getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Mgr03_lazy_lock();
        }
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Mgr03_lazy_lock.getInstance().hashCode());
                }
            }).start();
        }
    }
}
