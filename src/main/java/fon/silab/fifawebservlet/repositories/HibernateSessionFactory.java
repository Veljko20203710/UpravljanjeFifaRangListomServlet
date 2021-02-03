/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.repositories;

import fon.silab.fifawebservlet.model.Confederation;
import fon.silab.fifawebservlet.model.Match;
import fon.silab.fifawebservlet.model.MatchType;
import fon.silab.fifawebservlet.model.Selection;
import fon.silab.fifawebservlet.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Veljko
 */
public class HibernateSessionFactory {

    private static final Configuration CONFIGURATION;

    static {
        CONFIGURATION = new Configuration();
        CONFIGURATION.configure("hibernate.cfg.xml").addAnnotatedClass(Selection.class).
                addAnnotatedClass(User.class).addAnnotatedClass(Confederation.class).addAnnotatedClass(Match.class).addAnnotatedClass(MatchType.class);
    }

    public static Session getSession() {
        SessionFactory sessionFactory = CONFIGURATION.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }
}
