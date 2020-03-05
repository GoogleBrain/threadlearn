import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockLearn {
    public static void main(String[] args) {
        My my = new My();
        new Thread(my,"线程1").start();
        new Thread(my,"线程2").start();
        new Thread(my,"线程3").start();
    }
}

class My implements Runnable{
    private int a=100;
    private Lock lock = new ReentrantLock();
    @Override
    public void run() {
        while(true){
            lock.lock();
            if(a>0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"剩余票数>>>"+(a--));
            }else{
                lock.unlock();
                break;
            }
            lock.unlock();
        }
    }
}
