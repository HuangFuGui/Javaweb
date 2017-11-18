package server;

import java.util.Map;

import com.huangfugui.rpc.Response;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import com.huangfugui.rpc.UserService;

public class Server implements UserService.Iface {
    private final static int PORT = 19000;

    private final static String USERNAME = "huangfugui";
    private final static String PASSWORD = "123456";

    private final static int OK = 200;
    private final static String SUCCEED = "login succeed";
    private final static String FAILED = "username or password error";

    // thrift服务设置，只提供UserService服务
    public static void main(String[] as) {
        // 定义transport
        TNonblockingServerTransport serverTransport = null;
        try {
            serverTransport = new TNonblockingServerSocket(PORT);
        } catch (TTransportException e) {
            e.printStackTrace();
        }

        // 定义processor
        UserService.Processor<UserService.Iface> processor = new UserService.Processor<UserService.Iface>(new Server());

        // 定义protocol
        Factory protFactory = new TBinaryProtocol.Factory(true, true);

        // 定义server（设置server参数：transport、protocol、processor）
        TNonblockingServer.Args args = new TNonblockingServer.Args(serverTransport);
        args.processor(processor);
        args.protocolFactory(protFactory);
        TServer server = new TNonblockingServer(args);

        // 开启服务
        System.out.println("Start server on port " + PORT +"...");
        server.serve();
    }

    // UserService login服务实现
    public Response login(long callTime, String cliInfo, Map<String, String> paramMap) throws TException {
        // 服务端打印请求日志
        System.out.println("java UserService login()---cliInfo: " + cliInfo + ", callTime: " + callTime + " ms, params: " + paramMap);

        String username = paramMap.get("username");
        String password = paramMap.get("password");
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            return new Response(OK, SUCCEED);
        }
        return new Response(OK, FAILED);
    }
}