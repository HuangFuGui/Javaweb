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

        HelloServiceHandler helloServiceHandler = new HelloServiceHandler();
        HelloService proxy = (HelloService)helloServiceHandler.bind(new HelloServiceImpl());//bind后生成代理对象
        proxy.sayHello("黄复贵");//通过代理对象调用方法
        System.out.println(proxy.getClass().getName());//打印代理对象类对象的符号引用
        createProxyClassFile();//生成代理类的class文件

        /*HelloServiceCgLib helloServiceCgLib = new HelloServiceCgLib();
        HelloService proxy = (HelloService) helloServiceCgLib.getInstance(new HelloServiceImpl());
        proxy.sayHello("黄复贵");*/
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
