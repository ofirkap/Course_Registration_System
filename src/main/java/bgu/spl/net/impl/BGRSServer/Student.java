package bgu.spl.net.impl.BGRSServer;


import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class Student {
    private final String name;
    private final String psw;
    private boolean loggedIn = false;
    private final SortedSet<Course> courses;

    public Student(String name, String psw) {
        this.name = name;
        this.psw = psw;
        this.courses = new ConcurrentSkipListSet<>();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void logInOrOut() {
        loggedIn = !loggedIn;
    }

    public List<Integer> getCourses() {
        List<Integer> ans = new ArrayList<>();
        for (Course course : courses)
            ans.add(course.getNum());
        return ans;
    }

    public String getPsw() {
        return psw;
    }

    public String getName() {
        return name;
    }

    public void register(Course course) {
        courses.add(course);
    }

    public void unregister(Course course) {
        courses.remove(course);
    }

    public boolean isRegistered(Course course) {
        return courses.contains(course);
    }
}
