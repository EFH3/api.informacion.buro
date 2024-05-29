/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.filters;

import com.coppel.coppelframework.config.ApplicationConfiguration;
import com.coppel.utils.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author smatty
 */
@Provider
public class SSOFilter implements ContainerRequestFilter {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setLenient().create();
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        //Si la configuración indica que no se debe consultar token o el servicio que se esta consumiendo
        //es el de la configuración, no debe validar el token.
        String tokenSSO = "SINTOKEN";
        
        try{
            if(requestContext.getUriInfo().getPath().contains("/buro/consultainfoburo/")){
            
                if(!new Util().getPropIsWithToken()){
                    //Si el parámetro validar token no está activo, se pasa libremente.
                    return;
                }
            }
            else{
                //Se deja pasar libre.
                return;
            }
        }catch(Exception ex){
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(gson.toJson(new Error(ex.getMessage())))
                    .build()
            );
            return;
        }
        if( requestContext.getMethod().equals(HttpMethod.OPTIONS)) return;
        //Antes
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        Gson gson = new Gson();
        
        // Comprobamos si se proporciono el token
        if (authorizationHeader == null ) {
            // Regresamos el mensaje de error.            
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .type(MediaType.APPLICATION_JSON)
                    .entity( gson.toJson("No se proporcionó un token de sesion válido."))
                    .build());
            return;
        }else{
            // Extraemos el token.
            tokenSSO = authorizationHeader.trim();
        }
        try 
        {
            validateToken(tokenSSO);
        } 
        catch (Exception e) {
            requestContext.abortWith(Response
                    .status(Response.Status.UNAUTHORIZED)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(gson.toJson(new Error(e.getMessage())))
                    .build()
            );
        }
    }
    
    /**
     * Metodo para consultar la validez del token
     * @param token Cadena string con el token de sesión SSO.
     * @throws Exception 
     */
    private void validateToken(String token) throws Exception {        
        Properties configProperties = ApplicationConfiguration
                .getInstance()
                .getProperties();
        if (userAllowed()) {
            String url = configProperties.getProperty("SSO");

            try
            {
                Client client = ClientBuilder.newClient();
                Response res = client.target(url)
                                        .request("application/json")
                                        .header(HttpHeaders.AUTHORIZATION, token)
                                        .post(null);
                // Comprobamos el estatus de respuesta.
                if( (int) res.getStatus() != HttpServletResponse.SC_OK ){
                    throw new Exception("El token de sesion proporcionado no es valido.");
                }
            }catch(NotAuthorizedException e){
                throw new NotAuthorizedException("No se pudo validar el token. Mensaje: "+ e.getMessage());
            }
        }
    }
    public Boolean userAllowed() {
        Boolean permiso = true;
        return permiso;
    }
}
