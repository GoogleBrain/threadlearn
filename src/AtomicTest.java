import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    public static void main(String[] args) {
        AtomicInteger ato = new AtomicInteger(1);
        ato.compareAndSet(1,2);
        System.out.println(ato.get());
    }
}
