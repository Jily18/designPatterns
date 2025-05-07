package com.jily.demo.singleton;

//懒汉式，加锁
public class Mgr04_lazy_double_check {

    private static Mgr04_lazy_double_check INSTANCE;
    private Mgr04_lazy_double_check(){
    }

    private static synchronized Mgr04_lazy_double_check getInstance(){
//        错误写法，并
//                if(INSTANCE == null){
//            //妄图通过减小同步代码块的方式提高效率，然后不可行
//            //问题是因为 判断和锁没有连在一起，虽然new加锁了，但是仍然可能判断出错
//            synchronized (Mgr04_lazy_better_lock.class){
//                INSTANCE = new Mgr04_lazy_better_lock();
//            }
//        }
        //复杂的双重判断写法，是可以的
        if(INSTANCE == null){//这句不能省略，否则也会浪费时间
            synchronized (Mgr04_lazy_double_check.class){
                //进来之后又判断了一次
                if(INSTANCE == null){
                    INSTANCE = new Mgr04_lazy_double_check();
                }
            }
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
                    System.out.println(Mgr04_lazy_double_check.getInstance().hashCode());
                }
            }).start();
        }
    }
}
