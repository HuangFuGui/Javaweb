import dao.PluploadDao;
import entity.PluploadFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class PluploadDaoTest {

    @Autowired
    private PluploadDao pluploadDao;
    @Test
    public void testInsertFileInfo() throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        int insertResult = pluploadDao.insertFileInfo("asd.xml","ª∆∏¥πÛ",now);
        System.out.println(insertResult);
    }

    @Test
    public void testSelectFileByUsername() throws Exception {
        List<PluploadFile> list = pluploadDao.selectFileByUsername("—Ó«ßã√");
        for(PluploadFile pluploadFile:list){
            System.out.println(pluploadFile);
        }
    }
}