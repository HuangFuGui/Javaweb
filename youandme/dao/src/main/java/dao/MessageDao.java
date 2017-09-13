package dao;

import entity.Message;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface MessageDao {
    /**
     * 将信息插入数据库中保存
     * @param fromId
     * @param fromName
     * @param toId
     * @param messageText
     * @param messageDate
     * @return
     */
    int insertMessage(@Param("fromId") int fromId,
                      @Param("fromName") String fromName,
                      @Param("toId") int toId,
                      @Param("messageText") String messageText,
                      @Param("messageDate") Timestamp messageDate);

    List<Message> selectMessageOfTwo(@Param("fromId") int fromId,
                                     @Param("toId") int toId);
}
