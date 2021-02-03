/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.repositories;

import fon.silab.fifawebservlet.model.Confederation;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Veljko
 */
public class ConfederationRepository {
    
    public List<Confederation> getAllConfederation() {
        Session session = HibernateSessionFactory.getSession();

        String hql = "SELECT c FROM Confederation c";
        Query query = session.createQuery(hql);
        List result = query.list();

        session.close();
        return result;
    }
    
    public Confederation getConfederationByName(String name) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "SELECT c FROM Confederation c WHERE c.name = ?0";
        Confederation confederation;
        try {
            confederation = (Confederation) session.createQuery(query).setParameter(0, name).list().get(0);
            transaction.commit();
            return confederation;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }

    }
}
