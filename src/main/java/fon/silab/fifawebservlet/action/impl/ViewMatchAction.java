/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.impl;

import fon.silab.fifawebservlet.action.AbstractAction;
import fon.silab.fifawebservlet.action.constants.PageConstants;
import fon.silab.fifawebservlet.action.services.GetIdFromPathInfo;
import fon.silab.fifawebservlet.model.Match;
import fon.silab.fifawebservlet.model.Selection;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class ViewMatchAction extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        int selectionId = GetIdFromPathInfo.getId(request.getPathInfo());
        request.setAttribute("matchesBySelection", getMatchesBySelection(request, selectionId));
        return PageConstants.VIEW_VIEW_MATCHES;
    }

    private List<Match> getMatchesBySelection(HttpServletRequest request, int selectionId) {
        List<Match> allMatches = (List<Match>) request.getServletContext().getAttribute("matches");
        List<Match> matchesBySelection = new LinkedList<>();
        for (Match match : allMatches) {
            if (match.getHost().getId() == selectionId || match.getAway().getId() == selectionId) {
                matchesBySelection.add(match);
            }
        }
        return matchesBySelection;
    }

}
