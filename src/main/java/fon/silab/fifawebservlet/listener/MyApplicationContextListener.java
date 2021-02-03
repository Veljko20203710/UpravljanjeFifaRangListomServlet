/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.listener;
import fon.silab.fifawebservlet.action.services.SortSelectionsByName;
import fon.silab.fifawebservlet.config.MyAppConfig;
import fon.silab.fifawebservlet.model.Confederation;
import fon.silab.fifawebservlet.model.Match;
import fon.silab.fifawebservlet.model.MatchType;
import fon.silab.fifawebservlet.model.Selection;
import fon.silab.fifawebservlet.model.User;
import fon.silab.fifawebservlet.repositories.ConfederationRepository;
import fon.silab.fifawebservlet.repositories.MatchRepository;
import fon.silab.fifawebservlet.repositories.MatchTypeRepository;
import fon.silab.fifawebservlet.repositories.SelectionRepository;
import fon.silab.fifawebservlet.repositories.UserRepository;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Web application lifecycle listener.
 *
 * @author Veljko
 */
@WebListener
public class MyApplicationContextListener implements ServletContextListener {


    public MyApplicationContextListener() {
        System.out.println("==================================");
        System.out.println("===========Constructor context listeer==========");
        System.out.println("==================================");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("==================================");
        System.out.println("===========Context init==========");
        System.out.println("==================================");

        sce.getServletContext().setAttribute("users", readUsers());

        sce.getServletContext().setAttribute("selections", readSelections());

        sce.getServletContext().setAttribute("confederations", readConfederations());

        sce.getServletContext().setAttribute("matchTypes", readMatchTypes());

        sce.getServletContext().setAttribute("year", new Date().getYear() + 1900);

        sce.getServletContext().setAttribute("matches", readMatches());

        sce.getServletContext().setAttribute("administrator", true);

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyAppConfig.class);
        sce.getServletContext().setAttribute("applicationContext", applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("context destroyed");
    }

    private List<User> readUsers() {
        List<User> users = new UserRepository().getAllUsers();
        return users;
    }

    private List<Confederation> readConfederations() {
        List<Confederation> confederations = new ConfederationRepository().getAllConfederation();
        return confederations;
    }

    public List<MatchType> readMatchTypes() {
        List<MatchType> matchTypes = new MatchTypeRepository().getAllMatchTypes();
        return matchTypes;
    }

    private List<Selection> readSelections() {
        SelectionRepository selectionRepository = new SelectionRepository();
        List<Selection> selections = selectionRepository.getAllSelections();
        Collections.sort(selections,new SortSelectionsByName());
        return selections;
    }

    private List<Match> readMatches() {
        MatchRepository matchRepository = new MatchRepository();
        return matchRepository.getAllMatches();
    }

}
 