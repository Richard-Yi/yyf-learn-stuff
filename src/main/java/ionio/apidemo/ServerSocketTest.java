package main.java.ionio.apidemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Richard_yyf
 * @version 1.0 2020/1/15
 */
public class ServerSocketTest {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(12222);
              // block until client is connected 阻塞到有客户端连接上
              Socket socket = serverSocket.accept()) {
            System.out.println("服务端连接客户端成功");
            String input = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
            System.out.println("收到client信息：");
            System.out.println(input);
        }
    }
}
