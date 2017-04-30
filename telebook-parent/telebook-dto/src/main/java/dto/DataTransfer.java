package dto;

/**
 * Created by huangfugui on 2016/10/24.
 */
//数据传输对象，负责Controller与表示逻辑层js的数据传输
public class DataTransfer<T> {

    private T data;//泛型数据
    private boolean success;//判断是否执行成功
    private String info;//提示信息

    public DataTransfer(boolean success) {
        this.success = success;
    }

    public DataTransfer(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
