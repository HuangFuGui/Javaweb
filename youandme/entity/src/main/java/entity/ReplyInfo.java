package entity;

import java.sql.Timestamp;

public class ReplyInfo {

    private int replyId;
    private int commentId;
    private int sendId;
    private String receiveUsername;
    private String replyText;
    private int likeNum;
    private Timestamp replyTime;
    private User sendUser;

    public ReplyInfo() {
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
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

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

    @Override
    public String toString() {
        return "ReplyInfo{" +
                "replyId=" + replyId +
                ", commentId=" + commentId +
                ", sendId=" + sendId +
                ", receiveUsername='" + receiveUsername + '\'' +
                ", replyText='" + replyText + '\'' +
                ", likeNum=" + likeNum +
                ", replyTime=" + replyTime +
                ", sendUser=" + sendUser +
                '}';
    }
}
