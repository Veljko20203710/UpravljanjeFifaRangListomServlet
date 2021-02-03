/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.repositories;

import fon.silab.fifawebservlet.model.Selection;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Veljko
 */
public class SelectionRepository {

    public void save(Selection selection) {
        try (Session session = HibernateSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(selection);
            transaction.commit();
        }
    }

    public void deleteSelection(Selection selection) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(selection);
        transaction.commit();
    }

    public Selection getSelectionById(int id) {
        Selection selection;
        try (Session session = HibernateSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            selection = session.get(Selection.class, id);
            transaction.commit();
        }
        return selection;
    }

    public void updateSelection(Selection selection) {
        try (Session session = HibernateSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(selection);
            transaction.commit();
        }
    }

    public List<Selection> getAllSelections() {
        List result;
        try (Session session = HibernateSessionFactory.getSession()) {
            String hql = "SELECT s FROM Selection s";
            Query query = session.createQuery(hql);
            result = query.list();
        }
        return result;
    }

}
