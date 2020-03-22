public class VolatileLearn {
    public static void main(String[] args) {

        AA a = new AA();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    a.a=10;
                    System.out.println("当前值为>>>"+a.a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while(true){
            if(a.a==10){
                System.out.println("我看见了。。。。。。。");
            }

        }

    }
}

class AA{
      int a=0;
}

