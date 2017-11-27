import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;

public class NIOServer {
    private final static String addr = "127.0.0.1";
    private final static int port = 9090;
    private final static int size = 1024;
    // 实现编码、解码的字符集对象
    private Charset charset= Charset.forName("UTF-8");
    public void init() throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(addr, port);
        serverSocketChannel.socket().bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        for(;selector.select() > 0;) {
            for(SelectionKey selectionKey : selector.selectedKeys()) {
                // 从selector已选择的key集合中删除正在处理的selectionKey
                selector.selectedKeys().remove(selectionKey);
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // 获取该SelectionKey对应的Channel，该Channel中有可读的数据
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 定义接收数据的ByteBuffer
                    ByteBuffer buff = ByteBuffer.allocate(size);
                    String content = "";
                    while (socketChannel.read(buff) > 0) {
                        buff.flip();
                        content += charset.decode(buff);
                    }
                    System.out.println("==========" + content);

                    // 如果聊天信息不为空，就群发
                    if (content.length() > 0) {
                        // 遍历该selector里注册的所有SelectKey
                        for (SelectionKey selectionKey1 : selector.keys()) {
                            Channel channel = selectionKey1.channel();
                            if(channel instanceof SocketChannel) {
                                ((SocketChannel) channel).write(charset.encode(content));
                            }
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        new NIOServer().init();
    }
}
