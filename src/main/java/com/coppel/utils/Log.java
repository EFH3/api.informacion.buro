/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.utils;

import com.coppel.entities.DataSource;
import com.coppel.entities.ErrorInfoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author smatty
 */
public class Log {
    public static void addInfo(Class clase, String mensaje) {
        addErrorLog(clase, Level.INFO, mensaje, null);
    }
    
    public static void addError(Class clase, String mensaje) {
        addErrorLog(clase, Level.ERROR, mensaje, null);
    }
    
    public static void addError(Class clase, String mensaje, Exception excepcion) {
        if (excepcion instanceof ErrorInfoException == false) {
            excepcion = new ErrorInfoException.ErrorInfoBuilder()
                .error(excepcion).build();
        }
        
        addErrorLog(clase, Level.ERROR, "", excepcion.toString());
    }
    
    /** 
     * Método para agregar al log un error de conexión, utilizando el tipo de excepción: ErrorInfoException.
     * @param clase
     * @param mensaje 
     * @param conexion  
     * @param stm 
     * @param excepcion 
     */
    public static void addError(Class clase, String mensaje, Connection conexion, 
        PreparedStatement stm, Exception excepcion) {
    
        if (excepcion instanceof ErrorInfoException == false) {
            excepcion = new ErrorInfoException.ErrorInfoBuilder()
                .context(new DataSource(conexion, stm))
                .error(excepcion).build();
        }
        
        addErrorLog(clase, Level.ERROR, mensaje, excepcion.toString());        
    }
    
    /** 
     * Método para agregar al log un error de conexión, utilizando el tipo de excepción: ErrorInfoException.
     * @param clase
     * @param conexion  
     * @param stm 
     * @param excepcion 
     */
    public static void addError(Class clase, Connection conexion, PreparedStatement stm, Exception excepcion) {
    
        if (excepcion instanceof ErrorInfoException == false) {
            excepcion = new ErrorInfoException.ErrorInfoBuilder()
                .context(new DataSource(conexion, stm))
                .error(excepcion).build();
        }
        
        addErrorLog(clase, Level.ERROR, "", excepcion.toString());        
    }
    
    private static void addErrorLog(Class clase, Level nivel, String mensaje, String excepcion) {
        Logger logFile = LogManager.getLogger(clase);
        
        if (excepcion == null)
            logFile.log(nivel, mensaje);
        else 
            logFile.log(nivel, mensaje + " " + excepcion);
    }
}
