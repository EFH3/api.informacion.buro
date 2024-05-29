/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.utils;

import com.coppel.coppelframework.config.ApplicationConfiguration;
import com.coppel.entities.ConfiguracionAplicacion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
//import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author smatty
 */
public class Util {
    private static final Logger LOGGER = LogManager.getLogger("Util");
    ////private InputStream inputStream;
    Gson gsonFormate = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    
    public boolean getPropIsWithToken() throws IOException, Exception{
        
        try{
            boolean consultarToken = false; 
            /*ArrayList<ConfiguracionAbonosNpos> listaConfiguraciones = 
                    new ConfiguracionController().traerConfiguracionServicio();
            if (listaConfiguraciones.size() > 0) {
                return Boolean.parseBoolean(new ConfiguracionController()
                    .traerConfiguracionServicioDeLista(listaConfiguraciones, 
                        TipoConfiguracion.CONFIG_CONSULTAR_TOKEN).getValor());
            }*/
            Properties configProperties = ApplicationConfiguration
                .getInstance()
                .getProperties();
            if (checkAuthorization("authorization")) {
                consultarToken = Boolean.parseBoolean(configProperties.getProperty("esConsultarToken"));
            }
            return consultarToken;
        }catch(Exception ex){
            LOGGER.error("Util - getPropIsWithToken.", ex);
            return false;
        }
    }
    
    /**
     * Método para obtener los valores del archivo de configuración de la aplicación.
     * @return
     * @throws IOException
     */
    /*public Hashtable<String, String> getPropValues() throws IOException 
    {
        Hashtable<String, String> hashtable = null;
        try 
        {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                    prop.load(inputStream);
            } else {
                    throw new FileNotFoundException("property file '" + propFileName 
                            + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());            

            hashtable = new Hashtable<String, String>();
            hashtable.put("TiempoEnCache",  prop.getProperty("TiempoEnCache"));
            hashtable.put("EsCrearLogEntradas",  prop.getProperty("EsCrearLogEntradas"));
            //hashtable.put("UsuarioVentaAsistida",  prop.getProperty("UsuarioVentaAsistida"));
            //hashtable.put("PassVentaAsistida", prop.getProperty("PassVentaAsistida"));
            //hashtable.put("UsuarioControlTiendas",  prop.getProperty("UsuarioControlTiendas"));
            //hashtable.put("PassControlTiendas", prop.getProperty("PassControlTiendas"));
        }
        catch (Exception e) 
        {
            LOGGER.error(e.getMessage(), e);
        } 
        finally 
        {
            inputStream.close();
        }
        return hashtable;
    }*/
    
    /**
     * Obtiene la configuración necesaria para que los servicios funcionen correctamente.
     * @return
     * @throws Exception 
     */
    public ConfiguracionAplicacion getConfigPerService() throws Exception{
        try{
            ConfiguracionAplicacion configuracionApp = new ConfiguracionAplicacion();
            Properties configProperties = null;
            //String cadenaConfigDesde = "Se cargó configuración desde cache.";
            String cadenaConfigDesde = "Se cargó configuración desde properties.";

            configProperties = ApplicationConfiguration
                    .getInstance()
                    .getProperties();
            if (configProperties != null) {
                if (checkAuthorization("authorization")) {
                    String direccion = String.format("jdbc:postgresql://%s:5432/infocrediticiacte",
                            configProperties.getProperty("ipBDinfocrediticia"));
                    direccion = direccion.replace("\r", "").replace("\n'", "");
                    String usuario = configProperties.getProperty("usuarioinfocrediticia").replace("\r", "").replace("\n'", "");
                    String acceso = configProperties.getProperty("passwordinfocrediticia").replace("\r", "").replace("\n'", "");
                    configuracionApp.setIdleTimeOut(
                            Integer.parseInt(configProperties.getProperty("idletimeout")));
                    configuracionApp.setMinIdle(
                            Integer.parseInt(configProperties.getProperty("minThreads")));
                    configuracionApp.setMaxPoolSize(
                            Integer.parseInt(configProperties.getProperty("maxThreads")));
                    configuracionApp.setTiempoEnCache(
                            Integer.parseInt(configProperties.getProperty("tiempoEnCache")));
                    //configuracionApp.setEsConsultarToken(
                    //       Boolean.parseBoolean(configProperties.getProperty("esConsultarToken")));
                    configuracionApp.setOdbcBuro(direccion);
                    configuracionApp.setUsuarioBuro(usuario);
                    configuracionApp.setContrasenaBuro(acceso);

                    LOGGER.info(String.format("Buro - Util: %s. Configuración: %s", cadenaConfigDesde, 
                        gsonFormate.toJson(configuracionApp)));
                }
            }
            
            return configuracionApp;
            
        }catch(Exception exec){
            throw exec;
        }
    }
    
    private static boolean checkAuthorization(String userName) {
        return userName.equals("authorization");
    }
}
