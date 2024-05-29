/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.services;
import com.coppel.controllers.BuroController;
import com.coppel.entities.ConfiguracionAplicacion;
import com.coppel.entities.LogAccesosServicios;
import com.coppel.entities.MaeInfoBuroCliente;
import com.coppel.entities.MaeInfoModeloPrestamoPersonal;
import com.coppel.entities.ResponseParametrico;
import com.coppel.entities.SuccessResponse;
import com.coppel.utils.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author smatty
 */
@Path("buro")
public class Buro {
    
    @Context
    HttpServletRequest httpServletRequest;
    
    /**
     * variable con el objeto de tipo ConfiguracionAplicacion, con la configuración del servicio.
     */
    public ConfiguracionAplicacion configuracionApp;
    /**
     * Cadena con el formato de un error para el log.
     */
    private String mensajeErrorLog = 
        "Uuid: %s, Servicio: %s, PathInfo: %s, Metodo: %s, TipoConsulta: %s, Cliente: %s, Mensaje: %s";
    /**
     * Cadena con el formato de un error para informar a tienda.
     */
    private String mensajeErrorMesaAyuda = "Id Rastreo: %s. Mensaje: %s.";
    
    Gson gsonFormate = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    
    //Objeto para obtener los recursos para los mensajes.
    ResourceBundle recursos;
    
    private static final Logger LOGGER = LogManager.getLogger("Buro");
    
    /**
     * Constructor de la clase donde se obtiene la configuración necesaria para funcionar.
     * @throws IOException
     */
    public Buro() throws IOException, Exception{
        try{
            this.configuracionApp = new Util().getConfigPerService();
            if (this.configuracionApp == null) {
                LOGGER.error("La configuración viene vacia.");
            }
        }catch(Exception exec){
            LOGGER.error(exec.getMessage(), exec);
        }
    }
   
    
    /**
     * Metodo que regresa la fecha actual, indicando que el servicio esta funcional.
     * @return
     */
    @Path("estatus")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response enLinea()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
        return Response.ok(dateFormat.format(date) + " - Correcto.").build();
    }
    
    @Path("consultainfoburo/{numeroCliente}/tipo/{tipoConsulta}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response traerInfoBuroCliente(@PathParam("numeroCliente") int numeroCliente
            ,@PathParam("tipoConsulta") int tipoConsulta) 
            throws Exception {
        SuccessResponse<com.coppel.entities.MaeInfoBuroCliente> response = new SuccessResponse<>();
        LogAccesosServicios logAcccess = new LogAccesosServicios();
        MaeInfoBuroCliente maeInfoBuroCliente;
        
        try{
            //Validar que el cliente sea > 0 
            if(numeroCliente <= 0 ){
                maeInfoBuroCliente = new MaeInfoBuroCliente(numeroCliente);
            }else{
                BuroController burocontroller = new BuroController(this.configuracionApp);
                maeInfoBuroCliente = burocontroller.traerInfoBuroCliente(numeroCliente,tipoConsulta);

                if (maeInfoBuroCliente == null) {
                    maeInfoBuroCliente = new 
                    MaeInfoBuroCliente(-4,numeroCliente,"Error en la consulta.");

                    String jsonResponse = gsonFormate.toJson(maeInfoBuroCliente);
                    return Response.status(Response.Status.OK)
                        .entity(jsonResponse)
                        .type(MediaType.APPLICATION_JSON).build();
                }
            }
            String jsonResponse = gsonFormate.toJson(maeInfoBuroCliente);
            return Response.status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(jsonResponse).build();
        }
        catch(Exception exec){
            response.setEsCorrecto(false);
            String pathInfo = httpServletRequest.getPathInfo().replace("\r", "").replace("\n'", "");
            String mensajeError = 
                String.format(mensajeErrorLog, logAcccess.getUuid(), 
                httpServletRequest.getContextPath(), pathInfo,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 
                tipoConsulta, numeroCliente, exec.getMessage());
            LOGGER.error(mensajeError, exec);
            response.setMensaje(String.format(mensajeErrorMesaAyuda, logAcccess.getUuid(), 
                    exec.toString()));
            String jsonResponse = gsonFormate.toJson(response);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(jsonResponse)
                    .type(MediaType.APPLICATION_JSON).build();
        }
    }
    
    @Path("consultarinfocrediticia/{numeroCliente}/tipo/{tipoConsulta}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarInformacionCrediticia(@PathParam("numeroCliente") int numeroCliente
            ,@PathParam("tipoConsulta") int tipoConsulta) 
            throws Exception {
        SuccessResponse<com.coppel.entities.ResponseParametrico> response = new SuccessResponse<>();
        LogAccesosServicios logAcccess = new LogAccesosServicios();
        ResponseParametrico responseInfo;
        
        try{
            //Validar que el cliente sea > 0 
            if(numeroCliente <= 0 ){
                responseInfo = new ResponseParametrico();
            }else{
                BuroController burocontroller = new BuroController(this.configuracionApp);
                responseInfo = burocontroller.consultarInformacionCrediticia(numeroCliente,tipoConsulta);

                if (responseInfo == null) {
                    responseInfo = new 
                    ResponseParametrico(-4,"Error en la consulta.");

                    String jsonResponse = gsonFormate.toJson(responseInfo);
                    return Response.status(Response.Status.OK)
                        .entity(jsonResponse)
                        .type(MediaType.APPLICATION_JSON).build();
                }
            }
            String jsonResponse = gsonFormate.toJson(responseInfo);
            return Response.status(Response.Status.OK)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(jsonResponse).build();
        }
        catch(Exception exec){
            response.setEsCorrecto(false);
            String pathInfo = httpServletRequest.getPathInfo().replace("\r", "").replace("\n'", "");
            String mensajeError = 
                String.format(mensajeErrorLog, logAcccess.getUuid(), 
                httpServletRequest.getContextPath(), pathInfo,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 
                tipoConsulta, numeroCliente, exec.getMessage());
            LOGGER.error(mensajeError, exec);
            response.setMensaje(String.format(mensajeErrorMesaAyuda, logAcccess.getUuid(), 
                    exec.toString()));
            String jsonResponse = gsonFormate.toJson(response);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(jsonResponse)
                    .type(MediaType.APPLICATION_JSON).build();
        }
    }
    
    @Path("consultainfomodeloprestamopersonal/{numeroCliente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarInformacionModeloPrestamoPersonal(@PathParam("numeroCliente") int numeroCliente
            ,@PathParam("tipoConsulta") int tipoConsulta)
            throws Exception {
        SuccessResponse<com.coppel.entities.MaeInfoModeloPrestamoPersonal> response = new SuccessResponse<>();
        LogAccesosServicios logAcccess = new LogAccesosServicios();
        MaeInfoModeloPrestamoPersonal responseMaeInfoModelo;
        
        try{ 
            if(numeroCliente <= 0 ){
                responseMaeInfoModelo = new MaeInfoModeloPrestamoPersonal();
            }else{
                BuroController burocontroller = new BuroController(this.configuracionApp);
                responseMaeInfoModelo = burocontroller.consultarInformacionModeloPrestamoPersonal(numeroCliente,tipoConsulta);

                if (responseMaeInfoModelo == null) {
                    responseMaeInfoModelo = new 
                    MaeInfoModeloPrestamoPersonal(-4, "Error en la consulta.");

                    String jsonResponse = gsonFormate.toJson(responseMaeInfoModelo);
                    return Response.status(Response.Status.OK)
                        .entity(jsonResponse)
                        .type(MediaType.APPLICATION_JSON).build();
                }
            }
            String jsonResponse = gsonFormate.toJson(responseMaeInfoModelo);
            return Response.status(Response.Status.OK)
                    .entity(jsonResponse)
                    .type(MediaType.APPLICATION_JSON).build();
        }
        catch(Exception exec){
            response.setEsCorrecto(false);
            String pathInfo = httpServletRequest.getPathInfo().replace("\r", "").replace("\n'", "");
            String mensajeError = 
                String.format(mensajeErrorLog, logAcccess.getUuid(), 
                httpServletRequest.getContextPath(), pathInfo,
                Thread.currentThread().getStackTrace()[1].getMethodName(), 
                tipoConsulta, numeroCliente, exec.getMessage());
            LOGGER.error(mensajeError, exec);
            response.setMensaje(String.format(mensajeErrorMesaAyuda, logAcccess.getUuid(), 
                    exec.toString()));
            String jsonResponse = gsonFormate.toJson(response);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(jsonResponse)
                    .type(MediaType.APPLICATION_JSON).build();
        }
    }
    
}
