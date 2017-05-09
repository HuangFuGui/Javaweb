package mechanism.cglibdynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by huangfugui on 2017/5/8.
 */
public class HelloServiceCgLib implements MethodInterceptor {

    public Object getInstance(Object target){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /*
     CGLib采用了非常底层的字节码技术，其原理是通过字节码技术为一个类创建子类，并在子类中采用方法拦截的技术
     拦截所有父类方法的调用，顺势织入横切逻辑
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CgLib动态代理：方法调用前");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("CgLib动态代理：方法调用后");
        return result;
    }
}