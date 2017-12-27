package Application;

import Utils.NotesEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class NotesDAO {


    public static ObservableList<Join> readAll() {
        List<NotesEntity> list = null;

        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            list = manager.createQuery("select c from NotesEntity as c", NotesEntity.class).getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
        ObservableList<Join> observableList = FXCollections.observableArrayList();

        for (NotesEntity n : list
                ) {
            observableList.add(new Join(n.getIdStudent(), n.getIdTeacher(), n.getIdSubject(), n.getIdMark()));
        }

        return observableList;
    }


    public static void delete(int id) {
        // Create an EntityManager
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            NotesEntity notes = manager.find(NotesEntity.class, id);

            // Delete note
            manager.remove(notes);

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


    public static void update(int id_student, int id_teacher, int id_subject, int id_mark) {
        // Create an EntityManager
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Note object
            NotesEntity notes = manager.find(NotesEntity.class, id_student);

            // Change the values
            notes.setIdStudent(id_student);
            notes.setIdTeacher(id_teacher);
            notes.setIdSubject(id_subject);
            notes.setIdMark(id_mark);


            // Update the student
            manager.persist(notes);

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


    public static void create(int id_student, int id_teacher, int id_subject, int id_mark) {
        // Create an EntityManager
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new notes object
            NotesEntity notes = new NotesEntity();
            notes.setIdStudent(id_student);
            notes.setIdTeacher(id_teacher);
            notes.setIdSubject(id_subject);
            notes.setIdMark(id_mark);

            // Save the note object
            manager.persist(notes);

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
