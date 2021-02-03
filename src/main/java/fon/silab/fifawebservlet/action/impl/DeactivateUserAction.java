/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.impl;

import fon.silab.fifawebservlet.action.AbstractAction;
import fon.silab.fifawebservlet.action.constants.PageConstants;
import fon.silab.fifawebservlet.action.services.GetIdFromPathInfo;
import fon.silab.fifawebservlet.model.User;
import fon.silab.fifawebservlet.repositories.UserRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class DeactivateUserAction extends AbstractAction{

    @Override
    public String execute(HttpServletRequest request) {
        int userID = GetIdFromPathInfo.getId(request.getPathInfo());
        User user = new UserRepository().getUserById(userID);
        user.setActive(!user.isActive());
        new UserRepository().updateUser(user);
        request.getServletContext().setAttribute("users", new UserRepository().getAllUsers());
        return PageConstants.VIEW_ALL_USERS;
    }
    
}
