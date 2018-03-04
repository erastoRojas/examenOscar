package config;


import config.Configuration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Erasto Rojas Sánchez
 */
public class ConfigListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
      Configuration.getInstance(sce.getServletContext().getResourceAsStream("/WEB-INF/config.yml"),
              sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
