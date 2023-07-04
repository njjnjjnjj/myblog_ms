package com.njj.thread;

/**
 * @author 倪佳俊
 * @date 2023/05/24 22:03
 **/
public class Thead {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }

    public static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("run !");
        }
    }
}
