/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.viewresolver;

import fon.silab.fifawebservlet.action.constants.PageConstants;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author Veljko
 */
@Component
public class ViewResolver {
    
    private final Map<String,String> viewPageMap;

    public ViewResolver() {
        viewPageMap = new HashMap<String,String>(){
            {
                put(PageConstants.VIEW_LOGIN,PageConstants.PAGE_LOGIN);
                put(PageConstants.VIEW_DEFAULT_ERROR,PageConstants.PAGE_DEFAULT_ERROR);
                put(PageConstants.VIEW_ALL_USERS,PageConstants.PAGE_ALL_USERS);
                put(PageConstants.VIEW_ADD_USERS,PageConstants.PAGE_ADD_USERS);
                put(PageConstants.VIEW_HOME,PageConstants.PAGE_HOME);
                put(PageConstants.VIEW_ADD_SELECTION,PageConstants.PAGE_ADD_SELECTION);
                put(PageConstants.VIEW_ALL_SELECTION,PageConstants.PAGE_ALL_SELECTIONS);
                put(PageConstants.VIEW_ALL_MATCHES,PageConstants.PAGE_ALL_MATCHES);
                put(PageConstants.VIEW_ADD_MATCHES,PageConstants.PAGE_ADD_MATCHES);
                put(PageConstants.VIEW_REGISTRATION, PageConstants.PAGE_REGISTRATION);
                put(PageConstants.VIEW_EDIT_SELECTION, PageConstants.PAGE_EDIT_SELECTION);
                put(PageConstants.VIEW_VIEW_MATCHES, PageConstants.PAGE_VIEW_MATCHES);
                put(PageConstants.VIEW_VIEW_ALLMATCHES, PageConstants.PAGE_VIEW_ALLMATCHES);
                put(PageConstants.VIEW_EDIT_DELETE_SELECTION, PageConstants.PAGE_EDIT_DELETE_SELECTION);
            }
        };
    }
    
    

    public String getPage(String view) {
        return viewPageMap.get(view);
    }
    
}
