package dao;

import entity.PluploadFile;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface PluploadDao {

    /**
     * 用户没上传一个文件就向数据库保存相关信息
     * @param fileName
     * @param uploadUsername
     * @param uploadTime
     * @return
     */
    int insertFileInfo(@Param("fileName") String fileName,
                       @Param("uploadUsername") String uploadUsername,
                       @Param("uploadTime")Timestamp uploadTime);

    /**
     * 根据用户名查找该用户上传的全部文件
     * @param uploadUsername
     * @return
     */
    List<PluploadFile> selectFileByUsername(@Param("uploadUsername") String uploadUsername);

    PluploadFile selectFileById(@Param("id") int id);

    void deleteInfoOfFile(@Param("id") int id);
}
