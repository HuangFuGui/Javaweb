package client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huangfugui.rpc.*;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Client {
    public static void main(String[] args) {
        testLogin();
    }

    private static void testLogin() {
        TTransport transport = null;
        try {
            transport = new TFramedTransport(new TSocket("localhost", 19000));
            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            UserService.Client client = new UserService.Client(protocol);
            transport.open();

            Map<String, String> param = new HashMap<String, String>();
            param.put("username", "huangfugui");
            param.put("password", "123456");
            System.out.println("form: " + param);

            long startTime = System.currentTimeMillis();
            Response response = client.login(startTime, "java client request login", param);
            long endTime = System.currentTimeMillis();

            System.out.println(response.msg);
            System.out.println("Program exit, startTime: " + startTime + " ms, endTime: " + endTime + " ms, totalCost: " + (endTime - startTime) + " ms");
        } catch (TException x) {
            x.printStackTrace();
        } finally {
            transport.close();
        }
    }

    private static void testPrimeNumber() {
        TTransport transport = null;
        try {
            transport = new TFramedTransport(new TSocket("localhost", 19002));
            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            UtilService.Client client = new UtilService.Client(protocol);
            transport.open();

            int threshold = 50;
            System.out.println("threshold: " + threshold);

            long startTime = System.currentTimeMillis();
            List<Integer> list = client.primeNumber(startTime, "java client request primeNumber", threshold);
            long endTime = System.currentTimeMillis();

            System.out.println("Program exit, startTime: " + startTime + " ms, endTime: " + endTime + " ms, prime number within " + threshold + ":" + list);
        } catch (TException x) {
            x.printStackTrace();
        } finally {
            transport.close();
        }
    }
}