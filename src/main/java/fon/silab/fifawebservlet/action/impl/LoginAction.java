/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.impl;

import fon.silab.fifawebservlet.action.AbstractAction;
import fon.silab.fifawebservlet.action.constants.PageConstants;
import fon.silab.fifawebservlet.action.services.ServiceCryptPassword;
import fon.silab.fifawebservlet.model.User;
import fon.silab.fifawebservlet.repositories.UserRepository;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class LoginAction extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (password == null) {
            request.setAttribute("message", "");
            return PageConstants.VIEW_LOGIN;
        }

        password = ServiceCryptPassword.cryptPassword(password);
        User user = new UserRepository().getUserByUsernameAndPassword(username, password);
        if (user == null) {
            return wrongUsernamePassword(request);
        }
        if (!user.isActive()) {
            return inactiveUser(request);
        }
        return sucessfulLogin(user, request);
    }

    private String sucessfulLogin(User user, HttpServletRequest request) {
        writeUserInSession(request, user);
        return PageConstants.VIEW_HOME;
    }

    private String wrongUsernamePassword(HttpServletRequest request) {
        request.setAttribute("message", "User does not exist.");
        return PageConstants.VIEW_LOGIN;
    }

    private String inactiveUser(HttpServletRequest request) {
        request.setAttribute("message", "User is deactivated.");
        return PageConstants.VIEW_LOGIN;
    }

    private void writeUserInSession(HttpServletRequest request, User user) {
        request.getSession(true).setAttribute("loggedUser", user);
        request.getSession().setAttribute("administrator", user.isAdministator());
    }
}
