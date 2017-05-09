package mechanism;

import mechanism.cglibdynamicproxy.HelloServiceCgLib;
import mechanism.jdkdynamicproxy.HelloServiceHandler;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by huangfugui on 2017/5/8.
 */
public class Main {
    public static void main(String[] args){

        /*
        HelloServiceHandler helloServiceHandler = new HelloServiceHandler();
        HelloService proxy = (HelloService)helloServiceHandler.bind(new HelloServiceImpl());//bind后生成代理对象
        proxy.sayHello("黄复贵");//通过代理对象调用方法
        System.out.println(proxy.getClass().getName());//打印代理对象类对象的符号引用
        createProxyClassFile();//生成代理类的class文件

        public final class GenerateProxy extends Proxy implements HelloService
        1. 继承Proxy得到相应的InvocationHandler，从而在代理类中将请求委托，实现路由转发
        2. 字节码（class对象）生成需要实现相应接口，这也是JDK动态代理需要提供接口的原因（用传入的接口定义新类）
        */

        HelloServiceCgLib helloServiceCgLib = new HelloServiceCgLib();
        HelloService proxy = (HelloService) helloServiceCgLib.getInstance(new HelloServiceImpl());
        proxy.sayHello("黄复贵");
    }

    private static void createProxyClassFile(){
        String name = "GenerateProxy";
        byte[] data = ProxyGenerator.generateProxyClass(name,new Class[]{HelloService.class});//JDK动态代理字节码生成接口
        FileOutputStream out =null;
        try {
            out = new FileOutputStream(name+".class");
            out.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=out) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
