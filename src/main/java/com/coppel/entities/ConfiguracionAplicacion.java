/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.entities;

/**
 *
 * @author smatty
 */
public class ConfiguracionAplicacion {

    private int tiempoEnCache;
    private boolean esConsultarToken;

    //Buro
    private String odbcBuro;
    private String usuarioBuro;
    private String contrasenaBuro;
    
    private int idleTimeOut;
    private int minIdle;
    private int maxPoolSize;

    public ConfiguracionAplicacion()
    {
        this.tiempoEnCache = 0;
        this.esConsultarToken = false;
    }

    /**
     * @return the tiempoEnCache
     */
    public int getTiempoEnCache() {
        return tiempoEnCache;
    }

    /**
     * @param tiempoEnCache the tiempoEnCache to set
     */
    public void setTiempoEnCache(int tiempoEnCache) {
        this.tiempoEnCache = tiempoEnCache;
    }

    /**
     * @return the idleTimeOut
     */
    public int getIdleTimeOut() {
        return idleTimeOut;
    }

    /**
     * @param idleTimeOut the idleTimeOut to set
     */
    public void setIdleTimeOut(int idleTimeOut) {
        this.idleTimeOut = idleTimeOut;
    }

    /**
     * @return the minIdle
     */
    public int getMinIdle() {
        return minIdle;
    }

    /**
     * @param minIdle the minIdle to set
     */
    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    /**
     * @return the maxPoolSize
     */
    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    /**
     * @param maxPoolSize the maxPoolSize to set
     */
    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    /**
     * @return the esConsultarToken
     */
    public boolean isEsConsultarToken() {
        return esConsultarToken;
    }

    /**
     * @param esConsultarToken the esConsultarToken to set
     */
    public void setEsConsultarToken(boolean esConsultarToken) {
        this.esConsultarToken = esConsultarToken;
    }
    
    public String getOdbcBuro() {
        return odbcBuro;
    }

    public void setOdbcBuro(String odbcBuro) {
        this.odbcBuro = odbcBuro;
    }

    public String getUsuarioBuro() {
        return usuarioBuro;
    }

    public void setUsuarioBuro(String usuarioBuro) {
        this.usuarioBuro = usuarioBuro;
    }

    public String getContrasenaBuro() {
        return contrasenaBuro;
    }

    public void setContrasenaBuro(String contrasenaBuro) {
        this.contrasenaBuro = contrasenaBuro;
    }
}
