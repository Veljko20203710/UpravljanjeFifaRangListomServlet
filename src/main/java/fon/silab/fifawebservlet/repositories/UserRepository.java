package fon.silab.fifawebservlet.repositories;

import fon.silab.fifawebservlet.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Veljko
 */
public class UserRepository {

    public void saveUser(User user) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "SELECT u FROM User_table u WHERE u.username = ?0 and u.password = ?1";
        User user;
        try {
            user = (User) session.createQuery(query).setParameter(0, username).setParameter(1, password).list().get(0);
            transaction.commit();
            return user;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }

    }

    public User getUserById(int id) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.find(User.class, id);
        transaction.commit();
        return user;
    }

    public void deleteUser(User user) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
    }

    public void updateUser(User user) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
    }

    public List<User> getAllUsers() {
        List result;
        try (Session session = HibernateSessionFactory.getSession()) {
            String hql = "SELECT u FROM User_table u";
            Query query = session.createQuery(hql);
            result = query.list();
        }
        return result;
    }

}
