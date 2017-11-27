import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NIOClient {
    private final static String addr = "127.0.0.1";
    private final static int port = 9090;
    private final static int size = 1024;
    // 处理编码和解码的字符集
    private Charset charset= Charset.forName("UTF-8");
    Selector selector = null;
    public void init() throws Exception {
        selector = Selector.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(addr, port);
        SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new ClientRead()).start();
        // 创建键盘输入流
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            // 读取键盘输入
            String input = scan.nextLine();
            // 将键盘输入的内容输出到SocketChannel中
            socketChannel.write(charset.encode(input));
        }
    }

    class ClientRead implements Runnable {
        @Override
        public void run() {
            try {
                for (;selector.select() > 0;) {
                    for (SelectionKey selectionKey : selector.selectedKeys()) {
                        selector.selectedKeys().remove(selectionKey);
                        if (selectionKey.isReadable()) {
                            // 使用NIO读取channel中的数据
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            ByteBuffer buff = ByteBuffer.allocate(size);
                            String content = "";
                            while (socketChannel.read(buff) > 0) {
                                buff.flip();
                                content += charset.decode(buff);
                            }
                            System.out.println("聊天信息：" + content);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new NIOClient().init();
    }
}