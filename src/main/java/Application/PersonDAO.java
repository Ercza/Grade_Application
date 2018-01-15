package Application;


import Utils.DialogUtils;
import Utils.LoginEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;

public class PersonDAO {


    public static ObservableList<Person> searchPerson(int id) {
        List<LoginEntity> list = null;

        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Query q = manager.createQuery("select c from LoginEntity  as c where c.id = ?1", LoginEntity.class);
            q.setParameter(1, id);

            list = q.getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
        ObservableList<Person> observableList = FXCollections.observableArrayList();

        for (LoginEntity n : list
                ) {
            observableList.add(new Person(n.getId(), n.getUsername(), n.getPassword(), n.getPartof()));
        }

        return observableList;
    }


    public static void updatePersonDetails(int personId, String username, String password, String partof) {
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Note object
            LoginEntity login = manager.find(LoginEntity.class, personId);

            // Change the values
            login.setUsername(username);
            login.setPassword(password);
            login.setPartof(partof);

            // Update the student
            manager.persist(login);

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


    public static void deletePersonWithId(int personId) {
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            LoginEntity login = manager.find(LoginEntity.class, personId);

            // Delete note
            manager.remove(login);

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


    public static void insertPerson(String username, String password, String partof) {
        // Create an EntityManager
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new notes object
            LoginEntity login = new LoginEntity();
            login.setUsername(username);
            login.setPassword(password);
            login.setPartof(partof);

            // Save the note object
            manager.persist(login);

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


    public static ObservableList<Person> searchPersons() {
        List<LoginEntity> list = null;

        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            list = manager.createQuery("select c from LoginEntity  as c", LoginEntity.class).getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            DialogUtils.errorDialog(e.getMessage());
        } finally {
            manager.close();
        }
        ObservableList<Person> observableList = FXCollections.observableArrayList();

        for (LoginEntity n : list
                ) {
            observableList.add(new Person(n.getId(), n.getUsername(), n.getPassword(), n.getPartof()));
        }

        return observableList;
    }

}
