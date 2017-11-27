public class WaitNotify {
    public static void main(String[] args) {
        Thread waitThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (WaitNotify.class) {
                    try {
                        System.out.println("waitThread开始wait");
                        WaitNotify.class.wait(5000);//wait只是在等notify，只有获得锁时才会返回。
                        System.out.println("waitThread从wait中返回");
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "waitThread");
        waitThread.start();

        try {
            Thread.sleep(1000);
        }catch (Exception e) {
            e.printStackTrace();
        }

        Thread notifyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (WaitNotify.class) {
                    WaitNotify.class.notify();
                    System.out.println("已经notify通知");
                    try {
                        Thread.sleep(10000);
                        System.out.println("notify线程退出");
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "notifyThread");
        notifyThread.start();
    }
}
