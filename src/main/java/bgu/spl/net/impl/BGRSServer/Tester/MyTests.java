package bgu.spl.net.impl.BGRSServer.Tester;

import java.io.IOException;
import java.net.Socket;

public class MyTests {
    public static void main(String[] args) {
        int numberOfThreads = 1;
        try {
            Socket socket = new Socket("localhost",7777);
            for (int i = 0;i<numberOfThreads;i++){
                TcpClient client = new TcpClient(socket);
                Thread th = new Thread(client);
                th.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
