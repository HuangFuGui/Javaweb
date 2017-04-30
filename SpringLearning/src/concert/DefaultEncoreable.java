package concert;

/**
 * Created by huangfugui on 2017/4/2.
 */
public class DefaultEncoreable implements Encoreable {
    @Override
    public void performEncore() {
        System.out.println("Spring AOP为类/对象添加新方法");
    }
}
