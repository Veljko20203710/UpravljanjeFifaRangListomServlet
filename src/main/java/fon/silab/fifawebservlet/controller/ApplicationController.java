/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.controller;

import fon.silab.fifawebservlet.action.AbstractAction;
import fon.silab.fifawebservlet.action.constants.PageConstants;
import fon.silab.fifawebservlet.action.factory.ActionFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class ApplicationController {

    @Autowired
    ActionFactory actionFactory;

    public String procesRequest(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        String nextPage = PageConstants.VIEW_DEFAULT_ERROR;
        AbstractAction action = actionFactory.createActionFactory(pathInfo);

        if (action != null) {
            nextPage = action.execute(request);
        }

        if (request.getSession().getAttribute("loggedUser") == null && !nextPage.equals(PageConstants.VIEW_REGISTRATION)) {
            nextPage = PageConstants.VIEW_LOGIN;
        }
        return nextPage;
    }

}
