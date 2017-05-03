package org.huangfugui.ibatis.enums;

/**
 * Created by huangfugui on 2017/5/1.
 */
public enum UserType {

    USER(1,"普通用户"),ADMINISTRATOR(2,"管理员");

    private int id;
    private String name;

    UserType(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}