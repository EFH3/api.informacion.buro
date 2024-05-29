/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.models;

import com.coppel.connections.ConnectionFactoryBuro;
import com.coppel.controllers.BuroController;
import com.coppel.dal.BuroDAL;
import com.coppel.entities.ConfiguracionAplicacion;
import com.coppel.entities.MaeInfoBuroCliente;
import com.coppel.entities.MaeInfoModeloPrestamoPersonal;
import com.coppel.entities.ResponseParametrico;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author amilcar.trujillo
 */
public class BuroModel {
    public Connection con = null;
    
     /**
     * Logger de la clase.
     */
    private static final Logger LOGGER = LogManager.getLogger(BuroModel.class.getName());
    
        /**
     * Conexión al central de venta asistida.
     * @param conectParams
     * @throws java.lang.Exception
     */
    public BuroModel(ConfiguracionAplicacion conectParams) throws Exception
    {
        try
        {
            ConnectionFactoryBuro cf = ConnectionFactoryBuro.getInstance(conectParams);
            con = cf.getConnection();
        }
        catch(SQLException | NamingException ex){
            //System.out.println("Error no controlado: " + ex.getMessage());
            LOGGER.error("Constructor: Error en BuroModel: " + ex.getMessage());
        } 
        catch (Exception ex) {
            LOGGER.error("Constructor: Error en BuroModel: " + ex.getMessage());
            throw ex;
        }
    }
    
     public BuroModel(Connection con){
        this.con = con;
    }
     
     public MaeInfoBuroCliente traerInfoBuroCliente(int numeroCliente, int tipoConsulta)
             throws Exception {
         BuroDAL buroDal;
         MaeInfoBuroCliente responseEntity;
         try {
             buroDal = new BuroDAL(this.con);
             responseEntity = buroDal.traerInfoBuroCliente(numeroCliente,tipoConsulta);
         } finally {
             if (this.con != null) {
                 con.close();
             }
         }
         return responseEntity;
     }
     
     public ResponseParametrico consultarInformacionCrediticia(int numeroCliente, int tipoConsulta)
             throws Exception {
         BuroDAL buroDal = new BuroDAL(this.con);
         return buroDal.consultaInfoCrediticia(numeroCliente,tipoConsulta);
     }
     
    public MaeInfoModeloPrestamoPersonal consultarInformacionModeloPrestamoPersonal(int numeroCliente, int tipoConsulta)
            throws Exception {
        BuroDAL buroDal = new BuroDAL(this.con);
        return buroDal.consultaInfoModeloPrestamoPersonal(numeroCliente,tipoConsulta);
    }
     
}
