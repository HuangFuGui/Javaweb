package entity;

import java.sql.Timestamp;

public class CommentInfo {
    private int commentId;
    private int sendId;
    private String receiveUsername;
    private String commentText;
    private int likeNum;
    private int commentNum;
    private Timestamp commentTime;
    private User sendUser;

    public CommentInfo() {
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public String getReceiveUsername() {
        return receiveUsername;
    }

    public void setReceiveUsername(String receiveUsername) {
        this.receiveUsername = receiveUsername;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "commentId=" + commentId +
                ", sendId=" + sendId +
                ", receiveUsername='" + receiveUsername + '\'' +
                ", commentText='" + commentText + '\'' +
                ", likeNum=" + likeNum +
                ", commentNum=" + commentNum +
                ", commentTime=" + commentTime +
                ", sendUser=" + sendUser +
                '}';
    }
}
