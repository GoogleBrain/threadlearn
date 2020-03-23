public class Phone {

    public synchronized  void message(){
        System.out.println(Thread.currentThread().getName()+"》》》》》  发送短信");
        music();
    }

    public synchronized  void music(){
        System.out.println(Thread.currentThread().getName()+"》》》》》  音乐");
    }
}
