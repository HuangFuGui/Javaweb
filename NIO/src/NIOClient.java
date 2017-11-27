import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {
    private final static int size = 1024;
    public static void main(String[] args) throws Exception {
        ByteBuffer echoBuffer = ByteBuffer.allocate(size);

        SocketChannel socketChannel  = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9090));

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        selector.select();
        Set selectedKeys = selector.selectedKeys();
        Iterator iterator = selectedKeys.iterator();
        while(iterator.hasNext()) {
            SelectionKey selectionKey = (SelectionKey)iterator.next();
            iterator.remove();
            if (selectionKey.isConnectable()) {
                if (socketChannel.isConnectionPending()) {
                    if (socketChannel.finishConnect()) {
                        echoBuffer.put("huangfugui in NIO test".getBytes());
                        echoBuffer.flip();
                        socketChannel.write(echoBuffer);
                        System.out.println("写入完毕");
                        socketChannel.close();
                    }
                }
            }
        }
    }
}