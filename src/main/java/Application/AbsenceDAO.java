package Application;


import Utils.AbsencesEntity;
import Utils.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AbsenceDAO {

    public static ObservableList<Absence> searchAbsences() {
        List<AbsencesEntity> list = null;

        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            list = manager.createQuery("select c from AbsencesEntity as c", AbsencesEntity.class).getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
        ObservableList<Absence> observableList = FXCollections.observableArrayList();

        for (AbsencesEntity n : list
                ) {
            observableList.add(new Absence(n.getName(), n.getSurename(), n.getDate(), n.getId()));
        }

        return observableList;
    }

    public static ObservableList<Absence> searchAbsence(String name, String surename) {
        List<AbsencesEntity> list = null;

        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Query q = manager.createQuery("select c from AbsencesEntity as c where c.name = ?1 and c.surename = ?2", AbsencesEntity.class);
            q.setParameter(1, name);
            q.setParameter(2, surename);

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
        ObservableList<Absence> observableList = FXCollections.observableArrayList();

        for (AbsencesEntity n : list
                ) {
            observableList.add(new Absence(n.getName(), n.getSurename(), n.getDate(), n.getId()));
        }

        return observableList;
    }


    public static void updateAbsence(int id, String name, String surename, String date) {
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Note object
            AbsencesEntity absences = manager.find(AbsencesEntity.class, id);

            // Change the values
            absences.setName(name);
            absences.setSurename(surename);
            absences.setDate(date);

            // Update the student
            manager.persist(absences);

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

    public static void deleteAbsenceWithId(int id) {
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            AbsencesEntity notes = manager.find(AbsencesEntity.class, id);

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

    public static void insertAbsence(String name, String surename, String date) {
        // Create an EntityManager
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new notes object
            AbsencesEntity absences = new AbsencesEntity();
            absences.setName(name);
            absences.setSurename(surename);
            absences.setDate(date);

            // Save the note object
            manager.persist(absences);

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
