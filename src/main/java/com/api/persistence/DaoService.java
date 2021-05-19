package com.api.persistence;

import com.api.domain.UserDomain;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public enum DaoService {
    INSTANCE;
    private final SessionFactory sessionFactory;
    DaoService() {
        this.sessionFactory = HibernateSessionFactory.INSTANCE.buildSessionFactory();
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public void merge(Object object) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.merge(object);
            session.flush();
            tx.commit();

        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw e;

        } finally {
            session.close();
        }
    }

    public UserDomain getUser(Integer userId) {
    UserDomain userDomain;
    Session session = sessionFactory.openSession();
    Transaction tx = null;

    try {
        tx = session.beginTransaction();
        userDomain = session.get(UserDomain.class, userId);
        tx.commit();

    } catch (Exception e) {
        if (tx != null)
            tx.rollback();

        throw new RuntimeException(String.format("Error obtaining the User (%d) from the DB.", userId), e);

    } finally {
        session.close();
    }
    return userDomain;
    }

    public void deleteUser(Integer userId) {
        UserDomain userDomain;
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            userDomain = session.get(UserDomain.class, userId);

            if (userDomain != null) {
                session.delete(userDomain);
                session.flush();
                tx.commit();
            }

        } catch (Exception e) {
            if (tx != null)
                tx.rollback();

            throw new RuntimeException(String.format("Error deleting the User (%d) from the DB.", userId), e);

        } finally {
            session.close();
        }
    }
}