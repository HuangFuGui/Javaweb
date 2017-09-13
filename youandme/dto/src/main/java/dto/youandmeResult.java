package dto;

public class youandmeResult<T> {

    private T  data;//泛型数据

    private boolean success;//执行是否成功

    private String info;//提示信息

    public youandmeResult(boolean success,String info ) {
        this.success = success;
        this.info = info;
    }

    public youandmeResult(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public youandmeResult(T data, boolean success, String info) {
        this.data = data;
        this.success = success;
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String Info) {
        this.info = Info;
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
}
