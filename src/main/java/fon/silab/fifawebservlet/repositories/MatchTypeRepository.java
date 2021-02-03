/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.repositories;

import fon.silab.fifawebservlet.model.MatchType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Veljko
 */
public class MatchTypeRepository {
    
    public List<MatchType> getAllMatchTypes() {
        Session session = HibernateSessionFactory.getSession();

        String hql = "SELECT m FROM MatchType m";
        Query query = session.createQuery(hql);
        List result = query.list();

        session.close();
        return result;
    }
    
    public MatchType getMatchTypeByName(String name) {
        Session session = HibernateSessionFactory.getSession();
        Transaction transaction = session.beginTransaction();
        String query = "SELECT m FROM MatchType m WHERE m.name = ?0";
        MatchType matchType;
        try {
            matchType = (MatchType) session.createQuery(query).setParameter(0, name).list().get(0);
            transaction.commit();
            return matchType;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }

    }
}
