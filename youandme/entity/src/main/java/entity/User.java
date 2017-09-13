package entity;

public class User {

    private int userId;//数据库中为user_id，驼峰转换
    private String username;
    private String password;
    private String email;
    private String address;
    private String description;
    private String headImg;
    private String joinTime;
    private int dynamicsNum;
    private int followingNum;
    private int followersNum;

    public User() {
    }

    public User(int userId, String username, String headImg, String address) {
        this.userId = userId;
        this.username = username;
        this.headImg = headImg;
        this.address = address;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getFollowingNum() {
        return followingNum;
    }

    public void setFollowingNum(int followingNum) {
        this.followingNum = followingNum;
    }

    public int getDynamicsNum() {
        return dynamicsNum;
    }

    public void setDynamicsNum(int dynamicsNum) {
        this.dynamicsNum = dynamicsNum;
    }

    public int getFollowersNum() {
        return followersNum;
    }

    public void setFollowersNum(int followersNum) {
        this.followersNum = followersNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", headImg='" + headImg + '\'' +
                ", joinTime='" + joinTime + '\'' +
                ", dynamicsNum=" + dynamicsNum +
                ", followingNum=" + followingNum +
                ", followersNum=" + followersNum +
                '}';
    }
}
