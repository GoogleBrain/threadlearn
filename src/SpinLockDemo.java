import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLockDemo {
    AtomicReference<Thread> ato = new AtomicReference<>();

    public void put(){
        Thread thread = Thread.currentThread();

        System.out.println("开始放置》》"+thread.getName());
        while(!ato.compareAndSet(null,thread)){
             System.out.println("在拼命的抢锁>>>>"+Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void release(){
        Thread thread = Thread.currentThread();
        ato.compareAndSet(thread,null);
        System.out.println("释放完毕>>>"+thread.getName());
    }

    public static void main(String[] args) {
        SpinLockDemo self = new SpinLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
            self.put();
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                self.release();
            }
        },"AAAA").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                self.put();
                self.release();
            }
        },"BB").start();

    }
}
