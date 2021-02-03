/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.impl;

import fon.silab.fifawebservlet.action.AbstractAction;
import fon.silab.fifawebservlet.action.constants.PageConstants;
import fon.silab.fifawebservlet.action.services.GetIdFromPathInfo;
import fon.silab.fifawebservlet.model.Confederation;
import fon.silab.fifawebservlet.model.Selection;
import fon.silab.fifawebservlet.repositories.ConfederationRepository;
import fon.silab.fifawebservlet.repositories.MatchTypeRepository;
import fon.silab.fifawebservlet.repositories.SelectionRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class UpdateSelectionAction extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        int selectionId = GetIdFromPathInfo.getId(request.getPathInfo());
        String name = request.getParameter("name");
        String confederationParameter = request.getParameter("confederations");
        Confederation confederation = new ConfederationRepository().getConfederationByName(confederationParameter);
        if (isSelectionNameBusy(request, name)) {
            return unsuccessfulUpdate(request);
        }
        Selection selection = updateSelection(name, confederation,selectionId);
        return successfulUpdate(request, selection);
    }

    private Selection updateSelection(String name, Confederation confederation,int selectionId) {
        Selection selection = new SelectionRepository().getSelectionById(selectionId);
        selection.setName(name);
        selection.setConfederation(confederation);
        return selection;
    }

    private String successfulUpdate(HttpServletRequest request, Selection selection) {
        SelectionRepository selectionRepository = new SelectionRepository();
        request.setAttribute("message", "Selection sucesfully updated.");
        selectionRepository.updateSelection(selection);
        request.getServletContext().setAttribute("selections", selectionRepository.getAllSelections());
        return PageConstants.VIEW_ALL_SELECTION;
    }

    private String unsuccessfulUpdate(HttpServletRequest request) {
        request.setAttribute("message", "Selection with that name already exists.");
        return PageConstants.VIEW_ALL_SELECTION;
    }

    private boolean isSelectionNameBusy(HttpServletRequest request, String selectionName) {
        List<Selection> selections = (List<Selection>) request.getServletContext().getAttribute("selections");
        for (Selection selection : selections) {
            if (selection.getName().equals(selectionName)) {
                return true;
            }
        }
        return false;
    }
}
