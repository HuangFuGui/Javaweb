package dtoPackage;

/**暴露秒杀地址DTO
 * Created by Administrator on 2016/5/24.
 */
public class Exposer {

    private boolean exposed;

    //一种加密措施
    private String md5;

    private int seckillId;

    //系统当前时间（毫秒）
    private long now;

    private long start;

    private long end;

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    //constructor构造方法
    public Exposer(String md5, boolean exposed, int seckillId) {
        this.md5 = md5;
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public Exposer(long start, long end, long now, boolean exposed,int seckillId) {
        this.start = start;
        this.seckillId = seckillId;
        this.end = end;
        this.now = now;
        this.exposed = exposed;
    }

    public Exposer(boolean exposed, int seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
