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
import fon.silab.fifawebservlet.repositories.MatchRepository;
import fon.silab.fifawebservlet.repositories.SelectionRepository;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class DeleteSelectionAction extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {;
        int selectionId = GetIdFromPathInfo.getId(request.getPathInfo());
        SelectionRepository selectionRepository = new SelectionRepository();
        Selection selection = selectionRepository.getSelectionById(selectionId);
        List<Match> matches = getAllmatchesBySelection(request,selectionId);
        MatchRepository matchRepository = new MatchRepository();
        for (Match match : matches) {
            matchRepository.deleteMatch(match);
        }
        selectionRepository.deleteSelection(selection);
        request.getServletContext().setAttribute("selections", new SelectionRepository().getAllSelections());
        request.getServletContext().setAttribute("matches", new MatchRepository().getAllMatches());
        return PageConstants.VIEW_EDIT_DELETE_SELECTION;
    }
    
    private List<Match> getAllmatchesBySelection(HttpServletRequest request, int selectionID) {
       List<Match> matches = (List<Match>) request.getServletContext().getAttribute("matches");
       List<Match> matchesBySelection = new LinkedList<>();
        for (Match match : matches) {
            if(match.getHost().getId()==selectionID || match.getAway().getId()==selectionID ) matchesBySelection.add(match);
        }
        return matchesBySelection;
    }
}
