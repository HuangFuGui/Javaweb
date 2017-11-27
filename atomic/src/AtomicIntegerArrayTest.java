import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        int[] value = new int[]{1, 2};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(value);// 复制一份数组
        atomicIntegerArray.getAndSet(0, 5);
        atomicIntegerArray.getAndIncrement(0);
        System.out.println(atomicIntegerArray.get(0));
        System.out.println(value[0]);
        /*
        输出：
        6
        1
        * */
    }
}
