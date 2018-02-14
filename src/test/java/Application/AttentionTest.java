package Application;

import DAO.Attention;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttentionTest {

    Attention attention = new Attention(1,"testowanie","16:00 2017-12-11");

    @Test
    void getStudent_id() {
        assertEquals(1,attention.getStudent_id());
    }

    @Test
    void getAttention() {
        assertEquals("testowanie",attention.getAttention());
    }

    @Test
    void getDate() {
        assertEquals("16:00 2017-12-11",attention.getDate());
    }
}