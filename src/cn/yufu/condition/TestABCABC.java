package cn.yufu.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 循环输出5次ABC,得到ABCABCABCABCABC
 */
public class TestABCABC {
    public static void main(String[] args) {
        ABC abc = new ABC();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<=5;i++){
                    abc.A();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<=5;i++){
                    abc.B();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<=5;i++){
                    abc.C();
                }
            }
        }).start();
    }
}


class ABC{
    public int a=1;
    public Lock lock = new ReentrantLock();
    public Condition con1 = lock.newCondition();
    public Condition con2 = lock.newCondition();
    public Condition con3 = lock.newCondition();

    public void A(){
        lock.lock();
        try{
            if(a!=1) {
                con1.await();
            }
                System.out.println("A");
                a=2;
                con2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void B(){
        lock.lock();
        try{
            if(a!=2){
                    con2.await();
            }
                System.out.println("B");
                a=3;
                con3.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void C(){
        lock.lock();
        try{
            if(a!=3){
                    con3.await();
            }
                System.out.println("C");
                a=1;
                con1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
