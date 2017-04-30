import dao.ContactsDao;
import entity.Contacts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by huangfugui on 2016/10/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ContactsDaoTest {

    @Autowired
    ContactsDao contactsDao;

    @Test
    public void selectAllContacts() throws Exception {
        List<Contacts> contactsList = contactsDao.selectAllContacts(1);
        for (Contacts contact:contactsList){
            System.out.println(contact);
        }
    }

    @Test
    public void selectContactById() throws Exception {
        int contactId = 3;
        Contacts contacts = contactsDao.selectContactById(contactId);
        System.out.println(contacts);
    }
}