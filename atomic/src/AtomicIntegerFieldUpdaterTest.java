import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {
    private static AtomicIntegerFieldUpdater<User> userAtomicIntegerFieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
    public static void main(String[] args) {
        User user = new User("huangfugui", 21);
        System.out.println(userAtomicIntegerFieldUpdater.getAndIncrement(user));
        System.out.println(userAtomicIntegerFieldUpdater.get(user));
        /*
        输出：
        21
        22
        * */
    }
    static class User {
        private String name;
        public volatile int age;// 必须是public且volatile
        public User (String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
    }
}
