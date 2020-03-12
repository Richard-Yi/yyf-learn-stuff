package ionio.apidemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Richard_yyf
 * @version 1.0 2020/1/15
 */
public class ClientSocketTest {

    public static void main(String[] args) throws IOException {
        // connect with server
        Socket socket = new Socket("localhost", 12222);
        System.out.println("客户端连接服务端成功");
        // 发送消息给客户端
        socket.getOutputStream().write("hello world".getBytes());
        String input = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
        System.out.println("收到server 响应");
        System.out.println(input);
        socket.close();
    }
}
