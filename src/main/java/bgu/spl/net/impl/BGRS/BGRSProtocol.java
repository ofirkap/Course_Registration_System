package bgu.spl.net.impl.BGRS;

import bgu.spl.net.api.MessagingProtocol;

public class BGRSProtocol implements MessagingProtocol<Message> {
    private boolean shouldTerminate = false;

    @Override
    public Message process(Message message) {
        Database dataBase = Database.getInstance();
        shouldTerminate = message.getOPCode() == 4;
        boolean succeeded = false;
        String info = "";
        switch (message.getOPCode()) {
            case 1:
                if (message.getName() != null && message.getPass() != null)
                    succeeded = dataBase.registerAdmin(message.getName(), message.getPass());
                break;
            case 2:
                if (message.getName() != null && message.getPass() != null)
                    succeeded = dataBase.registerStudent(message.getName(), message.getPass());
                break;
            case 3:
                if (message.getName() != null && message.getPass() != null)
                    succeeded = dataBase.login(message.getName(), message.getPass());
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
        }
        return createMessage(succeeded, message.getOPCode(), info);
    }

    private Message createMessage(boolean succeeded, short returnOPCode, String returnInfo) {
        Message ans = new Message(succeeded ? (short) 12 : (short) 13);
        ans.setReturnOPCode(returnOPCode);
        ans.setReturnInfo(returnInfo);
        return ans;
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
