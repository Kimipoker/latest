package com.kimi.thread;

public class Interrupt {
    public static void main(String[] args) {
        Thread threadone = new Thread(() -> {
            try {
                System.out.println("threadOne begin sleep for 2000 seconds");
                Thread.sleep(2000000);
                System.out.println("threadOne awaking");
            } catch (InterruptedException e) {
                System.out.println("threadOne is interrupted while sleeping");
                return;
            }
            System.out.println("thread0ne-leaving normally");
        });

        //启动线程
        threadone.start();
        //确保子线程进入休眠状态
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打断子线程的休眠，让子线程从sleep函数返回
        threadone.interrupt();
        //等待子线程执行完毕
        try {
            threadone.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread is over");

    }
}
