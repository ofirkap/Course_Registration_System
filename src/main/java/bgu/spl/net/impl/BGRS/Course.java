package bgu.spl.net.impl.BGRS;

import java.util.concurrent.atomic.AtomicInteger;

public class Course {

    private final int num;
    private final String name;
    private final int maxSeats;
    private AtomicInteger seatsAvailable = new AtomicInteger(0);
    private final boolean[] kdamCourses;
    private String[] registeredStudents;

    public Course(int num, String name, int maxSeats, boolean[] kdamCourses) {
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

    public AtomicInteger getSeatsAvailable() {
        return seatsAvailable;
    }

    public boolean[] getKdamCourses() {
        return kdamCourses;
    }

    public String[] getRegisteredStudents() {
        return registeredStudents;
    }
}
