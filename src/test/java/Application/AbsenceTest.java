package Application;

import DAO.Absence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbsenceTest {
    Absence absence = new Absence("Artur","Vasiliev","16:00 2017-12-11",1);

    @Test
    void getName() {
        assertEquals("Artur",absence.getName());
    }

    @Test
    void getSurename() {
        assertEquals("Vasiliev",absence.getSurename());
    }

    @Test
    void getDate() {
        assertEquals("16:00 2017-12-11",absence.getDate());
    }

    @Test
    void getId() {
        assertEquals(1,absence.getId());
    }
}