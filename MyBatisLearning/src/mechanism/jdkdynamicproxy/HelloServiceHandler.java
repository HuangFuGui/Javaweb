package mechanism.jdkdynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by huangfugui on 2017/5/8.
 */
public class HelloServiceHandler implements InvocationHandler {

    private Object target;//真实对象

    public Object bind(Object target){//返回代理对象
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                                        target.getClass().getInterfaces(),this);
    }

    //通过代理对象调用的方法会被路由分发到HelloServiceHandler的invoke(Object proxy, Method method, Object[] args)
    //真正起作用的是该Handler
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//注意方法参数
        System.out.println("JDK动态代理：方法调用前");
        Object result = method.invoke(target,args);//反射调用（剔除无数if else硬编码）
        System.out.println("JDK动态代理：方法调用后");
        return result;
    }
}
