package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.srv.Server;

public class ReactorMain {
    public static void main(String[] args) {

        Database database = Database.getInstance();
        database.initialize("/home/spl211/IdeaProjects/spl-net/Courses.txt");
        Server.reactor(
                Runtime.getRuntime().availableProcessors(),
                7777, //port
                BGRSProtocol::new, //protocol factory
                BGRSMessageEncoderDecoder::new //message encoder decoder factory
        ).serve();

    }
}