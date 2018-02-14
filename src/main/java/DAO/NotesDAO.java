package DAO;

import Application.Main;
import Entity.OcenyEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class NotesDAO {
    public static ObservableList<Notes> searchNotes() {
        List<OcenyEntity> list = null;

        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            list = manager.createQuery("select c from OcenyEntity as c", OcenyEntity.class).getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            manager.close();
        }
        ObservableList<Notes> observableList = FXCollections.observableArrayList();

        for (OcenyEntity n : list
                ) {
            observableList.add(new Notes(n.getId(), n.getImię(), n.getNazwisko(), n.getPrzedmiot(),n.getOcena()));
        }

        return observableList;
    }

    public static ObservableList<Notes> searchNote(String name, String surename) {
        List<OcenyEntity> list = null;

        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();

            Query q = manager.createQuery("select c from OcenyEntity as c where c.imię = ?1 and c.nazwisko = ?2", OcenyEntity.class);
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
        ObservableList<Notes> observableList = FXCollections.observableArrayList();

        for (OcenyEntity n : list
                ) {
            observableList.add(new Notes(n.getId(), n.getImię(), n.getNazwisko(), n.getPrzedmiot(),n.getOcena()));
        }

        return observableList;
    }


    public static void updateNote(int id, String name, String surename, String subject, int note) {
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Note object
            OcenyEntity notes = manager.find(OcenyEntity.class, id);

            // Change the values
            notes.setImię(name);
            notes.setNazwisko(surename);
            notes.setPrzedmiot(subject);
            notes.setOcena(note);

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

    public static void deleteNoteWithId(int id) {
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            OcenyEntity notes = manager.find(OcenyEntity.class, id);

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

    public static void insertNote(String name, String surename, String subject, int note) {
        // Create an EntityManager
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new notes object
            OcenyEntity notes = new OcenyEntity();
            notes.setImię(name);
            notes.setNazwisko(surename);
            notes.setPrzedmiot(subject);
            notes.setOcena(note);

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
