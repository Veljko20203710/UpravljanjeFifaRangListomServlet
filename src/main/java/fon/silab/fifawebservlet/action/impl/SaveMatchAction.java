/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.impl;

import fon.silab.fifawebservlet.action.AbstractAction;
import fon.silab.fifawebservlet.action.constants.PageConstants;
import fon.silab.fifawebservlet.model.Match;
import fon.silab.fifawebservlet.model.MatchType;
import fon.silab.fifawebservlet.model.Selection;
import fon.silab.fifawebservlet.model.User;
import fon.silab.fifawebservlet.repositories.MatchRepository;
import fon.silab.fifawebservlet.repositories.MatchTypeRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class SaveMatchAction extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        String hostParametar = request.getParameter("host");
        Selection host = findSelection(hostParametar, request);

        String awayParametar = request.getParameter("away");
        Selection away = findSelection(awayParametar, request);
        int hostGoals;
        int awayGoals;

        try {
            hostGoals = Integer.parseInt(request.getParameter("hostGoals").trim());
            awayGoals = Integer.parseInt(request.getParameter("awayGoals").trim());
        } catch (NumberFormatException ex) {
            request.setAttribute("message", "Goals must be numbers.");
            return PageConstants.VIEW_ADD_MATCHES;
        }

        if (hostGoals < 0 || awayGoals < 0) {
            request.setAttribute("message", "Goals must not be negative numbers.");
            return PageConstants.VIEW_ADD_MATCHES;
        }

        int day = Integer.parseInt(request.getParameter("day"));
        int month = Integer.parseInt(request.getParameter("month"));
        int year = Integer.parseInt(request.getParameter("year"));

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date;

        try {
            date = sdf.parse(day + "." + month + "." + year);
        } catch (ParseException ex) {
            request.setAttribute("message", "Wrong date.");
            return PageConstants.VIEW_ADD_MATCHES;
        }

        if (date.after(new Date())) {
            return wrongDate(request);
        }

        if (host.equals(away)) {
            request.setAttribute("message", "Host and away selections are same.");
            return PageConstants.VIEW_ADD_MATCHES;
        }

        MatchType matchType = new MatchTypeRepository().getMatchTypeByName(request.getParameter("matchType"));

        Match match = new Match();
        match.setHost(host);
        match.setAway(away);
        match.setAwayGoals(awayGoals);
        match.setHostGoals(hostGoals);
        match.setDate(date);
        match.setMatchType(matchType);
        User user = (User) request.getSession().getAttribute("loggedUser");
        match.setUser(user);

        return sucesfullAdd(request, match);
    }

    private String wrongDate(HttpServletRequest request) {
        request.setAttribute("message", "Date must be in the past.");
        return PageConstants.VIEW_ADD_MATCHES;
    }

    private void createMatch() {

    }

    private String sucesfullAdd(HttpServletRequest request, Match match) {
        new MatchRepository().saveMatch(match);
        request.getServletContext().setAttribute("matches", new MatchRepository().getAllMatches());

        request.setAttribute("message", "Match sucesfully added.");
        return PageConstants.VIEW_ADD_MATCHES;
    }

    private Selection findSelection(String selectionName, HttpServletRequest request) {
        List<Selection> selections = (List<Selection>) request.getServletContext().getAttribute("selections");
        for (Selection selection : selections) {
            if (selection.getName().equals(selectionName)) {
                return selection;
            }
        }
        return null;
    }
}
