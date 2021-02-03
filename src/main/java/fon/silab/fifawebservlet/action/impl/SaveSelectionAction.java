/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.impl;

import fon.silab.fifawebservlet.action.AbstractAction;
import fon.silab.fifawebservlet.action.constants.PageConstants;
import fon.silab.fifawebservlet.model.Confederation;
import fon.silab.fifawebservlet.model.Selection;
import fon.silab.fifawebservlet.model.User;
import fon.silab.fifawebservlet.repositories.ConfederationRepository;
import fon.silab.fifawebservlet.repositories.SelectionRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class SaveSelectionAction extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");

        if (isSelectionNameBusy(request, name)) {
            return unsuccessfulCreate(request);
        }

        String confederationParametar = request.getParameter("confederations");
        Confederation confederation = new ConfederationRepository().getConfederationByName(confederationParametar);     
        Selection selection = createSelection(name, confederation, request);
        return successfulCreate(selection, request);
    }
    
    private String successfulCreate(Selection selection,HttpServletRequest request) {
        new SelectionRepository().save(selection);
        request.getServletContext().setAttribute("selections", new SelectionRepository().getAllSelections());
        request.setAttribute("message", "Selection sucesfully added.");
        return PageConstants.VIEW_ADD_SELECTION;
    }
    
    private Selection createSelection(String name,Confederation confederation,HttpServletRequest request) {
        Selection selection = new Selection();
        selection.setName(name);
        selection.setPoints(0);
        selection.setConfederation(confederation);
        selection.setRang(((List<Selection>) request.getServletContext().getAttribute("selections")).size() + 1);
        User user = (User) request.getSession().getAttribute("loggedUser");
        selection.setUser(user);
        return selection;
    }

    private String unsuccessfulCreate(HttpServletRequest request) {
        request.setAttribute("message", "Selection with that name already exists.");
        return PageConstants.VIEW_ADD_SELECTION;
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
