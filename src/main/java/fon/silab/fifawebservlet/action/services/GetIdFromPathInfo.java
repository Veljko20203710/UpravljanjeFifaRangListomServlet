/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.services;

/**
 *
 * @author Veljko
 */
public class GetIdFromPathInfo {
    
    private GetIdFromPathInfo(){}
    
    public static int getId(String pathInfo) {
        String[] pathStringSplit = pathInfo.split("/");
        return Integer.parseInt(pathStringSplit[pathStringSplit.length - 1]);
    }
}
