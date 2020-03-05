public class TestVolatile {
    public static void main(String[] args) {
        A a = new A();
        Thread ss = new Thread(a);
        ss.start();

        while(true){
            if(a.flag){
                System.out.println("--------------------------");
                break;
            }
        }
    }
}
class A implements  Runnable{
    public volatile    boolean flag=false;
    @Override
    public void run() {
        flag=true;
        System.out.println("当前>>"+flag);
    }
}