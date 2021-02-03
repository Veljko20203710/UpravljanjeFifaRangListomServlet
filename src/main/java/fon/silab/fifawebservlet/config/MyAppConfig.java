/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.fifawebservlet.config;

import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Veljko
 */
@ComponentScan(basePackages = {
    "fon.silab.fifawebservlet.controller",
    "fon.silab.fifawebservlet.viewresolver",
    "fon.silab.fifawebservlet.action"
})
public class MyAppConfig {
    
}
