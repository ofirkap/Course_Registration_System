package bgu.spl.net.impl.BGRSServer.Tester;

import bgu.spl.net.impl.BGRSServer.BGRSMessageEncoderDecoder;
import bgu.spl.net.impl.BGRSServer.Message;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class TcpClient implements Runnable {

    private final Socket socket;

    public TcpClient(Socket socket) {
        this.socket = socket;
    }

    private void write(BGRSMessageEncoderDecoder encdec, Message toSend) {
        try (DataOutputStream socketWriter = new DataOutputStream(socket.getOutputStream())) {
            byte[] actualBytesToSend = encdec.encode(toSend);
            socketWriter.write(actualBytesToSend, 0, actualBytesToSend.length);
            socketWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        try (DataInputStream socketReader = new DataInputStream(socket.getInputStream())) {
            byte[] received = new byte[4];
            for (int i = 0; i < 4; i++)
                received[i] = socketReader.readByte();
            Message answer = new Message(bytesToShort(received, 0));
            answer.setReturnOPCode(bytesToShort(received, 2));
            if (answer.getOPCode() == 12) {
                received = new byte[1 << 10];
                int i = 0;
                do
                    received[i] = socketReader.readByte();
                while (received[i] != (byte) 0);
                answer.setReturnInfo(new String(received, 0, i, StandardCharsets.UTF_8));
            }
            System.out.println(Thread.currentThread().getName() + " -> " + answer.getOPCode() + " " + answer.getReturnOPCode() + answer.getReturnInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static short bytesToShort(byte[] arr, int index) {
        short result = (short) ((arr[index] & 0xff) << 8);
        result += (short) (arr[index + 1] & 0xff);
        return result;
    }

    @Override
    public void run() {
        String generatedUsername = UUID.randomUUID().toString().substring(0, 4);
        BGRSMessageEncoderDecoder encdec = new BGRSMessageEncoderDecoder();
        //register
        Message toSend = new Message((short) 2);
        toSend.setName(generatedUsername);
        toSend.setPass(generatedUsername);
        write(encdec,toSend);
        read();
        //login
        toSend = new Message((short) 3);
        toSend.setName(generatedUsername);
        toSend.setPass(generatedUsername);
        write(encdec,toSend);
        read();
        //course register
        toSend = new Message((short) 5);
        toSend.setCourseNum((short) 1);
        write(encdec,toSend);
        read();
        //logout
        toSend = new Message((short) 4);
        write(encdec,toSend);
        read();
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}