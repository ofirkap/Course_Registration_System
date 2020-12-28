package bgu.spl.net.impl.BGRS;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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

    //Hashmap that holds all the registered Admin users
    private final ConcurrentHashMap<String, Admin> adminUsers;
    //Hashmap that holds all the registered Student users
    private final ConcurrentHashMap<String, Student> studentUsers;
    //Array that holds all the courses, sorted by order of appearance in the text file
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
        try (BufferedReader br = new BufferedReader(new FileReader(coursesFilePath))) {
            List<String> temp = new LinkedList<>();
            String line = br.readLine();

            while (line != null) {
                temp.add(line);
                line = br.readLine();
            }
            this.courses = new Course[temp.size()];
            //The courses have been added to the string in a backwards order so we iterate from the end
            int i = temp.size() - 1;
            for (String course : temp) {
                //split each string to its 4 components as written in the file
                String[] parts = course.split("\\|");
                //split the string representing the kdams while ignoring the brackets ('[',']')
                String[] stringKdams = parts[2].substring(1, parts[2].length() - 1).split(",");
                int[] intKdams = new int[stringKdams.length];
                for (int j = 0; j < stringKdams.length; j++)
                    intKdams[j] = Integer.parseInt(stringKdams[j]);
                courses[i] = new Course(Integer.parseInt(parts[0]), parts[1], intKdams, Integer.parseInt(parts[3]));
                i--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
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

    /**
     * @param name the name of the user preforming the action
     * @param pass the password to register with
     * @return true if registration successful, false otherwise
     */
    public boolean registerAdmin(String name, String pass) {
        return (adminUsers.putIfAbsent(name, new Admin(name, pass)) == null);
    }

    /**
     * @param name the name of the user preforming the action
     * @param pass the password to register with
     * @return true if registration successful, false otherwise
     */
    public boolean registerStudent(String name, String pass) {
        return (studentUsers.putIfAbsent(name, new Student(name, pass)) == null);
    }

    /**
     * login if provided a correct password, registered and not logged in already
     *
     * @param name the name of the user preforming the action
     * @param pass the password to login with
     * @return true if login successful, false otherwise
     */
    public boolean login(String name, String pass) {
        Admin tempA = adminUsers.get(name);
        //if no admin registered under that name, try a student
        if (tempA == null) {
            Student tempS = studentUsers.get(name);
            if (tempS == null || !pass.equals(tempS.getPsw()) || tempS.isLoggedIn())
                return false;
            tempS.logInOrOut();
        }else {
            if (!pass.equals(tempA.getPsw()) || tempA.isLoggedIn())
                return false;
            tempA.logInOrOut();
        }
        return true;
    }

    /**
     * logout if registered and logged in
     *
     * @param name the name of the user preforming the action
     * @return true if logout successful, false otherwise
     */
    public boolean logoutAdmin(String name) {
        Admin temp = adminUsers.get(name);
        if (temp == null || !temp.isLoggedIn())
            return false;
        temp.logInOrOut();
        return true;
    }

    /**
     * logout if registered and logged in
     *
     * @param name the name of the user preforming the action
     * @return true if logout successful, false otherwise
     */
    public boolean logoutStudent(String name) {
        Student temp = studentUsers.get(name);
        if (temp == null || !temp.isLoggedIn())
            return false;
        temp.logInOrOut();
        return true;
    }

    /**
     * register if all kdam requirements are fulfilled
     *
     * @param name the name of the user preforming the action
     * @param num  the number of course to register
     * @return true if registered successfully, false otherwise
     */
    public boolean registerCourse(String name, int num) {
        Student student = studentUsers.get(name);
        if (student == null || !student.isLoggedIn())
            return false;
        Course course = findCourse(num);
        //if the student is already registered return false
        if (course.isRegistered(name) != -1)
            return false;
        int[] kdams = course.getKdamCourses();
        boolean[] registered = student.getCourses();
        //the index of 'course' in the courses array
        int locationOfCourse = 0;
        for (int i = 0; i < registered.length; i++) {
            //if we found the location of 'course' in the courses array, mark it
            if (courses[i] == course)
                locationOfCourse = i;
        }
        boolean ans = false;
        //loop on all the required kdams for 'course'
        for (int courseNum : kdams) {
            ans = false;
            //for each of them, check if 'student' is registered to it
            for (int i = 0; i < registered.length; i++) {
                if (registered[i] && courseNum == courses[i].getNum()) {
                    ans = true;
                    break;
                }
            }
            //if 'student' isn't registered to a kdam course, return false because he can't register to 'course'
            if (!ans)
                return false;
        }
        //if we got here 'student' fulfills all the kdam requirements
        //add 'student' to the students registered to 'course'
        //this function can fail if there are no seats available
        if (!courses[locationOfCourse].addStudent(name))
            return false;
        //synchronized prevents the student 'name' from registering to courses while printing his stats
        synchronized (student) {
            //add the course to the student's registered courses
            registered[locationOfCourse] = true;
        }
        return true;
    }

    /**
     * @param num the number of course to check
     * @return the array of kdams required for course 'num'
     */
    //you must be logged in to check this?
    public String kdamCheck(int num) {
        return "\n" + findCourse(num).getKdamCourses().toString();
    }

    /**
     * @param num the number of course to check
     * @print the information of the course 'num'
     */
    public String courseStat(int num) {
        Course curr = findCourse(num);
        return ("\n" + "Course: " + "(" + num + ")" + curr.getName() +
                "\n" + "Seats Available: " + (curr.getMaxSeats() - curr.getSeatsTaken().intValue()) + "/" + curr.getMaxSeats() +
                "\n" + "Students Registered: " + curr.getRegisteredStudents());
    }

    /**
     * @param name the name of the student to check
     * @print the information of the student 'name'
     */
    public String studentStat(String name) {
        Student student = studentUsers.get(name);
        List<Integer> ans = new LinkedList<>();
        //synchronized prevents the student 'name' from registering to courses while printing his stats
        synchronized (student) {
            for (int i = courses.length - 1; i >= 0; i--) {
                if (student.getCourses()[i])
                    ans.add(courses[i].getNum());
            }
        }
        return ("\n" + "Student: " + student.getName() +
                "\n" + "Courses: " + ans);
    }

    /**
     * @param name the name of the user preforming the action
     * @param num  the number of course to check
     * @return "REGISTERED" if the student 'name' is registered to course 'num' or "UNREGISTERED" otherwise
     */
    public String isRegistered(String name, int num) {
        if (findCourse(num).isRegistered(name) != -1)
            return "\n" + "REGISTERED";
        return "\n" + "UNREGISTERED";
    }

    /**
     * unregister only if student 'name' is registered to course 'num'
     *
     * @param name the name of the user preforming the action
     * @param num  the number of course to unregister
     * @return true if unregistered successfully, false otherwise
     */
    public boolean unregister(String name, int num) {
        Student student = studentUsers.get(name);
        Course course = findCourse(num);
        int index = course.isRegistered(student.getName());
        if (index == -1)
            return false;
        course.removeStudent(index);
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == course) {
                index = i;
                break;
            }
        }
        student.getCourses()[index] = false;
        return true;
    }

    /**
     * @param name the name of the user preforming the action
     * @return a list representing the courses that student 'name' is registered to
     */
    public String myCourses(String name) {
        List<Integer> ans = new LinkedList<>();
        boolean[] toAns = studentUsers.get(name).getCourses();
        for (int i = courses.length - 1; i >= 0; i--)
            if (toAns[i])
                ans.add(courses[i].getNum());
        return "\n" + ans.toString();
    }
}