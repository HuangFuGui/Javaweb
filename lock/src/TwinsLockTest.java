import java.util.concurrent.locks.Lock;

public class TwinsLockTest {
    public static void main(String[] args) {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                        // 刚刚释放锁的线程进行sleep，将机会让给同步队列中刚被唤醒的线程（模拟公平锁）
                        // 若不进行sleep，直接回到while(true)，是非公平锁的场景
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker();
            worker.setDaemon(true);
            worker.start();
        }
        // 每隔1秒换行
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(1000);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
