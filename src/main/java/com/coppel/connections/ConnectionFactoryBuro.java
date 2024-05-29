/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.connections;

import com.coppel.entities.ConfiguracionAplicacion;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;

/**
 *
 * @author smatty
 */
public class ConnectionFactoryBuro {
    private static ConnectionFactoryBuro connectionFactory;
    protected HikariDataSource ds;
    private String url;
    private Connection conn;
    private Properties props;
    
    /**
     * Constructor de la clase con la conexión al controlservicios desde context.
     * @throws NamingException 
     */
    private ConnectionFactoryBuro(ConfiguracionAplicacion conectParams) {
        this.url = "";
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(conectParams.getOdbcBuro());
        hikariConfig.setUsername(conectParams.getUsuarioBuro());
        hikariConfig.setPassword(conectParams.getContrasenaBuro());
        hikariConfig.setIdleTimeout(conectParams.getIdleTimeOut());
        hikariConfig.setMinimumIdle(conectParams.getMinIdle());
        hikariConfig.setMaximumPoolSize(conectParams.getMaxPoolSize());
        this.ds = new HikariDataSource(hikariConfig);
    }
    
    /**
     * Método que obtiene una instancia única para administrar los recursos
     * El contexto deberá estar dado de alta en Tomcat, bajo el nombre <code>appPooling</code>;
     * en caso de que no se encuentre se lanzará <code>NamingException</code>.
     * @param conectParams
     * @return Una de las conexiones disponibles del pool de conexiones
     * @throws javax.naming.NamingException
     * @throws java.io.IOException
     */
    public static ConnectionFactoryBuro getInstance(ConfiguracionAplicacion conectParams) 
            throws NamingException, IOException {
        synchronized(ConnectionFactoryBuro.class) {
            if (connectionFactory == null) {
                connectionFactory = new ConnectionFactoryBuro(conectParams);
            }
        }
        return connectionFactory;
    }
    /**
     * Método que obtiene una conexión disponible del Connection Pool del servidor.
     * El contexto deberá estar dado de alta en Tomcat, bajo el nombre <code>appPooling</code>;
     * en caso de que no se encuentre se lanzará <code>NamingException</code>.
     * @return Una de las conexiones disponibles del pool de conexiones
     * @throws java.sql.SQLException
     */
    public final Connection getConnection() throws SQLException {
        /*if(url.isEmpty()){
            return ds.getConnection();
        }else{
            conn = DriverManager.getConnection(url, props);
            return conn;
        }*/
        
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            if(url.isEmpty()){
                return ds.getConnection();
            }else{
            conn = DriverManager.getConnection(url, props);
            return conn;
            }
        } finally {
            if (this.conn != null) {
                conn.close();
            }
        }
    }
    
    public void cerrarPool() {
        if (!this.ds.isClosed()) {
            this.ds.close();
        }
    }
}
