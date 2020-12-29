package bgu.spl.net.impl.BGRS;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    Database database = Database.getInstance();

    @BeforeEach
    void setUp() {
        database.clear();
        initialize();
    }

    @AfterEach
    void tearDown() {
        database.clear();
    }

    @Test
    void initialize() {
        assertFalse(database.initialize(""));
        database.initialize("/home/spl211/IdeaProjects/spl-net/Courses.txt");
        assertEquals(database.courseStat(2),"\n" +
                "Course: (2) two" + "\n" +
                "Seats Available: 3/3" + "\n" +
                "Students Registered: []");
    }

    @Test
    void registerAdmin() {
        assertTrue(database.registerAdmin("admin1", "123"));
        assertFalse(database.registerAdmin("admin1", "123"));

    }

    @Test
    void registerStudent() {
        assertTrue(database.registerStudent("student1", "123"));
        assertFalse(database.registerStudent("student1", "123"));
    }

    @Test
    void login() {
        assertFalse(database.login("student1", "123"));
        database.registerStudent("student1", "123");
        assertFalse(database.login("student1", "12"));
        assertFalse(database.login("admin1", "123"));
        database.registerAdmin("admin1", "123");
        assertTrue(database.login("admin1", "123"));
    }

    @Test
    void logout() {
        assertFalse(database.logout("student1"));
        database.registerStudent("student1", "123");
        assertFalse(database.logout("student1"));
        database.login("student1", "123");
        assertTrue(database.logout("student1"));
    }

    @Test
    void registerCourse() {
        assertFalse(database.registerCourse("student1", 1));
        database.registerStudent("student1", "123");
        database.login("student1", "123");
        assertFalse(database.registerCourse("student1", 2));
        assertTrue(database.registerCourse("student1", 1));
        assertTrue(database.registerCourse("student1", 2));
    }

    @Test
    void kdamCheck() {
        System.out.println(database.kdamCheck(1));
    }

    @Test
    void courseStat() {
        System.out.println(database.courseStat(1));
        database.registerStudent("student1", "123");
        database.login("student1", "123");
        database.registerCourse("student1", 1);
        System.out.println(database.courseStat(1));
    }

    @Test
    void studentStat() {
        assertNull(database.studentStat("student1"));
        database.registerStudent("student1", "123");
        database.login("student1", "123");
        database.registerCourse("student1", 1);
        System.out.println(database.studentStat("student1"));
    }

    @Test
    void isRegistered() {
        assertEquals(database.isRegistered("student1",1),"\n" + "NOT REGISTERED");
        database.registerStudent("student1", "123");
        database.login("student1", "123");
        database.registerCourse("student1", 1);
        assertEquals(database.isRegistered("student1",1),"\n" + "REGISTERED");
    }

    @Test
    void unregister() {
        assertFalse(database.unregister("student1",1));
        database.registerStudent("student1", "123");
        database.login("student1", "123");
        assertFalse(database.unregister("student1",1));
        database.registerCourse("student1", 1);
        assertTrue(database.unregister("student1",1));
    }

    @Test
    void myCourses() {
        assertNull(database.myCourses("student1"));
        database.registerStudent("student1", "123");
        database.login("student1", "123");
        database.registerCourse("student1", 1);
        System.out.println(database.myCourses("student1"));
    }
}