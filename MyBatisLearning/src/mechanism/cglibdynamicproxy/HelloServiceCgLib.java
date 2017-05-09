package mechanism.cglibdynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by huangfugui on 2017/5/8.
 */
public class HelloServiceCgLib implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CgLib动态代理：我准备说hello");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("CgLib动态代理：我说过hello了");
        return result;
    }
}
