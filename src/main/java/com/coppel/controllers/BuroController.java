/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.controllers;

import com.coppel.coppelframework.controllers.Controller;
import com.coppel.entities.ConfiguracionAplicacion;
import com.coppel.entities.MaeInfoBuroCliente;
import com.coppel.entities.MaeInfoModeloPrestamoPersonal;
import com.coppel.entities.ResponseParametrico;
import com.coppel.models.BuroModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author amilcar.trujillo
 */
public class BuroController extends Controller{

    private final ConfiguracionAplicacion configuracionApp;
    
    public BuroController(ConfiguracionAplicacion configuracionApp) {
        this.configuracionApp = configuracionApp;
    }
 
     /**
     * Logger de la clase.
     */
    private static final Logger LOGGER = LogManager.getLogger(BuroController.class.getName());
    
    
    public MaeInfoBuroCliente traerInfoBuroCliente(int numeroCliente, int tipoConsulta) throws Exception{
        try
        {
            return new BuroModel(this.configuracionApp).traerInfoBuroCliente(numeroCliente,tipoConsulta);
        }
        catch(Exception exec)
        {
            LOGGER.error("traerInfoBuroCliente: Error en Controller: " + exec.getMessage());
            throw exec;
        }
    } 
    
    public ResponseParametrico consultarInformacionCrediticia(int numeroCliente, int tipoConsulta) throws Exception{
        try
        {
            return new BuroModel(this.configuracionApp).consultarInformacionCrediticia(numeroCliente,tipoConsulta);
        }
        catch(Exception exec)
        {
            LOGGER.error("consultarInformacionCrediticia: Error en Controller: " + exec.getMessage());
            throw exec;
        }
    }
    
    public MaeInfoModeloPrestamoPersonal consultarInformacionModeloPrestamoPersonal(int numeroCliente, int tipoConsulta) throws Exception {
        try {
            return new BuroModel(this.configuracionApp).consultarInformacionModeloPrestamoPersonal(numeroCliente,tipoConsulta);
        } catch (Exception exec) {
            LOGGER.error("consultarInformacionModeloPrestamoPersonal: Error en Controller: " + exec.getMessage());
            throw exec;
        }
    }
}
