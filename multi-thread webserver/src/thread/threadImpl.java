package thread;

import java.io.*;
import java.net.Socket;

/**
 * Created by huangfugui on 2017/1/13.
 */
public class threadImpl implements Runnable {

    /*
     * 1.The Java Virtual Machine allows an application to have multiple threads of execution running concurrently.
     * 2.The Runnable interface should be implemented by any class whose instances are intended to be executed by
     *   a thread. The class must define a method of no arguments called run.
     ************************************************************************************************************/

    Socket clientSocket = null;
    String requestURL = null;//客户端请求的URL
    String requestFile = null;//客户端请求的文件名

    public threadImpl(){

    }
    public threadImpl(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        PrintStream printStream = null;
        DataInputStream dataInputStream = null;
        try{
            //获得输入字节流
            InputStream inputStream = clientSocket.getInputStream();
            //An InputStreamReader is a bridge from byte streams to character streams:
            //It reads bytes and decodes them into characters using a specified charset.
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            //使用缓冲，提高读效率
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //获得客户端请求URL
            requestURL = bufferedReader.readLine();
            //获得客户端请求的文件名
            requestFile = new threadImpl().getRequestFile(requestURL);
            if(requestFile==null){
                return;
            }

            File file = new File(requestFile);
            printStream = new PrintStream(clientSocket.getOutputStream());
            if(file.exists()){
                //设置HTTP响应头信息
                printStream.println("HTTP/1.0 200 OK");
                printStream.println("Content_Type:text/html");
                printStream.println("Content_Length:"+(int)file.length());
                printStream.println("");
            }
            else{
                //输出错误页
                file = new File("error.html");
                //设置HTTP响应头信息
                printStream.println("HTTP/1.0 404 NOT FOUND");
                printStream.println("Content_Type:text/html");
                printStream.println("Content_Length:"+(int)file.length());
                printStream.println("");
            }
            //HTTP message body写入数据
            dataInputStream = new DataInputStream(new FileInputStream(file));
            byte[] bytes = new byte[1024];
            int len = 0;
            while((len=dataInputStream.read(bytes))>0){
                printStream.write(bytes,0,len);
            }
            printStream.flush();
            printStream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    //从URL中获得请求的文件名
    private String getRequestFile(String requestURL){
        String file = null;
        if(requestURL!=null){
            //[beginIndex , endIndex-1]
            file = requestURL.substring(requestURL.indexOf("/")+1,requestURL.lastIndexOf(" "));
        }
        return file;
    }
}
