package DAO;

import Application.Main;
import Entity.LoginEntity;
import Utils.DialogUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class LoginDAO {

    public static int loginID;

    public boolean isLogin(String user, String pass, String option) {

        EntityManager em = Main.emf.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();

            Query q = em.createQuery("select u from LoginEntity u where u.username = ?1 and u.password = ?2 and u.partof = ?3", LoginEntity.class);
            q.setParameter(1, user);
            q.setParameter(2, pass);
            q.setParameter(3, option);

            transaction.commit();

            LoginEntity le = (LoginEntity) q.getSingleResult();
            loginID = le.getId();

            if (user.equalsIgnoreCase(le.getUsername()) && pass.equals(le.getPassword()) && option.equals(le.getPartof())) {
                return true;
            }else{
                return false;
            }

        } catch (Exception e) {
            DialogUtils.informationDialog("Podane dane logowania są błędne");

            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

            return false;
        } finally {
            em.close();
        }

    }
}
