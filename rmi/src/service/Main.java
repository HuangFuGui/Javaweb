package service;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) {
        try {
            // 注册通信端口
            LocateRegistry.createRegistry(6600);
            // 注册通信路由
            Naming.rebind("rmi://127.0.0.1:6600/userService", new UserServiceImpl());
            System.out.println("Service start...");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
