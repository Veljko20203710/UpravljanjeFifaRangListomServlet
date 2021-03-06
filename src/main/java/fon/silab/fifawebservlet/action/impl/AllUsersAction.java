/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.impl;

import fon.silab.fifawebservlet.action.AbstractAction;
import fon.silab.fifawebservlet.action.constants.PageConstants;
import fon.silab.fifawebservlet.repositories.UserRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class AllUsersAction extends AbstractAction{

    @Override
    public String execute(HttpServletRequest request) {
        request.getServletContext().setAttribute("users", new UserRepository().getAllUsers());
        return PageConstants.VIEW_ALL_USERS;
    }
    
}
