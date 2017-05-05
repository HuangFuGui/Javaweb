package enums;

/**
 * Created by huangfugui on 2017/4/10.
 */
public enum Sex {

    MALE(1,"男"),FEMALE(2,"女"),NOTKNOWN(3,"双性人");
    private int id;
    private String name;

    private Sex(int id,String name){
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
