import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {

    public volatile Map<String,Integer> map = new HashMap<>();

    public ReentrantReadWriteLock rw= new ReentrantReadWriteLock();

    public void put(String key,Integer value){
        try {
            rw.writeLock().lock();
            System.out.println("开始放数据"+Thread.currentThread().getName()+","+key);
            map.put(key,value);
            Thread.sleep(300);
            System.out.println("放数据完成"+Thread.currentThread().getName()+","+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rw.writeLock().unlock();
        }
    }

    public void get(String key){
        try {
            rw.readLock().lock();
            System.out.println("读数据"+Thread.currentThread().getName()+","+key);
            Object o = map.get(key);
            Thread.sleep(300);
            System.out.println("读数据完成"+Thread.currentThread().getName()+",结果为》"+o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rw.readLock().unlock();
        }
    }

    public static void main(String[] args) {

        ReadWriteLock lo = new ReadWriteLock();
        for(int i=0;i<5;i++){
            final int a=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lo.put("A"+a,a);
                }
            }).start();
        }

        for(int i=0;i<5;i++){
            final int a=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lo.get("A"+a);
                }
            }).start();
        }
    }
}
