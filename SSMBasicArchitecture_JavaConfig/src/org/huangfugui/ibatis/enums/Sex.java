package org.huangfugui.ibatis.enums;

/**
 * Created by huangfugui on 2017/4/27.
 */
public enum Sex {

    MALE(1,"男"),FEMALE(2,"女");

    private int id;
    private String name;

    Sex(int id,String name){//默认私有private
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
