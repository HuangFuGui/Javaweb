import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
    private final static String inFile = "/Users/huangfugui/JavaProjects/javathrift/proj.thrift";
    private final static String outFile = "/Users/huangfugui/JavaProjects/NIO/src/tmp.thrift";
    private final static int size = 1024;
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(inFile);
        FileOutputStream fileOutputStream = new FileOutputStream(outFile);
        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(size);
        for(;;) {
            // 清空缓冲区
            buffer.clear();
            // 从输入通道读取数据到缓冲区
            int total = inputChannel.read(buffer);
            // 判断是否有从输入通道读取到数据
            if (total == -1) {
                break;
            }
            // 将buffer position指针指向头部
            buffer.flip();
            // 把缓冲区数据写入输出通道
            outputChannel.write(buffer);
        }
    }
}