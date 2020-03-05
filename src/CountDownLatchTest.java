import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始>>>"+new Date());
        CountDownLatch latch = new CountDownLatch(5);

        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=1;j<2;j++){
                        System.out.println("1");
                    }
                    latch.countDown();
                }
            }).start();
        }


        latch.await();

        System.out.println("结束>>>"+new Date());
    }
}
