package bgu.spl.net.impl.BGRS;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Course {

    private final int num;
    private final String name;
    private final int maxSeats;
    private final AtomicInteger seatsTaken = new AtomicInteger(0);
    private final int[] kdamCourses;
    private final SortedSet<String> registeredStudents;

    public Course(int num, String name, int[] kdamCourses, int maxSeats) {
        this.num = num;
        this.name = name;
        this.maxSeats = maxSeats;
        this.kdamCourses = kdamCourses;
        this.registeredStudents = new TreeSet<>();
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

    public synchronized SortedSet<String> getRegisteredStudents() {
        return registeredStudents;
    }

    public synchronized boolean addStudent(String name) {
        if (seatsTaken.intValue() == maxSeats)
            return false;
        if (!registeredStudents.add(name))
            return false;
        seatsTaken.incrementAndGet();
        return true;
    }

    public synchronized boolean removeStudent(String name) {
        if (!registeredStudents.remove(name))
            return false;
        seatsTaken.decrementAndGet();
        return true;
    }

    public synchronized boolean isRegistered(String name) {
        return registeredStudents.contains(name);
    }
}
