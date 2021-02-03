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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class RegisterUserAction extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String retypedPassowrd = request.getParameter("retypedpassword");

        if (username.length() < 6) {
            return shortUsername(request);
        }
        if (password.length() < 6) {
            return shortPassword(request);
        }
        if (!password.equals(retypedPassowrd)) {
            return differentPassowrd(request);
        }
        
        User user = findUser(request, username);
        if (user != null) {
            return busyUsername(request);
        }
        User newUser = createUser(username, ServiceCryptPassword.cryptPassword(password));
        return sucesfullRegistration(request, newUser);
    }

    private User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAdministator(false);
        user.setActive(true);
        return user;
    }

    private String sucesfullRegistration(HttpServletRequest request, User newUser) {
        request.setAttribute("message", "Sucesfull registration.");
        new UserRepository().saveUser(newUser);
        request.getServletContext().setAttribute("users", new UserRepository().getAllUsers());
        return PageConstants.VIEW_REGISTRATION;
    }

    private String busyUsername(HttpServletRequest request) {
        request.setAttribute("message", "Username is busy.");
        return PageConstants.VIEW_REGISTRATION;
    }

    private String shortUsername(HttpServletRequest request) {
        request.setAttribute("message", "Username must 6 characters or longer.");
        return PageConstants.VIEW_REGISTRATION;
    }

    private String shortPassword(HttpServletRequest request) {
        request.setAttribute("message", "Passwords must 6 characters or longer.");
        return PageConstants.VIEW_REGISTRATION;
    }

    private String differentPassowrd(HttpServletRequest request) {
        request.setAttribute("message", "Passwords are not same.");
        return PageConstants.VIEW_REGISTRATION;
    }

    private User findUser(HttpServletRequest request, String username) {
        List<User> users = (List<User>) request.getServletContext().getAttribute("users");
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
