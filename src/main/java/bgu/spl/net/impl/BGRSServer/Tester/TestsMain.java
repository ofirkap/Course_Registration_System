package bgu.spl.net.impl.BGRSServer.Tester;


import java.util.LinkedList;
import java.util.List;

public class TestsMain {
    public static void main(String[] args) {
        int numberOfThreads = 4;
        List<Thread> threads = new LinkedList<>();
        for (int i = 0;i<numberOfThreads;i++){
            TesterClient client = new TesterClient("localhost",7777);
            Thread th = new Thread(client);
            threads.add(th);
            th.start();
        }
        try {
            for (Thread th : threads)
                th.join();
        }catch (InterruptedException ignored){}
    }
}
