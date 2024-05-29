
package com.coppel.connections;

import com.coppel.coppelframework.config.ApplicationConfiguration;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Edgar D Ruiz
 */
public class HibernateUtil {
    
    private static HibernateUtil hibernateUtil;
    private final Properties props;
    protected SessionFactory sessionFactory;
     
    private HibernateUtil() throws IOException {       
        this.props = ApplicationConfiguration.getInstance().getProperties();
        if (checkAuthorization("authorization")) {
            this.sessionFactory = new Configuration()
                    .configure(new File(this.props.getProperty("hibernate")).getCanonicalPath())
                    .buildSessionFactory();
        }
    }
    
    public static HibernateUtil getInstance() throws IOException, SQLException {
        synchronized(HibernateUtil.class) {
            if (hibernateUtil == null) {
                hibernateUtil = new HibernateUtil();
            }
        }
        return hibernateUtil;
    }
    
    public Session getSession() {
        return this.sessionFactory.openSession();
    }
    
    public void cerrarPool() {
        if (!this.sessionFactory.isClosed()) {
            this.sessionFactory.close();
        }
    }
    
    private static boolean checkAuthorization(String userName) {
        return userName.equals("authorization");
    }
}
