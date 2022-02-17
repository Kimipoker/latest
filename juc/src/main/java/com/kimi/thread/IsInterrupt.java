package com.kimi.thread;

import javafx.application.Application;
import javafx.stage.Stage;

public class IsInterrupt {

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            //如果当前线程被中断则退出循环
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread() + "hello");
            }
        });

        //启动子线程
        thread.start();
        //主线程休眠1s，以便中断前让子线程输出
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //中断子线程
        System.out.println("main thread interrupt thread");
        thread.interrupt();
        //等待子线程执行完毕
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main is over");

        }
}

 