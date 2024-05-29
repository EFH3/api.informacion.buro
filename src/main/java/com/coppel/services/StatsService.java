
package com.coppel.services;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import org.eclipse.jetty.server.handler.StatisticsHandler;

/**
 *
 * @author emmanuel
 */
@Path("stats")
public class StatsService {
    
    @Context StatisticsHandler sh;
    
    @GET
    public String getStats() {
        this.sh = new StatisticsHandler();
        final Gson gson = new Gson();
        Map<String, String> stats = new HashMap<>();
        stats.put("numeroPeticiones", String.valueOf(this.sh.getRequests()));
        return gson.toJson(stats);
    }
    
}
