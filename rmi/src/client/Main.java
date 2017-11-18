package client;

import api.UserService;
import model.User;

import java.rmi.Naming;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            UserService userService = (UserService)Naming.lookup("rmi://127.0.0.1:6600/userService");
            List<User> userList = userService.getUsers();
            System.out.println(userList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
