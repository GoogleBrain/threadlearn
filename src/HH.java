import java.util.concurrent.atomic.AtomicInteger;

public class HH {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger();
        i.getAndIncrement();
        System.out.println(i);
    }
}
