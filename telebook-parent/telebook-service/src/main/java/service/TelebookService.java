package service;

import entity.Contacts;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by huangfugui on 2016/10/20.
 */
public interface TelebookService {

    List<Contacts> showAllContacts(int accountId);

    Contacts showContactById(int contactId);

    int login(String account,String password);

    boolean logout(HttpServletRequest request);

    int register(String account,String password);

    boolean addContact(int accountId,String headImg,String contactName,String contactPhone,String contactAddress);

    boolean dropContact(int contactId);

    boolean modifyContact(int contactId,String headImg,String contactName,String contactPhone,String contactAddress);

    String headImgChange(HttpServletRequest request);

}
