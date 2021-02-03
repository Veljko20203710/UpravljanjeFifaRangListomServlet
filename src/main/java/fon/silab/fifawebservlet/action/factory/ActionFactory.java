/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.factory;

import fon.silab.fifawebservlet.action.AbstractAction;
import fon.silab.fifawebservlet.action.constants.ActionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class ActionFactory {

    @Autowired
    private AbstractAction loginAction;

    @Autowired
    private AbstractAction allUsersAction;

    @Autowired
    private AbstractAction addUserAction;

    @Autowired
    private AbstractAction addSelectionAction;

    @Autowired
    private AbstractAction saveUserAction;

    @Autowired
    private AbstractAction allSelectionsAction;

    @Autowired
    private AbstractAction addMatchAction;

    @Autowired
    private AbstractAction allMatchesAction;

    @Autowired
    private AbstractAction registrationAction;

    @Autowired
    private AbstractAction registerUserAction;

    @Autowired
    private AbstractAction logoutAction;

    @Autowired
    private AbstractAction saveSelectionAction;

    @Autowired
    private AbstractAction deleteSelectionAction;

    @Autowired
    private AbstractAction editSelectionAction;

    @Autowired
    private AbstractAction updateSelectionAction;

    @Autowired
    private AbstractAction saveMatchAction;

    @Autowired
    private AbstractAction deleteMatchAction;

    @Autowired
    private AbstractAction viewMatchAction;

    @Autowired
    private AbstractAction viewAllMatchesAction;

    @Autowired
    private AbstractAction editDeleteSelectionAction;
    
    @Autowired
    private AbstractAction deactivateUserAction;

    @Autowired
    private AbstractAction calculateRangList;

    public AbstractAction createActionFactory(String actionName) {
        AbstractAction action = null;
        if (actionName.equals(ActionConstants.URL_LOGIN)) {
            action = loginAction;
        }

        if (actionName.equals(ActionConstants.URL_USERS_ALL)) {
            action = allUsersAction;
        }

        if (actionName.equals(ActionConstants.URL_USER_ADD)) {
            action = addUserAction;
        }

        if (actionName.equals(ActionConstants.URL_SELECTION_ADD)) {
            action = addSelectionAction;
        }

        if (actionName.equals(ActionConstants.URL_SAVE_USER)) {
            action = saveUserAction;
        }

        if (actionName.equals(ActionConstants.URL_SELECTION_ALL)) {
            action = allSelectionsAction;
        }

        if (actionName.equals(ActionConstants.URL_MATCH_ADD)) {
            action = addMatchAction;
        }

        if (actionName.equals(ActionConstants.URL_MATCH_ALL)) {
            action = allMatchesAction;
        }

        if (actionName.equals(ActionConstants.URL_REGISTRATION)) {
            action = registrationAction;
        }

        if (actionName.equals(ActionConstants.URL_REGISTERUSER)) {
            action = registerUserAction;
        }

        if (actionName.equals(ActionConstants.URL_LOGOUT)) {
            action = logoutAction;
        }

        if (actionName.equals(ActionConstants.URL_SELECTION_SAVE)) {
            action = saveSelectionAction;
        }

        if (actionName.startsWith(ActionConstants.URL_SELECTION_DELETE)) {
            action = deleteSelectionAction;
        }

        if (actionName.startsWith(ActionConstants.URL_SELECTION_EDIT)) {
            action = editSelectionAction;
        }

        if (actionName.startsWith(ActionConstants.URL_SELECTION_UPDATE)) {
            action = updateSelectionAction;
        }

        if (actionName.equals(ActionConstants.URL_MATCH_ADD)) {
            action = addMatchAction;
        }

        if (actionName.equals(ActionConstants.URL_MATCH_SAVE)) {
            action = saveMatchAction;
        }

        if (actionName.startsWith(ActionConstants.URL_MATCH_DELETE)) {
            action = deleteMatchAction;
        }

        if (actionName.startsWith(ActionConstants.URL_MATCH_VIEW)) {
            action = viewMatchAction;
        }

        if (actionName.equals(ActionConstants.URL_MATCH_VIEW_ALL)) {
            action = viewAllMatchesAction;
        }

        if (actionName.equals(ActionConstants.URL_SELECTION_EDIT_DELETE)) {
            action = editDeleteSelectionAction;
        }
        
         if (actionName.startsWith(ActionConstants.URL_DEACTIVATE_USER)) {
            action = deactivateUserAction;
        }
         
         if (actionName.equals(ActionConstants.URL_CALCULATE)) {
            action = calculateRangList;
        }
        return action;
    }
}
