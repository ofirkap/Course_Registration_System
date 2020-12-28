package bgu.spl.net.impl.BGRS;


public class Student {
    private final String name;
    private final String psw;
    private boolean loggedIn = false;
    private final boolean[] courses = new boolean[Database.getInstance().getNumberOfCourses()];
    public Student(String name,String psw){
        this.name = name;
        this.psw = psw;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void logInOrOut() {
        loggedIn = !loggedIn;
    }

    public boolean[] getCourses() {
        return courses;
    }

    public String getPsw() {
        return psw;
    }

    public String getName() {
        return name;
    }

    public void register(int index){
        courses[index] = true;
    }
}
