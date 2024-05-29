/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.dal;

import com.coppel.entities.MaeInfoBuroCliente;
import com.coppel.entities.MaeInfoModeloPrestamoPersonal;
import com.coppel.entities.ResponseParametrico;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author amilcar.trujillo
 */
public class BuroDAL {
    private static final Logger LOGGER = LogManager.getLogger(BuroDAL.class.getName());

    private Connection con = null;
    
    public BuroDAL(Connection con){
        this.con = con;
    }
    
    private static boolean checkAuthorization(String userName) {
        return userName.equals("authorization");
    }
    
    /**
     * Método que permite consultar el MaeInfoBuroCliente de Buro.
     * @param numeroCliente
     * @param tipoConsulta
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws NamingException 
     */
    public MaeInfoBuroCliente traerInfoBuroCliente(int numeroCliente, int tipoConsulta) 
            throws SQLException, IOException, NamingException{
        MaeInfoBuroCliente maeInfoBuroCln = new MaeInfoBuroCliente(numeroCliente);
        PreparedStatement stmnt = null;
        try {
            String query = new StringBuilder("SELECT numeroinfoburocredito")
            .append(",fechamovimiento, fechaactuliazacioncliente")
            .append(",numerocliente, numerosolicitud, clvetipoconsulta")
            .append(",fechaconsulta")
            .append(",fun_converttoutf8_infocrediticiacliente(mensaje) AS mensaje")
            .append(",numerolongitudtramaburo, clvegrupohit1")
            .append(",clvegrupohit2, clvegrupohit3, fechagrupohit")
            .append(",numerogrupohit, numerorstotalcuentasmopactual")
            .append(",numerorslimitecreditocuentasactivas")
            .append(",numerorssaldovencidocreditocuentasactivas")
            .append(",numerorsporcentajelimitecreditocuentasactivas")
            .append(",numerovalorscore")
            .append(" FROM fun_consultarinfoburocliente(?,?);")
                .toString();
            stmnt = con.prepareStatement(query);
            stmnt.setInt(1, numeroCliente);
            stmnt.setInt(2, tipoConsulta);
            
            if (checkAuthorization("authorization")) { 
                try(ResultSet resultado = stmnt.executeQuery();){
                    while(resultado.next()) {
                        maeInfoBuroCln.setNum_infoburocredito(resultado.getLong("numeroinfoburocredito"));

                        if(maeInfoBuroCln.getNum_infoburocredito() > 0 ){
                            maeInfoBuroCln.setFec_movimiento(
                                    resultado.getDate("fechamovimiento"));
                            maeInfoBuroCln.setFec_actuliazacioncliente(
                                    resultado.getDate("fechaactuliazacioncliente"));
                            maeInfoBuroCln.setNum_cliente(
                                    resultado.getInt("numerocliente"));
                            maeInfoBuroCln.setNum_solicitud(
                                    resultado.getLong("numerosolicitud"));
                            maeInfoBuroCln.setClv_tipoconsulta(
                                    resultado.getString("clvetipoconsulta"));
                            maeInfoBuroCln.setFec_consulta(
                                    resultado.getDate("fechaconsulta"));
                            maeInfoBuroCln.setDes_mensaje(
                                    resultado.getString("mensaje"));
                            maeInfoBuroCln.setNum_longitudtramaburo(
                                    resultado.getInt("numerolongitudtramaburo"));
                            maeInfoBuroCln.setClv_grupohit1(
                                    resultado.getString("clvegrupohit1"));
                            maeInfoBuroCln.setClv_grupohit2(
                                    resultado.getString("clvegrupohit2"));
                            maeInfoBuroCln.setClv_grupohit3(
                                    resultado.getString("clvegrupohit3"));
                            maeInfoBuroCln.setFec_grupohit(
                                    resultado.getString("fechagrupohit"));
                            maeInfoBuroCln.setNum_grupohit(
                                    resultado.getShort("numerogrupohit"));
                            maeInfoBuroCln.setNum_rstotalcuentasmopactual(
                                    resultado.getShort("numerorstotalcuentasmopactual"));
                            maeInfoBuroCln.setNum_rslimitecreditocuentasactivas(
                                    resultado.getInt("numerorslimitecreditocuentasactivas"));
                            maeInfoBuroCln.setNum_rssaldovencidocreditocuentasactivas(
                                    resultado.getInt("numerorssaldovencidocreditocuentasactivas"));
                            maeInfoBuroCln.setNum_rsporcentajelimitecreditocuentasactivas(
                                    resultado.getShort("numerorsporcentajelimitecreditocuentasactivas"));
                            maeInfoBuroCln.setNum_valorscore(
                                    resultado.getShort("numerovalorscore"));
                        }
                        else
                        {
                            maeInfoBuroCln.setDes_mensaje(
                                    resultado.getString("mensaje"));
                        }
                    } 
                }
            }
        }catch (Exception ex) {
            LOGGER.error("Excepción en DAL: " + ex);
            throw ex;
        } finally {
            if (stmnt != null) {
                stmnt.close();
            }
            if( con != null ) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    /*System.out.println("Error al cerrar la conexión de BD: " + ex.getMessage());*/
                    LOGGER.error("Error al cerrar la conexión de BD: " + ex.getMessage());
                }
            }
        }
        
        return maeInfoBuroCln;
    }
    
    /**
     * Método que permite consultar el consultaInfoCrediticia de Buro.
     * @param numeroCliente
     * @param tipoConsulta
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws NamingException 
     */
    public ResponseParametrico consultaInfoCrediticia(int numeroCliente, int tipoConsulta) 
            throws SQLException, IOException, NamingException{
        ResponseParametrico response = new ResponseParametrico();
        PreparedStatement stmnt = null;
        try {
            String query = new StringBuilder("SELECT numeroinfoburocredito")
            .append(", fechaconsulta")
            .append(", fun_converttoutf8_infocrediticiacliente(mensaje) AS mensaje")
            .append(", numerogrupohit, numerorstotalcuentasmopactual")
            .append(", numerorslimitecreditocuentasactivas")
            .append(", numerorssaldovencidocreditocuentasactivas")
            .append(", numerorsporcentajelimitecreditocuentasactivas")
            .append(", numerovalorscore")
            .append(" FROM fun_consultarinfoburocliente(?,?);")
                .toString();
            stmnt = con.prepareStatement(query);
            stmnt.setInt(1, numeroCliente);
            stmnt.setInt(2, tipoConsulta);

            if (checkAuthorization("authorization")) {
                try(ResultSet resultado = stmnt.executeQuery();){
                    while(resultado.next()) {
                        response.setNum_infoburocredito(resultado.getLong("numeroinfoburocredito"));

                        if(response.getNum_infoburocredito() > 0 ){
                            response.setFec_consulta(
                                    resultado.getDate("fechaconsulta"));
                            response.setNum_grupohit(
                                    resultado.getShort("numerogrupohit"));
                            response.setNum_rstotalcuentasmopactual(
                                    resultado.getShort("numerorstotalcuentasmopactual"));
                            response.setNum_rslimitecreditocuentasactivas(
                                    resultado.getInt("numerorslimitecreditocuentasactivas"));
                            response.setNum_rssaldovencidocreditocuentasactivas(
                                    resultado.getInt("numerorssaldovencidocreditocuentasactivas"));
                            response.setNum_rsporcentajelimitecreditocuentasactivas(
                                    resultado.getShort("numerorsporcentajelimitecreditocuentasactivas"));
                            response.setNum_valorscore(
                                    resultado.getShort("numerovalorscore"));
                            response.setMensaje("");
                        }
                        else
                        {
                            response.setMensaje(
                                    resultado.getString("mensaje"));
                        }
                    } 
                }
            }
        }catch (Exception ex) {
            LOGGER.error("Excepción en DAL: " + ex);
            throw ex;
        } finally {
            if (stmnt != null) {
                stmnt.close();
            }
            if( con != null ) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    /*System.out.println("Error al cerrar la conexión de BD: " + ex.getMessage());*/
                    LOGGER.error("Error al cerrar la conexión de BD: " + ex.getMessage());
                }
            }
        }
        return response;
    }
    
    /**
     * Método que permite consultar el MaeInfoModeloPrestamoPersonal de Buro.
     * @param numeroCliente
     * @param tipoConsulta
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws NamingException 
     */
    public MaeInfoModeloPrestamoPersonal consultaInfoModeloPrestamoPersonal(int numeroCliente, int tipoConsulta)
            throws SQLException, IOException, NamingException {
        MaeInfoModeloPrestamoPersonal maeInfoPPersonal = new MaeInfoModeloPrestamoPersonal();
        PreparedStatement stmnt = null;
        try {
            String query = new StringBuilder("SELECT numeroBuroCredito")
                    .append(", numCliente")
                    .append(", consultasburo30dias")
                    .append(", consultasburo90dias")
                    .append(", consultasburoporproductoprestamopersonal")
                    .append(", numerodireccionesreportadasburo, mopcuentamorosagravereciente")
                    .append(", saldocuentamorosagravereciente, duracioncuentamorosagravereciente")
                    .append(", numerotarjetascredito, peorMop, porcentajeusolineascredito")
                    .append(", duracionmaximacuenta, maximoplazopagosfijos")
                    .append(", mesespromedioaperturatarjetascredito")
                    .append(", promedioporcentajescomportamientocorriente")
                    .append(", mesespromediocomportamientocuentas")
                    .append(", numerocuentasconinformacion")
                    .append(" FROM fun_obtenerinformacionburoprestamos(?);")
                    .toString();
            stmnt = con.prepareStatement(query);
            stmnt.setInt(1, numeroCliente);

            if (checkAuthorization("authorization")) {
                try(ResultSet resultado = stmnt.executeQuery();){
                    while(resultado.next()) {
                        maeInfoPPersonal.setNum_infoburocredito(resultado.getLong("numeroBuroCredito"));

                        if(maeInfoPPersonal.getNum_infoburocredito() > 0 ) {
                            maeInfoPPersonal.setNum_consultasndiashitsininformacion(
                                    resultado.getShort("consultasburo30dias"));
                            maeInfoPPersonal.setNum_consultasndiashitconinformacion(
                                    resultado.getShort("consultasburo90dias"));
                            maeInfoPPersonal.setNum_consultasprestamos(
                                    resultado.getShort("consultasburoporproductoprestamopersonal"));
                            maeInfoPPersonal.setNum_direccionesreportadas(
                                    resultado.getShort("numerodireccionesreportadasburo"));
                            maeInfoPPersonal.setNum_mopreciente(
                                    resultado.getShort("mopcuentamorosagravereciente"));
                            maeInfoPPersonal.setNum_saldomopreciente(
                                    resultado.getInt("saldocuentamorosagravereciente"));
                            maeInfoPPersonal.setNum_mesesmopreciente(
                                    resultado.getShort("duracioncuentamorosagravereciente"));
                            maeInfoPPersonal.setNum_tarjetascredito(
                                    resultado.getShort("numerotarjetascredito"));
                            maeInfoPPersonal.setNum_peormop(
                                    resultado.getShort("peorMop"));
                            maeInfoPPersonal.setNum_porcentajeusocuentasabiertas(
                                    resultado.getShort("porcentajeusolineascredito"));
                            maeInfoPPersonal.setNum_duracionmaximacuenta(
                                    resultado.getShort("duracionmaximacuenta"));
                            maeInfoPPersonal.setNum_maximoplazopagofijo(
                                    resultado.getShort("maximoplazopagosfijos"));
                            maeInfoPPersonal.setNum_mesespromedioaperturatarjetacredito(
                                    resultado.getShort("mesespromedioaperturatarjetascredito"));
                            maeInfoPPersonal.setNum_promedioporcentajecomportamientocorriente(
                                    resultado.getShort("promedioporcentajescomportamientocorriente"));
                            maeInfoPPersonal.setNum_mesespromediocomportamientocuenta(
                                    resultado.getShort("mesespromediocomportamientocuentas"));
                            maeInfoPPersonal.setNum_cuentasconinformacion(
                                    resultado.getShort("numerocuentasconinformacion"));
                            maeInfoPPersonal.setMensaje("OK");
                        }
                        else
                        {
                            maeInfoPPersonal.setMensaje("Cliente No Valido");
                        }
                    } 
                }
            }
        }catch (Exception ex) {
            LOGGER.error("Excepción en DAL: " + ex);
            throw ex;
        } finally {
            if (stmnt != null) {
                stmnt.close();
            }
            if( con != null ) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    /*System.out.println("Error al cerrar la conexión de BD: " + ex.getMessage());*/
                    LOGGER.error("Error al cerrar la conexión de BD: " + ex.getMessage());
                }
            }
        }
        return maeInfoPPersonal;
    } 
}
