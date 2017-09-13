package entity;

import java.sql.Timestamp;

public class PluploadFile {

    private int id;
    private String fileName;
    private String uploadUsername;
    private Timestamp uploadTime;

    public PluploadFile(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadUsername() {
        return uploadUsername;
    }

    public void setUploadUsername(String uploadUsername) {
        this.uploadUsername = uploadUsername;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "PluploadFile{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", uploadUsername='" + uploadUsername + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
