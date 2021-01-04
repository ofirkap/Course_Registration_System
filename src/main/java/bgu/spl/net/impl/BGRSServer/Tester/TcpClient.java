/*
package bgu.spl.net.impl.BGRSServer.Tester;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TcpClient {

    private static void run(String serverName, int port, String line) {
        //try with resources: automatically close the defined resources when complete or on failure
        try(Socket socket = new Socket(serverName, port);
            BufferedReader userIn = new BufferedReader(line);
            BufferedWriter out    = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // the next line is not used since we do not listen to the server's replies.
            BufferedReader in     = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            while ((line = userIn.readLine()) != null) {
                out.write(line);
                out.newLine(); // make sure to add the end of line as br.readLine strips it
                out.flush();
            }
            System.out.println(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java TcpClient host port");
            return;
        }
        int numOfThreads = 10;
        run(args[0], Integer.parseInt(args[1]));
    }
}*/
