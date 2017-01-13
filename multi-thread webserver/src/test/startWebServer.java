package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import thread.threadImpl;

/**
 * Created by huangfugui on 2017/1/13.
 */
public class startWebServer {

    public static void main(String[] args){
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try{
            serverSocket = new ServerSocket(6789);
            while(true){//一直监听来自客户端的请求

                //服务器端生成相应的Socket与客户端进行通信
                clientSocket = serverSocket.accept();

                //多态
                Runnable runnable = new threadImpl(clientSocket);
                Thread thread = new Thread(runnable);//创建单独线程处理客户端请求
                thread.start();//线程就绪
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
