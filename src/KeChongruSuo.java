public class KeChongruSuo {
    public static void main(String[] args) {

        Phone phone =new Phone();
        new Thread(new Runnable() {
            @Override
            public void run() {
                phone.message();
            }
        },"线程1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                phone.message();
            }
        },"线程2").start();
    }
}
