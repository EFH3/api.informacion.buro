/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.entities;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author hugo
 */
public class ErrorInfoException extends RuntimeException{
    private String ip;
    private Request request;
    private String url;
    private Context context;
    private String timestamp;
    private String message;
    private Error error;
    private Map<String,String> data;

    public ErrorInfoException() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                //No serializar los parametros de throwable ya que rompen el estandar
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return (f.getDeclaringClass() == Throwable.class && f.getName()
                                .equals("stackTrace")) ||
                                (f.getDeclaringClass() == Throwable.class && f.getName()
                                        .equals("suppressedExceptions"));

                    }
                    @Override
                    public boolean shouldSkipClass(Class<?> type) {
                        return false;
                    }
        }).create();
        return gson.toJson(this, ErrorInfoException.class);
    }
    
    
    public static class ErrorInfoBuilder {
        
        private ErrorInfoException errorInfo;
        //inyectado en cada peticion
        private static ThreadLocal<String> requestBody = new ThreadLocal<>();
        private static ThreadLocal<Context> serviceCalled = new ThreadLocal<>();
        
        public ErrorInfoBuilder() {
            this.errorInfo = new ErrorInfoException();
        }
        
        public ErrorInfoBuilder( ErrorInfoException errorInfo) {
            this.errorInfo = errorInfo;   
        }
        
        public ErrorInfoBuilder ip( String ip) {
            this.errorInfo.setIp(ip);
            return this;
        }
        
        public ErrorInfoBuilder request( Request request ) {
            this.errorInfo.request = request;
            return this;
        }
        
        public ErrorInfoBuilder request( UriInfo uriInfo, 
                HttpHeaders headers,HttpServletRequest request ) {
            if( this.errorInfo.request == null ) {
                this.errorInfo.request = new Request();                
            }
            this.errorInfo.request.setHeaders(headers.getRequestHeaders());
            this.errorInfo.request.setQueryParams(uriInfo.getQueryParameters());
            this.errorInfo.request.setMethod(request.getMethod());
            this.errorInfo.request.setBody(requestBody.get());
            this.errorInfo.url = uriInfo.getBaseUriBuilder()
                    .path(uriInfo.getPath())
                    .build().toString();
            return this;
        }
        
        public ErrorInfoBuilder url( String url ) {
            this.errorInfo.url = url;
            return this;
        }
        
        public ErrorInfoBuilder message( String message ) {
            this.errorInfo.message = message;
            return this;
        }
        
        
        public ErrorInfoBuilder error( Throwable ex ) {
            if( this.errorInfo.error == null ) {
                this.errorInfo.error = new Error();
            }            
            this.errorInfo.error.setMessage( ex.getMessage() );
            List<String> stack = new ArrayList<>();
            
            for( StackTraceElement trace : ex.getStackTrace()) {
                stack.add(trace.toString());
            }
            this.errorInfo.error.setStack(stack);
            return this;
        }
        
        public ErrorInfoBuilder context( Context context ) {
            this.errorInfo.context = context;
            return this;            
        }
        
        public ErrorInfoBuilder addData( String key, String value ) {
            if( this.errorInfo.data == null ) {
                this.errorInfo.data = new HashMap<>();
            }
            this.errorInfo.data.put(key, value);
            return this;
        }
        
        public  ErrorInfoException build() {
            this.errorInfo.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                    .format(new Date());
            if( this.errorInfo.getContext() == null ) {
                this.context(serviceCalled.get());
            }
            return this.errorInfo;
        }
        
        
        public static void setRequestBody( String body ) {
            requestBody.set(body);
        }    
        
        public static void setServiceCalled( Context context) {
            serviceCalled.set(context);
        }
        
        
    }
    
}
