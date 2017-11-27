import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public static AtomicReference<User> userAtomicReference = new AtomicReference<User>();
    public static void main(String[] args) {
        User user = new User("huangfugui", 21);
        userAtomicReference.set(user);
        User updateUser = new User("黄复贵", 21);
        userAtomicReference.compareAndSet(user, updateUser);
        System.out.println(userAtomicReference.get().getName());
        System.out.println(userAtomicReference.get().getAge());
    }
    static class User {
        private String name;
        private int age;
        public User(String name, int age) {
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
