package cn.yufu.pool;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pool {
    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for(int i=0;i<10;i++){
                final int b = i;
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("当前线程名>"+Thread.currentThread().getName()+">>>>"+b);
                    }
                });
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
