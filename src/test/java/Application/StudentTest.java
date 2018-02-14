package Application;

import DAO.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    Student student =new Student(1,"Artur","Vasiliev",1,1);

    @Test
    void getStudent_id() {
        assertEquals(1,student.getStudent_id());
    }

    @Test
    void getName() {
        assertEquals("Artur",student.getName());
    }

    @Test
    void getSurename() {
        assertEquals("Vasiliev",student.getSurename());
    }

    @Test
    void getLogin_id() {
        assertEquals(1,student.getLogin_id());
    }

    @Test
    void getSubject_id() {
        assertEquals(1,student.getSubject_id());
    }
}