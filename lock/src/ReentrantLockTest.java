import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    // 1. 设置为true是公平锁，默认是非公平锁。
    // 2. 非公平锁可能导致线程"饥饿"，但仍被定义为默认实现，因为非公平锁比公平锁
    // 少了大量的线程切换，保证了更大的吞吐量。
    private static Lock lock = new ReentrantLock(true);
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new MyRunner(), "thread" + i);
            thread.start();
        }
    }
    private static class MyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }
    }
}
