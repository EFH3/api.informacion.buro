
package com.coppel.connections;

import com.coppel.coppelframework.config.ApplicationConfiguration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import com.coppel.dal.BuroDAL;
/**
 *
 * @author emmanuel
 */
public class ConnectionFactoryPool {
    
    private static ConnectionFactoryPool connectionFactoryPool;
    private Properties props;
    protected HikariDataSource ds;
    
    private ConnectionFactoryPool()  {
        this.props = ApplicationConfiguration.getInstance().getProperties();
        HikariConfig config = new HikariConfig();
        if (checkAuthorization("authorization")) {
            config.setJdbcUrl(this.props.getProperty("jdbcURL"));
            config.setUsername(this.props.getProperty("userName"));
            config.setPassword(this.props.getProperty("password"));
            config.addDataSourceProperty("cachePrepStmts", this.props.getProperty("cachePrepStmts"));
            config.addDataSourceProperty("prepStmtCacheSize", this.props.getProperty("prepStmtCacheSize"));
            config.addDataSourceProperty("prepStmtCacheSqlLimit", this.props.getProperty("prepStmtCacheSqlLimit"));

            this.ds = new HikariDataSource(config);
        }
    }
    
    public static ConnectionFactoryPool getInstance() throws IOException, SQLException {
        synchronized(ConnectionFactoryPool.class) {
            if (connectionFactoryPool == null) {
                connectionFactoryPool = new ConnectionFactoryPool();
            }
        }
        return connectionFactoryPool;
    }
    
    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }
    
    
    public void cerrarPool() {
        if (!this.ds.isClosed()) {
            this.ds.close();
        }
    }
    
    private static boolean checkAuthorization(String userName) {
        return userName.equals("authorization");
    }
}
