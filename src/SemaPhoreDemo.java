import java.util.concurrent.Semaphore;

public class SemaPhoreDemo {
    public static void main(String[] args) {
        Semaphore sema=new Semaphore(3);
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sema.acquire();
                        System.out.println(Thread.currentThread().getName()+"抢到车位");
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName()+"释放车位>>>>>>>");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        sema.release();
                    }
                }
            }).start();
        }
    }
}
