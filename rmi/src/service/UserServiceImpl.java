package service;

import api.UserService;
import model.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    public UserServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public List<User> getUsers() throws RemoteException {
        System.out.println("UserService exec...!");
        List<User> userList = new ArrayList<User>();
        User user = new User(1, "黄复贵", "qwe123");
        userList.add(user);
        User user1 = new User(2, "huangfugui", "123456");
        userList.add(user1);
        return userList;
    }
}
