package Application;

import DAO.News;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewsTest {
    News news = new News(1,"testowanie");

    @Test
    void getNews_id() {
        assertEquals(1,news.getNews_id());
    }

    @Test
    void getDescription() {
        assertEquals("testowanie",news.getDescription());
    }
}