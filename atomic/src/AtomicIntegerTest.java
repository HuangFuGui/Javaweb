/*

所谓原子操作,就是"不可中断的一个或一系列操作" 。

硬件级的原子操作：
在单处理器系统(UniProcessor)中，能够在单条指令中完成的操作都可以认为是" 原子操作"，因为中断只能发生于指令之间。
这也是某些CPU指令系统中引入了test_and_set、test_and_clear等指令用于临界资源互斥的原因。

在对称多处理器(Symmetric Multi-Processor)结构中就不同了，由于系统中有多个处理器在独立地运行，即使能在单条指
令中完成的操作也有可能受到干扰。在x86 平台上，CPU提供了在指令执行期间对总线加锁的手段。CPU芯片上有一条引线#HLOCK pin，
如果汇编语言的程序中在一条指令前面加上前缀"LOCK"，经过汇编以后的机器代码就使CPU在执行这条指令的时候把#HLOCK pin
的电位拉低，持续到这条指令结束时放开，从而把总线锁住，这样同一总线上别的CPU就暂时不能通过总线访问内存了，保证了这条
指令在多处理器环境中的原子性。

程序输出：
8165
10000

注意：atomic包下的工具类基本都采用volatile修饰value
* */

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    private static int sum = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i += 1) {
                        sum ++;
                        atomicInteger.getAndIncrement();
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sum);
        System.out.println(atomicInteger.get());
    }
}
