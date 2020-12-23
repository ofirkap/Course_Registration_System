package bgu.spl.net;

import bgu.spl.net.impl.BGRS.Admin;
import bgu.spl.net.impl.BGRS.Course;
import bgu.spl.net.impl.BGRS.Student;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Arrays;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {

    private static class SingletonHolder {
        private static final Database instance = new Database();
    }

    private ConcurrentHashMap<String, Admin> adminUsers;
    private ConcurrentHashMap<String, Student> studentUsers;
    private Course[] courses;

    //to prevent user from creating new Database
    private Database() {
        adminUsers = new ConcurrentHashMap<>();
        studentUsers = new ConcurrentHashMap<>();
    }

    /**
     * Retrieves the single instance of this class.
     */
    public static Database getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * loads the courses from the file path specified
     * into the Database, returns true if successful.
     */
    boolean initialize(String coursesFilePath) {
        // TODO: implement
        return false;
    }

    public int getNumberOfCourses() {
        return courses.length;
    }

    private Course findCourse(int num) {
        for (Course ans : courses)
            if (ans.getNum() == num)
                return ans;
        return null;
    }

    public boolean registerAdmin(String name, String pass) {
        return (adminUsers.putIfAbsent(name, new Admin(name, pass)) == null);
    }

    public boolean registerStudent(String name, String pass) {
        return (studentUsers.putIfAbsent(name, new Student(name, pass)) == null);
    }

    public boolean loginAdmin(String name, String pass) {
        Admin temp = adminUsers.get(name);
        if (temp == null || !pass.equals(temp.getPsw()) || temp.isLoggedIn())
            return false;
        temp.logInOrOut();
        return true;
    }

    public boolean loginStudent(String name, String pass) {
        Student temp = studentUsers.get(name);
        if (temp == null || !pass.equals(temp.getPsw()) || temp.isLoggedIn())
            return false;
        temp.logInOrOut();
        return true;
    }

    public boolean logoutAdmin(String name) {
        Admin temp = adminUsers.get(name);
        if (temp == null || !temp.isLoggedIn())
            return false;
        temp.logInOrOut();
        return true;
    }

    public boolean logoutStudent(String name) {
        Student temp = studentUsers.get(name);
        if (temp == null || !temp.isLoggedIn())
            return false;
        temp.logInOrOut();
        return true;
    }

    public boolean registerCourse(String name, int num) {
        Student temp = studentUsers.get(name);
        if (temp == null || !temp.isLoggedIn())
            return false;
        Course course = findCourse(num);
        boolean[] kdams = course.getKdamCourses();
        boolean[] registred = temp.getCourses();
        int locationOfCourse = 0;
        for (int i = 0; i < kdams.length; i++) {
            if (courses[i] == course)
                locationOfCourse = i;
            if (kdams[i] && !registred[i])
                return false;
        }
        registred[locationOfCourse] = true;
        return true;
    }

    public List<Integer> kdamCheck(int num) {
        List<Integer> ans = new LinkedList<>();
        boolean[] kdams = findCourse(num).getKdamCourses();
        for (int i = kdams.length - 1; i >= 0; i--)
            if (kdams[i])
                ans.add(courses[i].getNum());
        return ans;
    }
}
