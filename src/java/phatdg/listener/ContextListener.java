/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatdg.listener;
import phatdg.utils.PropertiesFileHelper;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * Web application lifecycle listener.
 *
 * @author natton
 */
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String siteMapLocation = 
                context.getInitParameter(
                        "SITEMAP_PROPERTIES_FILE_LOCATION");
        Properties siteMapProperty = 
                PropertiesFileHelper.getProperties(context, 
                                                siteMapLocation);
        context.setAttribute("SITE_MAP", siteMapProperty);
        String authenticationLocation = context.getInitParameter(
                "AUTHENTICATION_PROPERTIES_FILE_LOCATION");
        Properties authProperty = PropertiesFileHelper.getProperties(
                context, authenticationLocation);
        context.setAttribute("AUTHENTICATION", authProperty);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}

