import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier ba = new CyclicBarrier(6, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有人凑齐了。。。。");
            }
        });

        for(int i=0;i<6;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        ba.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            },"当前线程:"+i).start();
        }
    }
}
