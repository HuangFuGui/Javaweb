package mechanism;

/**
 * Created by huangfugui on 2017/5/8.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.out.println("hello " + name);
    }
}
