package dao;

import entity.Contacts;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by huangfugui on 2016/10/20.
 */
public interface ContactsDao {

    /**
     * 显示电话簿所有信息
     * @return
     */
    List<Contacts> selectAllContacts(int accountId);

    /**
     *
     * 根据contact_id唯一查找联系人
     * @param contactId
     * @return
     */
    Contacts selectContactById(int contactId);

    /**
     * 根据id删除某个联系人
     * @param contactId
     * @return
     */
    int deleteContactById(int contactId);

    /**
     * 新增联系人
     * @param accountId
     * @param headImg
     * @param contactName
     * @param contactPhone
     * @param contactAddress
     * @return
     */
    int insertContact(@Param("accountId") int accountId,
                      @Param("headImg") String headImg,
                      @Param("contactName") String contactName,
                      @Param("contactPhone") String contactPhone,
                      @Param("contactAddress") String contactAddress);

    /**
     * 根据id修改联系人信息
     * @param contactId
     * @param headImg
     * @param contactName
     * @param contactPhone
     * @param contactAddress
     * @return
     */
    int updateContact(@Param("contactId") int contactId,
                      @Param("headImg") String headImg,
                      @Param("contactName") String contactName,
                      @Param("contactPhone") String contactPhone,
                      @Param("contactAddress") String contactAddress);
}
