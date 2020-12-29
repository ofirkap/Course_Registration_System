package bgu.spl.net.impl.BGRS;

import bgu.spl.net.api.MessagingProtocol;

public class BGRSProtocol implements MessagingProtocol<Message> {
    private boolean shouldTerminate = false;
    private String info = "";

    @Override
    public Message process(Message message) {
        shouldTerminate = message.getOPCode() == 4;
        boolean succeeded = messageSorter(message);
        return createMessage(succeeded, message.getOPCode(), info);
    }

    /**
     * used to sort messages by their OPCode and request the corresponding action from the server
     * @return true if action was successful, false otherwise
     */
    private boolean messageSorter(Message message){
        Database dataBase = Database.getInstance();
        switch (message.getOPCode()) {
            case 1:
                if (message.getName() != null && message.getPass() != null)
                    return dataBase.registerAdmin(message.getName(), message.getPass());
            case 2:
                if (message.getName() != null && message.getPass() != null)
                    return dataBase.registerStudent(message.getName(), message.getPass());
            case 3:
                if (message.getName() != null && message.getPass() != null)
                    return dataBase.login(message.getName(), message.getPass());
            case 4:
                if (!dataBase.logout(""))
                    shouldTerminate = false;
                return shouldTerminate;
            case 5:
                return dataBase.registerCourse("",message.getCourseNum());
            case 6:
                info = dataBase.kdamCheck(message.getCourseNum());
                return  (info != null);
            case 7:
                info = dataBase.courseStat(message.getCourseNum());
                return  (info != null);
            case 8:
                info = dataBase.studentStat(message.getName());
                return  (info != null);
            case 9:
                info = dataBase.isRegistered("",message.getCourseNum());
                return  (info != null);
            case 10:
                return dataBase.unregister("",message.getCourseNum());
            case 11:
                info = dataBase.myCourses("");
                return  (info != null);
            default:
                return false;
        }
    }

    /**
     * @param succeeded used to determine whether we're sending an ACK or ERR message
     * @param returnOPCode the number of action we are returning an answer for
     * @param returnInfo the information we're returning (if existent)
     * @return a new {@link Message} with the aforementioned parameters
     */
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