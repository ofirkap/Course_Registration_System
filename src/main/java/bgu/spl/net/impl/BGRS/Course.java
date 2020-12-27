package bgu.spl.net.impl.BGRS;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Course {

    private final int num;
    private final String name;
    private final int maxSeats;
    private AtomicInteger seatsTaken = new AtomicInteger(0);
    private final int[] kdamCourses;
    private String[] registeredStudents;

    public Course(int num, String name, int[] kdamCourses, int maxSeats) {
        this.num = num;
        this.name = name;
        this.maxSeats = maxSeats;
        this.kdamCourses = kdamCourses;
        this.registeredStudents = new String[maxSeats];
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public synchronized AtomicInteger getSeatsTaken() {
        return seatsTaken;
    }

    public int[] getKdamCourses() {
        return kdamCourses;
    }

    public synchronized String[] getRegisteredStudents() {
        return registeredStudents;
    }

    public synchronized boolean addStudent(String name) {
        if(seatsTaken.intValue() == maxSeats)
            return false;
        for (int i = 0; i < seatsTaken.intValue(); i++) {
            if (name.compareTo(registeredStudents[i]) > 0) {
                for (int j = seatsTaken.intValue(); j > i; j--) {
                    registeredStudents[j] = registeredStudents[j - 1];
                }
                registeredStudents[i] = name;
                break;
            }
        }
        seatsTaken.incrementAndGet();
        return true;
    }

    public synchronized void removeStudent(int index){
        for (int j = index;j<seatsTaken.intValue() -1; j++) {
            registeredStudents[j] = registeredStudents[j+1];
        }
        registeredStudents[seatsTaken.intValue()-1] = null;
        seatsTaken.decrementAndGet();
    }
    public synchronized int isRegistered(String name){
        return Arrays.binarySearch(registeredStudents,name);
    }
}
