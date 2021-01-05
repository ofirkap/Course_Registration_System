package bgu.spl.net.impl.BGRSServer.Tester;

import bgu.spl.net.impl.BGRSServer.Message;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class TesterClient implements Runnable {

    DataInputStream socketReader;
    DataOutputStream socketWriter;

    public TesterClient(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            this.socketReader = new DataInputStream(socket.getInputStream());
            this.socketWriter = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(Message toSend) {
        try {
            System.out.println(Thread.currentThread().getName() + " -> trying to " + toSend.getOPCode() + " " + toSend.getName() + " " + ((toSend.getCourseNum() != 0) ? toSend.getCourseNum() : ""));
            byte[] actualBytesToSend = encode(toSend);
            socketWriter.write(actualBytesToSend, 0, actualBytesToSend.length);
            socketWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] encode(Message message) {
        byte[] ans = new byte[1 << 10];
        int index = 2;
        shortToBytes(message.getOPCode(), ans, 0);
        switch (message.getOPCode()) {
            case 1:
            case 2:
            case 3:
                byte[] nameAndPass = message.getName().getBytes(StandardCharsets.UTF_8);
                for (byte toAdd : nameAndPass) {
                    ans[index] = toAdd;
                    ans[index + nameAndPass.length + 1] = toAdd;
                    index++;
                }
                return Arrays.copyOf(ans, index + nameAndPass.length + 2);
            case 8:
                byte[] name = message.getName().getBytes(StandardCharsets.UTF_8);
                for (byte toAdd : name)
                    ans[index++] = toAdd;
                return Arrays.copyOf(ans, index + 1);
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
                shortToBytes((short) message.getCourseNum(), ans, index);
                return Arrays.copyOf(ans, 4);
            default:
                return Arrays.copyOf(ans, 2);
        }
    }

    private void read() {
        try {
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
            System.out.println(Thread.currentThread().getName() + " -> " + ((answer.getOPCode() == 12) ? "ACK " : "ERR ") + answer.getReturnOPCode() + answer.getReturnInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private short bytesToShort(byte[] arr, int index) {
        short result = (short) ((arr[index] & 0xff) << 8);
        result += (short) (arr[index + 1] & 0xff);
        return result;
    }

    private void shortToBytes(short num, byte[] arr, int index) {
        arr[index] = (byte) ((num >> 8) & 0xFF);
        arr[index + 1] = (byte) (num & 0xFF);
    }

    @Override
    public void run() {
        String generatedUsername = UUID.randomUUID().toString().substring(0, 4);
        //register
        Message toSend = new Message((short) 2);
        toSend.setName(generatedUsername);
        toSend.setPass(generatedUsername);
        write(toSend);
        read();
        //login
        toSend = new Message((short) 3);
        toSend.setName(generatedUsername);
        toSend.setPass(generatedUsername);
        write(toSend);
        read();
        //course register
        toSend = new Message((short) 5);
        toSend.setName(generatedUsername);
        toSend.setCourseNum((short) (new Random().nextInt(4) + 1));
        write(toSend);
        read();
        //logout
        toSend = new Message((short) 4);
        toSend.setName(generatedUsername);
        write(toSend);
        read();
    }
}