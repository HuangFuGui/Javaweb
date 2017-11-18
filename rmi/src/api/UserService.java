package api;

import model.User;

import java.rmi.RemoteException;
import java.util.List;

import java.rmi.Remote;

public interface UserService extends Remote {
    List<User> getUsers() throws RemoteException;
}
