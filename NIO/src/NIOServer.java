import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    private final static int size = 8;
    private final static int PORT = 9090;
    public static void main(String[] args) throws Exception {
        ByteBuffer echoBuffer = ByteBuffer.allocate(size);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(PORT);
        serverSocket.bind(inetSocketAddress);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("开始监听...");

        for(;;) {
            selector.select();
            Set selectedKeys = selector.selectedKeys();
            Iterator iterator = selectedKeys.iterator();
            while(iterator.hasNext()) {
                SelectionKey selectionKey = (SelectionKey)iterator.next();
                SocketChannel sc = null;
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    iterator.remove();
                } else if (selectionKey.isReadable()) {
                    sc = (SocketChannel) selectionKey.channel();
                    for (;;) {
                        echoBuffer.clear();
                        int total = sc.read(echoBuffer);
                        if (total <= 0) {
                            sc.close();
                            System.out.println("接受完毕，断开连接。");
                            break;
                        }
                        System.out.println("长度：" + total + "，内容：" + new String(echoBuffer.array(), 0, echoBuffer.position()));
                        echoBuffer.flip();
                    }
                    iterator.remove();
                }
            }
        }
    }
}
