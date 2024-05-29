/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.entities;

import java.util.Map;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author hugo
 */
public class Request {
    
    private MultivaluedMap<String,? > headers;
    private String body;
    private MultivaluedMap<String, String> queryParams;    
    private String method;

    public MultivaluedMap<String, ?> getHeaders() {
        return headers;
    }

    public void setHeaders(MultivaluedMap<String, ?> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MultivaluedMap<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(MultivaluedMap<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }   
}
