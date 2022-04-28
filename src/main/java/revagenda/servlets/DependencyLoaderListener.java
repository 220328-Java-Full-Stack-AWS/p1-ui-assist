package revagenda.servlets;

import revagenda.ConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;


//you can think of this as the servlet container which communicates with all the servlets
//ServletContextListener is an interface that gets notified about ServletContext lifecycle changes.
// It offers two methods. contextInitialized - Is triggered when the web application is starting the initialization.
// This will be invoked before any of the filters and servlets are initialized.
//public interface ServletContext. Defines a set of methods that a servlet uses to communicate with its servlet container
public class DependencyLoaderListener implements ServletContextListener {
    Connection conn;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        conn = ConnectionManager.getConnection();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionManager.close();
    }
}