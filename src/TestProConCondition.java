import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestProConCondition {
    /**
     * 完善版本的生产者，消费者；
     * @param args
     */
    public static void main(String[] args) {
        User user = new User();
        new Thread(new Pro(user),"生产者A").start();
        new Thread(new Pro(user),"生产者B").start();
        new Thread(new Cons(user),"消费者C").start();
        new Thread(new Cons(user),"消费者D").start();
    }
}


class User{
    private int a=0;

    private Lock lock= new ReentrantLock();
    private Condition condition = lock.newCondition();

    public   void get(){
        lock.lock();//2次
        try{
            while(a>=1){//次数使用while是为了防止虚假唤醒问题的出现；
                System.out.println("产品已满");
                try {
                   condition.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+ ++a);
            condition.signal();
        }finally {
            lock.unlock();
        }


    }

    public   void sale(){//1次
        lock.lock();
        try {
            while(a<=0){
                System.out.println("缺货");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+ --a);
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}

class Pro implements  Runnable{

    private User user;

    public Pro(User user){
        this.user = user;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            user.get();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Cons implements Runnable{

    public User user;
    public Cons(User user){
        this.user = user;
    }
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            user.sale();
        }
    }
}
