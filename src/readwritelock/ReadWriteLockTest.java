package readwritelock;
/**
 * 读写锁
 */

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args) {
        A a  = new A();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.set();
            }
        }).start();

        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    a.get();
                }
            }).start();
        }
    }
}

class A{

    private int a=0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    public void get(){
        lock.readLock().lock();
        try{
            System.out.println("获取>"+a);
        }finally {
            lock.readLock().unlock();
        }
    }

    public void set(){
        lock.writeLock().lock();
        try{
            System.out.println("设置属性");
            a=12;
        }finally {
            lock.writeLock().unlock();
        }
    }
}
