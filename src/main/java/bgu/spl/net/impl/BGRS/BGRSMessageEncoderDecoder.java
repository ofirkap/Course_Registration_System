package bgu.spl.net.impl.BGRS;

import bgu.spl.net.api.MessageEncoderDecoder;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BGRSMessageEncoderDecoder implements MessageEncoderDecoder<Message> {

    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    private Message decoded = null;

    @Override
    public Message decodeNextByte(byte nextByte) {
        if (decoded == null) {
            if (len == 2)
                decoded = new Message(bytesToShort());
            pushByte(nextByte);
            return null; //not a line yet
        } else {
            switch (decoded.getOPCode()) {
                case 1:
                case 2:
                case 3:
                    return decodeType1(nextByte);
                case 5:
                case 6:
                case 7:
                case 9:
                case 10:
                    return decodeType2(nextByte);
                case 8:
                    return decodeType3(nextByte);
                default:
                    clearBytes();
                    return decoded;
            }
        }
    }

    @Override
    public byte[] encode(Message message) {
        shortToBytes(message.getOPCode());
        shortToBytes(message.getReturnOPCode());
        byte[] temp = message.getReturnInfo().getBytes(StandardCharsets.UTF_8);
        for (byte e : temp)
            pushByte(e);
        return Arrays.copyOf(bytes, len + 1);
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }
        bytes[len++] = nextByte;
    }

    private String popString() {
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        clearBytes();
        return result;
    }

    private Message decodeType1(byte nextByte) {
        if (nextByte == '\0') {
            if (decoded.getName() != null) {
                decoded.setPass(popString());
                return decoded;
            } else {
                decoded.setName(popString());
                return null;
            }
        } else {
            pushByte(nextByte);
            return null; //not a line yet
        }
    }

    private Message decodeType2(byte nextByte) {
        if (len == 1) {
            pushByte(nextByte);
            decoded.setCourseNum(bytesToShort());
            return decoded;
        } else {
            pushByte(nextByte);
            return null; //not a line yet
        }
    }

    private Message decodeType3(byte nextByte) {
        if (nextByte == '\0') {
            decoded.setName(popString());
            return decoded;
        } else {
            pushByte(nextByte);
            return null; //not a line yet
        }
    }

    private short bytesToShort() {
        short result = (short) ((bytes[0] & 0xff) << 8);
        result += (short) (bytes[1] & 0xff);
        clearBytes();
        return result;
    }

    private void shortToBytes(short num) {
        pushByte((byte) ((num >> 8) & 0xFF));
        pushByte((byte) (num & 0xFF));
    }

    private void clearBytes() {
        Arrays.fill(bytes, (byte) 0);
        len = 0;
    }
}
