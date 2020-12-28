package bgu.spl.net.impl.BGRS;

import bgu.spl.net.api.MessageEncoderDecoder;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BGRSMessageEncoderDecoder implements MessageEncoderDecoder<Message> {

    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    private Message decoded = null;

    @Override
    public Message decodeNextByte(byte nextByte) {
        if (decoded == null) {
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
                    return decoded;
            }
        }
    }

    @Override
    public byte[] encode(Message message) {
        return (message.toString() + "\n").getBytes(); //uses utf8 by default
    }

    private void pushByte(byte nextByte) {
        if (decoded == null && len == 2) {
            decoded = new Message(ByteBuffer.wrap(Arrays.copyOf(bytes, 2)).getShort());
            Arrays.fill(bytes, (byte) 0);
        }
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }
        bytes[len++] = nextByte;
    }

    private String popString() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        len = 0;
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
        if (len == 2) {
            decoded.setCourseNum(ByteBuffer.wrap(Arrays.copyOf(bytes, 2)).getInt());
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


}
