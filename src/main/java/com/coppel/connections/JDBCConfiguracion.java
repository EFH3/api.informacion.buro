/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.connections;

import java.util.Properties;

/**
 *
 * @author smatty
 */
public class JDBCConfiguracion {
    private final String url;
    private final String driver;
    private final String dataBase;
    private final String acceso;
    private final String user;
    private final String port;
    private final String server;
    private final String connectionTimeOut;
    private final String idleTimeout;
    private final String maxLifetime;
    private final String minimumIdle;
    private final String maximumPoolSize;
    private final String poolName;
    private final String cachePrepStmts;
    private final String prepStmtCacheSize;
    private final String prepStmtCacheSqlLimit;
    private final String leakDetectionThreshold;
    private final String patronConnectionString;
    
    public JDBCConfiguracion(Properties configuracion){
        String urlAux = "",driverAux = "",dataBaseAux = "",accesoAux = "",userAux = "",portAux = "",serverAux = "";
        String connectionTimeOutAux = "",idleTimeoutAux = "",maxLifetimeAux = "",minimumIdleAux = "",maximumPoolSizeAux = "";
        String poolNameAux = "",cachePrepStmtsAux = "",prepStmtCacheSizeAux = "",prepStmtCacheSqlLimitAux = "";
        String leakDetectionThresholdAux = "",patronConnectionStringAux = "";
        if (userAllowed()) {
            urlAux = configuracion.getProperty("JDBC_URL");
            driverAux = configuracion.getProperty("JDBC_DRIVERCLASSNAME");
            dataBaseAux = configuracion.getProperty("JDBC_DB");
            accesoAux = configuracion.getProperty("JDBC_PASSWORD");
            userAux = configuracion.getProperty("JDBC_USER");
            portAux = configuracion.getProperty("JDBC_PORT");
            serverAux = configuracion.getProperty("JDBC_SERVER");
            connectionTimeOutAux = configuracion.getProperty("JDBC_CONNECTIONTIMEOUT");
            idleTimeoutAux = configuracion.getProperty("JDBC_IDLETIMEOUT");
            maxLifetimeAux = configuracion.getProperty("JDBC_MAXLIFETIME");
            minimumIdleAux = configuracion.getProperty("JDBC_MINIMUMIDLE");
            maximumPoolSizeAux = configuracion.getProperty("JDBC_MAXIMUMPOOLSIZE");
            poolNameAux = configuracion.getProperty("JDBC_POOLNAME");
            cachePrepStmtsAux = configuracion.getProperty("JDBC_CACHEPREPSTMTS");
            prepStmtCacheSizeAux = configuracion.getProperty("JDBC_PREPSTMTCACHESIZE");
            prepStmtCacheSqlLimitAux = configuracion.getProperty("JDBC_PREPSTMTCACHESQLLIMIT");
            leakDetectionThresholdAux = configuracion.getProperty("JDBC_LEAKDETECTIONTHRESHOLD");
            patronConnectionStringAux = configuracion.getProperty("JDBC_PATRONCONNECTIONSTRING");
        }
        
        this.url = urlAux;
        this.driver = driverAux;
        this.dataBase = dataBaseAux;
        this.acceso = accesoAux;
        this.user = userAux;
        this.port = portAux;
        this.server = serverAux;
        this.connectionTimeOut = connectionTimeOutAux;
        this.idleTimeout = idleTimeoutAux;
        this.maxLifetime = maxLifetimeAux;
        this.minimumIdle = minimumIdleAux;
        this.maximumPoolSize = maximumPoolSizeAux;
        this.poolName = poolNameAux;
        this.cachePrepStmts = cachePrepStmtsAux;
        this.prepStmtCacheSize = prepStmtCacheSizeAux;
        this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimitAux;
        this.leakDetectionThreshold = leakDetectionThresholdAux;
        this.patronConnectionString = patronConnectionStringAux;
    }
    
    public String getCadenaConexion(){
        String[] listaServidores = this.server.split(",");
        String Cadena=null;
         if("org.voltdb.jdbc.Driver".equals(this.driver)){
            String servidores = "";
            for(short i=0; i<listaServidores.length; i++)
            {
                servidores+=listaServidores[i]+":"+ String.format("%d", this.getPort());
                if(i<(listaServidores.length-1)){
                    servidores+=",";
                }
            }
            Cadena = String.format(patronConnectionString, url,servidores);
        }else{
           Cadena= String.format(
                patronConnectionString,this.url,this.server,this.getPort(),this.dataBase);
        }
        return Cadena;
    }
    
    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getDataBase() {
        return dataBase;
    }

    public String getAcceso() {
        return acceso;
    }

    public String getUser() {
        return user;
    }

    public int getPort() {
        int valor = Integer.parseInt(port);
        return valor;
    }

    public String getServer() {
        return server;
    }

    public long getConnectionTimeOut() {
        return Long.parseLong(connectionTimeOut);
    }

    public long getIdleTimeout() {
        return Long.parseLong(idleTimeout);
    }

    public long getMaxLifetime() {
        return Long.parseLong(maxLifetime);
    }

    public int getMinimumIdle() {
        return Integer.parseInt(minimumIdle);
    }

    public int getMaximumPoolSize() {
        return Integer.parseInt(maximumPoolSize);
    }

    public String getPoolName() {
        return poolName;
    }

    public String getCachePrepStmts() {
        return cachePrepStmts;
    }

    public String getPrepStmtCacheSize() {
        return prepStmtCacheSize;
    }

    public String getPrepStmtCacheSqlLimit() {
        return prepStmtCacheSqlLimit;
    }

    public long getLeakDetectionThreshold() {
        return Long.parseLong(leakDetectionThreshold);
    }
    
    public Boolean userAllowed() {
        Boolean permiso = true;
        return permiso;
    }
}
