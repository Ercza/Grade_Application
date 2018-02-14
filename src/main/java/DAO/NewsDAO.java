package DAO;

import Application.Main;
import Entity.NewsEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class NewsDAO {
    public static ObservableList<News> searchNews() {
        List<NewsEntity> list = null;

        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            list = manager.createQuery("select c from NewsEntity  as c", NewsEntity.class).getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
        ObservableList<News> observableList = FXCollections.observableArrayList();

        for (NewsEntity n : list
                ) {
            observableList.add(new News(n.getId(), n.getDescription()));
        }

        return observableList;
    }

    public static void updateNews(int newsId, String description) {
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Note object
            NewsEntity news = manager.find(NewsEntity.class, newsId);

            // Change the values
            news.setDescription(description);

            // Update the student
            manager.persist(news);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }

    public static void deleteNewsWithId(int newsId) {
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            NewsEntity news = manager.find(NewsEntity.class, newsId);

            // Delete note
            manager.remove(news);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }

    }

    public static void insertNews(String description) {
        // Create an EntityManager
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new notes object
            NewsEntity news = new NewsEntity();
            news.setDescription(description);

            // Save the note object
            manager.persist(news);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }

    }
}
