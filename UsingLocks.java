package threads;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import static java.lang.Thread.sleep;


public class UsingLocks {


    public static void main (String [] args){

        ExecutorService writerExecutor = Executors.newFixedThreadPool(5);
        ExecutorService readingExecutor = Executors.newFixedThreadPool(2);

        Map<String, String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        writerExecutor.submit(() -> {
            lock.writeLock().lock();
            try {
                sleep(1);
                map.put("test", "1st response");
                map.put("new", "2nd response");
            }
            catch(Exception e){
                System.out.println(e);
            }
            finally
             {
                lock.writeLock().unlock();
            }
        });

        Runnable readTask = () -> {
            lock.readLock().lock();
            try {
                String response = map.get("test");
                map.remove("test");
                if(response == null){
                    response = map.get("new");
                    map.remove("new");
                }
                System.out.println(response);

                sleep(1);
            } catch(Exception e){
                System.out.println(e);
            } finally {
                lock.readLock().unlock();
            }
        };

        readingExecutor.submit(readTask);
        readingExecutor.submit(readTask);


    }
}
