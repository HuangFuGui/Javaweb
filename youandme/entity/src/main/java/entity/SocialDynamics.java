package entity;

import java.sql.Timestamp;
import java.util.List;

public class SocialDynamics {

    private int dynamicsId;
    private int userId;
    private String dynamicsText;
    private String dynamicsFile;
    private Timestamp createTime;
    private int likeNum;
    private User user;//一对一，一条动态对应一个发布者

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public SocialDynamics() {
    }

    public int getDynamicsId() {
        return dynamicsId;
    }

    public void setDynamicsId(int dynamicsId) {
        this.dynamicsId = dynamicsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDynamicsText() {
        return dynamicsText;
    }

    public void setDynamicsText(String dynamicsText) {
        this.dynamicsText = dynamicsText;
    }

    public String getDynamicsFile() {
        return dynamicsFile;
    }

    public void setDynamicsFile(String dynamicsFile) {
        this.dynamicsFile = dynamicsFile;
    }

    @Override
    public String toString() {
        return "SocialDynamics{" +
                "dynamicsId=" + dynamicsId +
                ", userId=" + userId +
                ", dynamicsText='" + dynamicsText + '\'' +
                ", dynamicsFile='" + dynamicsFile + '\'' +
                ", createTime=" + createTime +
                ", likeNum=" + likeNum +
                ", user=" + user +
                '}';
    }
}
