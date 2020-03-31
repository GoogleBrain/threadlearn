import java.util.concurrent.CountDownLatch;

public class CoundDownLatchLearn {
    public static void main(String[] args) {
        CountDownLatch down = new CountDownLatch(7);
        for(int i=0;i<7;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    down.countDown();
                }
            },"线程名字："+i).start();
        }

        try {
            down.await();
            System.out.println("所有线程执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
