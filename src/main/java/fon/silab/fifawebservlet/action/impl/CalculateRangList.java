/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.impl;

import fon.silab.fifawebservlet.action.AbstractAction;
import fon.silab.fifawebservlet.action.constants.PageConstants;
import fon.silab.fifawebservlet.model.Match;
import fon.silab.fifawebservlet.model.Selection;
import fon.silab.fifawebservlet.repositories.SelectionRepository;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class CalculateRangList extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            List<Selection> selections = getAllSelections(request);
            for (Selection selection : selections) {
                List<Match> matches = getAllMatchesSelection(request, selection);
                selection.setPoints(calcutePoints(matches, selection));
            }
            setRanking(selections);
            return PageConstants.VIEW_ALL_SELECTION;
        } catch (Exception ex) {
            return PageConstants.VIEW_DEFAULT_ERROR;
        }
    }

    private static List<Selection> getAllSelections(HttpServletRequest request) throws Exception {
        return (List<Selection>) request.getServletContext().getAttribute("selections");
    }

    public static List<Match> getAllMatchesSelection(HttpServletRequest request, Selection selection) {
        List<Match> allMatches = (List<Match>) request.getServletContext().getAttribute("matches");
        List<Match> matchesBySelection = new LinkedList<>();
        for (Match match : allMatches) {
            if (match.getHost().getId() == selection.getId() || match.getAway().getId() == selection.getId()) {
                matchesBySelection.add(match);
            }
        }
        return matchesBySelection;
    }

    private static int calcutePoints(List<Match> matches, Selection selection) {
        try {
            int points = 0;
            for (Match m : matches) {
                points += checkWinner(m, selection) * checkImportance(m) * checkDate(m)
                        * checkOpponentStrength(m, selection) * checkConfederationStrength(m, selection);
            };
            return points;
        } catch (Exception ex) {
            return 0;
        }
    }

    private static double checkWinner(Match match, Selection selection) {
        if (match.getHost().getId() == selection.getId()) {
            return chechWinnerHost(match);
        } else {
            return chechWinnerAway(match);
        }
    }

    private static double chechWinnerAway(Match match) {
        if (match.getHostGoals() < match.getAwayGoals()) {
            return MatchConstants.WINNER;
        } else if (match.getHostGoals() == match.getAwayGoals()) {
            return MatchConstants.DRAW;
        } else {
            return MatchConstants.LOSE;
        }
    }

    private static double chechWinnerHost(Match match) {
        if (match.getHostGoals() > match.getAwayGoals()) {
            return MatchConstants.WINNER;
        } else if (match.getHostGoals() == match.getAwayGoals()) {
            return MatchConstants.DRAW;
        } else {
            return MatchConstants.LOSE;
        }
    }

    private static double checkImportance(Match m) {
        return m.getMatchType().getStrenght();
    }

    private static double checkDate(Match m) {
        Date date = new Date();

        if (m.getDate().getYear() == date.getYear()) {
            return MatchConstants.THISYEAR;
        } else if (m.getDate().getYear() + 1 == date.getYear()) {
            return MatchConstants.LASTYEAR;
        } else if (m.getDate().getYear() + 2 == date.getYear()) {
            return MatchConstants.TWOYEARSAGO;
        } else if (m.getDate().getYear() + 3 == date.getYear()) {
            return MatchConstants.THREEYEARSAGO;
        }
        return 0;
    }

    private static double checkOpponentStrength(Match match, Selection selection) {
        return MatchConstants.INITIALOPPONENTSTRENGHT - getOpponent(match, selection).getRang();
    }

    private static double checkConfederationStrength(Match match, Selection selection) {
        return getOpponent(match, selection).getConfederation().getStrenght();
    }

    private static Selection getOpponent(Match match, Selection selection) {
        if (selection.equals(match.getAway())) {
            return match.getHost();
        }
        return match.getAway();
    }

    private static List<Selection> setRanking(List<Selection> selections) {
        int ranking = 1;
        SelectionRepository selectionRepository = new SelectionRepository();
        Collections.sort(selections, new fon.silab.fifawebservlet.action.services.SortSelectionByPoints());
        for (Selection selection : selections) {
            selection.setRang(ranking++);
            selectionRepository.updateSelection(selection);
        }
        return selections;
    }
}
