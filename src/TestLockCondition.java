import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockCondition {
    public static void main(String[] args) {
        ABC abc = new ABC();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    abc.A();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    abc.B();
                }
            }
        }).start();
    }
}


class ABC{
    private int a=1;
    public Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();

    public void A(){
        lock.lock();
        while(a!=1){
            try {
                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("A");
        a=2;
        c2.signal();
        lock.unlock();
    }
    public void B(){
        lock.lock();
        while(a!=2){
             try {
                 c2.await();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         System.out.println("B");
         a=1;
         c1.signal();
         lock.unlock();
    }
}
