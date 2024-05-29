/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.entities;

import java.util.UUID;

/**
 * Clase que almacena la información de las entradas a los logs de servicios.
 * @author smatty
 */
public class LogAccesosServicios {
    private String ipCliente;
    private int numeroEmpleado;
    private String nombreServicio;
    private String urlAcceso;
    private String parametros;
    private int numeroCliente;
    private int numeroTienda;
    private int numeroCaja;
    private String area;
    private String uuid;
    
    public LogAccesosServicios()
    {
        this.ipCliente = "";
        this.numeroEmpleado = 0;
        this.nombreServicio = "";
        this.urlAcceso = "";
        this.parametros = "";
        this.numeroCliente = 0;
        this.numeroTienda = 0;
        this.numeroCaja = 0;
        this.area = "";
        this.uuid = UUID.randomUUID().toString().replace("-", "");
    }
    
    public LogAccesosServicios(String ipCliente, int numeroEmpleado, String nombreServicio, String urlAcceso, String parametros, int numeroCliente, int numeroTienda, int numeroCaja, String area)
    {
        this.ipCliente = ipCliente;
        this.numeroEmpleado = numeroEmpleado;
        this.nombreServicio = nombreServicio;
        this.urlAcceso = urlAcceso;
        this.parametros = parametros;
        this.numeroCliente = numeroCliente;
        this.numeroTienda = numeroTienda;
        this.numeroCaja = numeroCaja;
        this.area = area;
        this.uuid = UUID.randomUUID().toString().replace("-", "");
    }
    
    public LogAccesosServicios(String ipCliente, int numeroEmpleado, String nombreServicio, String urlAcceso, String parametros, int numeroTienda, int numeroCaja, String area, String uuid)
    {
        this.ipCliente = ipCliente;
        this.numeroEmpleado = numeroEmpleado;
        this.nombreServicio = nombreServicio;
        this.urlAcceso = urlAcceso;
        this.parametros = parametros;
        this.numeroTienda = numeroTienda;
        this.numeroCaja = numeroCaja;
        this.area = area;
        this.uuid = uuid;
    }

    /**
     * @return the ipCliente
     */
    public String getIpCliente() {
        return ipCliente;
    }

    /**
     * @param ipCliente the ipCliente to set
     */
    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }

    /**
     * @return the urlAcceso
     */
    public String getUrlAcceso() {
        return urlAcceso;
    }

    /**
     * @param urlAcceso the urlAcceso to set
     */
    public void setUrlAcceso(String urlAcceso) {
        this.urlAcceso = urlAcceso;
    }

    /**
     * @return the parametros
     */
    public String getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    /**
     * @return the numeroTienda
     */
    public int getNumeroTienda() {
        return numeroTienda;
    }

    /**
     * @param numeroTienda the numeroTienda to set
     */
    public void setNumeroTienda(int numeroTienda) {
        this.numeroTienda = numeroTienda;
    }

    /**
     * @return the numeroCaja
     */
    public int getNumeroCaja() {
        return numeroCaja;
    }

    /**
     * @param numeroCaja the numeroCaja to set
     */
    public void setNumeroCaja(int numeroCaja) {
        this.numeroCaja = numeroCaja;
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the numeroEmpleado
     */
    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    /**
     * @param numeroEmpleado the numeroEmpleado to set
     */
    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    /**
     * @return the nombreServicio
     */
    public String getNombreServicio() {
        return nombreServicio;
    }

    /**
     * @param nombreServicio the nombreServicio to set
     */
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the numeroCliente
     */
    public int getNumeroCliente() {
        return numeroCliente;
    }

    /**
     * @param numeroCliente the numeroCliente to set
     */
    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }
}

