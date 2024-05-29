/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.entities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLWarning;

/**
 *
 * @author hugo
 */
public class DataSource extends Context{
    
    private String url;
    private String query;
    private String user;
    private String database;
    private String message;
    private String sqlState;
    
    public DataSource(Connection con, PreparedStatement stmt ) {
        if ( con == null ) {
            this.message = "La conexion era null no se pudieron obtener datos";
            return;
        }
        try {
            DatabaseMetaData meta = con.getMetaData();
            this.url = meta.getURL();
            this.query = stmt != null ? new String (stmt.toString().getBytes(), "UTF8") : "";
            this.database = meta.getDatabaseProductName();
            this.user = meta.getUserName();
            SQLWarning warning = con.getWarnings();
            StringBuilder warningMessages = new StringBuilder();
            StringBuilder warningState = new StringBuilder();
            while( warning != null ) {
                warningMessages.append(warning.getMessage()).append( "\n|");
                warningState.append(warning.getSQLState()).append( "\n|");
                warning = warning.getNextWarning();
            }
            this.message = warningMessages.toString();
            this.sqlState =  warningState.toString();
        }catch(Exception ex) {
            this.message = "ERROR AL CREAR EL OBJETO DATASOURCE PARA LOGUEO:  " +
                    ex.getMessage();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSqlState() {
        return sqlState;
    }

    public void setSqlState(String sqlState) {
        this.sqlState = sqlState;
    }   
}
