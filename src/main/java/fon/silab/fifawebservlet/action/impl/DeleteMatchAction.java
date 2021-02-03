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
import fon.silab.fifawebservlet.repositories.MatchRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class DeleteMatchAction extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        int matchId = GetIdFromPathInfo.getId(request.getPathInfo());
        MatchRepository matchRepository = new MatchRepository();
        Match match = matchRepository.getMatchById(matchId);
        matchRepository.deleteMatch(match);
        request.getServletContext().setAttribute("matches", matchRepository.getAllMatches());
        return PageConstants.VIEW_ALL_MATCHES;
    }
}
