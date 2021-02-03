/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.repositories;

import fon.silab.fifawebservlet.model.Match;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Veljko
 */
public class MatchRepository {

    public void saveMatch(Match match) {
        try (Session session = HibernateSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(match);
            transaction.commit();
        }

    }

    public void deleteMatch(Match match) {
        try (Session session = HibernateSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(match);
            transaction.commit();
        }
    }

    public Match getMatchById(int id) {
        Match match;
        try (Session session = HibernateSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            match = session.get(Match.class, id);
            transaction.commit();
        }
        return match;
    }

    public void updateMatch(Match match) {
        try (Session session = HibernateSessionFactory.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(match);
            transaction.commit();
        }
    }

    public List<Match> getAllMatches() {
        Session session = HibernateSessionFactory.getSession();

        String hql = "SELECT m FROM Match_table m";
        Query query = session.createQuery(hql);
        List result = query.list();

        session.close();
        return result;
    }
} 
