/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;

/**
 * Web application lifecycle listener.
 *
 * @author Divz
 */
public class InitBD implements ServletContextListener {
    @EJB  
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;  
 
    @Override 
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("APPLICATION DEPLOYEE");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("APPLICATION UNDEPLOYED");
    }
}
