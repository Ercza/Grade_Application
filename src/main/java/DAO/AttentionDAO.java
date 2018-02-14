package DAO;

import Application.Main;
import Entity.AttentionsEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class AttentionDAO {

    public static ObservableList<Attention> searchAttentions(){
        List<AttentionsEntity> list = null;

        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;
        try{
            transaction = manager.getTransaction();
            transaction.begin();

            list = manager.createQuery("select c from AttentionsEntity as c",AttentionsEntity.class).getResultList();
            transaction.commit();
        }catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            manager.close();
        }
        ObservableList<Attention> observableList = FXCollections.observableArrayList();

        for (AttentionsEntity n :list
             ) {
            observableList.add(new Attention(n.getStudentId(),n.getAttention(),n.getDate()));
        }
        return observableList;
    }

    public static void updateAttention(int id, String attention, String date){
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Note object
            AttentionsEntity attentions = manager.find(AttentionsEntity.class, id);

            // Change the values
            attentions.setAttention(attention);
            attentions.setDate(date);

            // Update the student
            manager.persist(attentions);

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

    public static void deleteAttentionWithId(int attentionId){
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            AttentionsEntity attentions = manager.find(AttentionsEntity.class, attentionId);

            // Delete note
            manager.remove(attentions);

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

    public static void insertAttention(String attention, String date){
        // Create an EntityManager
        EntityManager manager = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new notes object
            AttentionsEntity attentions = new AttentionsEntity();
            attentions.setAttention(attention);
            attentions.setDate(date);

            // Save the note object
            manager.persist(attentions);

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
