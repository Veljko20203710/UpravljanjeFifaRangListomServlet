/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.impl;

import fon.silab.fifawebservlet.action.AbstractAction;
import fon.silab.fifawebservlet.action.constants.PageConstants;
import fon.silab.fifawebservlet.action.services.GetIdFromPathInfo;
import fon.silab.fifawebservlet.model.Selection;
import fon.silab.fifawebservlet.repositories.SelectionRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class EditSelectionAction extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        int selectionId = GetIdFromPathInfo.getId(request.getPathInfo());
        request.setAttribute("selection", new SelectionRepository().getSelectionById(selectionId));
        return PageConstants.VIEW_EDIT_SELECTION;
    }
}
