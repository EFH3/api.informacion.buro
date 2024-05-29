
package com.coppel.services;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;

/**
 * Esta clase registra todas las rutas (clases Service) y los filtros de
 * la aplicación. No modificar.
 * @author ${user}
 */
@ApplicationPath("api")
public class Main extends ResourceConfig {
    
    public Main() {
        packages("com.coppel.services", "com.coppel.coppelframework.filters", "com.coppel.filters");
        //Se agrega el filtro para comprimir con gzip.
        packages("org.codingpedia.demo.rest");
        register(EntityFilteringFeature.class);
	EncodingFilter.enableFor(this, GZipEncoder.class);
    }
    
}
