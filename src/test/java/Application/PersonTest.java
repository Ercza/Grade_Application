package Application;

import DAO.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person person = new Person(1,"test","test1","Admin");

    @Test
    void getPartof() {
        assertEquals("Admin",person.getPartof());

    }

    @Test
    void getPerson_id() {
        assertEquals(1,person.getPerson_id());
    }

    @Test
    void getUsername() {
        assertEquals("test",person.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("test1",person.getPassword());
    }
}