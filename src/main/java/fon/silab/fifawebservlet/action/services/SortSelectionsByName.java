/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.action.services;

import fon.silab.fifawebservlet.model.Selection;
import java.util.Comparator;

/**
 *
 * @author Veljko
 */
public class SortSelectionsByName implements Comparator<Selection> {

    @Override
    public int compare(Selection a, Selection b) {
        return a.getName().compareTo(b.getName());
    }
}
