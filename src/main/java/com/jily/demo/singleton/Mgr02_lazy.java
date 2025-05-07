package com.jily.demo.singleton;

//lazy loading 懒汉式，按需式初始化，但是带来多线程访问时可能new多个实例的问题
public class Mgr02_lazy {

    private static Mgr02_lazy INSTANCE;
    //设为private无法被new
    private Mgr02_lazy(){
    }

    private static Mgr02_lazy getInstance(){
        //理想情况：第一次调用为空时，才创建对象
        if(INSTANCE == null){
            INSTANCE = new Mgr02_lazy();
        }
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                System.out.println(Mgr02_lazy.getInstance().hashCode());
//            }).start();
            //最简单的内部类的写法
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Mgr02_lazy.getInstance().hashCode());
                }
            }).start();
        }
    }
}
