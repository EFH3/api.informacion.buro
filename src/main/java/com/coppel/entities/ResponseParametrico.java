/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.entities;

import com.coppel.utils.ManejadorFechas;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amilcar.trujillo
 */
@XmlRootElement
public class ResponseParametrico {
    private long num_infoburocredito;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="PST")
    private Date fec_consulta;
    private short num_grupohit;
    private short num_rstotalcuentasmopactual;
    private int num_rslimitecreditocuentasactivas;
    private int num_rssaldovencidocreditocuentasactivas;
    private short num_rsporcentajelimitecreditocuentasactivas;
    private short num_valorscore;
    private String mensaje;
    
    public ResponseParametrico(int numerror, String mensajeError) {
        this.num_infoburocredito = numerror;
        this.fec_consulta = ManejadorFechas.deStringToDate("01-01-1900");
        this.num_grupohit = 0;
        this.num_rstotalcuentasmopactual = -100;
        this.num_rslimitecreditocuentasactivas = -100;
        this.num_rssaldovencidocreditocuentasactivas = -100;
        this.num_rsporcentajelimitecreditocuentasactivas = -100;
        this.num_valorscore = -100;
        mensaje = mensajeError;
    }

    public ResponseParametrico() {
        this.num_infoburocredito = -3;
        this.fec_consulta = ManejadorFechas.deStringToDate("01-01-1900");
        this.num_grupohit = 0;
        this.num_rstotalcuentasmopactual = -100;
        this.num_rslimitecreditocuentasactivas = -100;
        this.num_rssaldovencidocreditocuentasactivas = -100;
        this.num_rsporcentajelimitecreditocuentasactivas = -100;
        this.num_valorscore = -100;
        this.mensaje = "Cliente sin información registrada.";
    }
    
    /**
     * @return the num_infoburocredito
     */
    public long getNum_infoburocredito() {
        return num_infoburocredito;
    }

    /**
     * @param num_infoburocredito the num_infoburocredito to set
     */
    public void setNum_infoburocredito(long num_infoburocredito) {
        this.num_infoburocredito = num_infoburocredito;
    }

    /**
     * @return the fec_consulta
     */
    public Date getFec_consulta() {
        return fec_consulta;
    }

    /**
     * @param fec_consulta the fec_consulta to set
     */
    public void setFec_consulta(Date fec_consulta) {
        this.fec_consulta = fec_consulta;
    }

    /**
     * @return the num_grupohit
     */
    public short getNum_grupohit() {
        return num_grupohit;
    }

    /**
     * @param num_grupohit the num_grupohit to set
     */
    public void setNum_grupohit(short num_grupohit) {
        this.num_grupohit = num_grupohit;
    }

    /**
     * @return the num_rstotalcuentasmopactual
     */
    public short getNum_rstotalcuentasmopactual() {
        return num_rstotalcuentasmopactual;
    }

    /**
     * @param num_rstotalcuentasmopactual the num_rstotalcuentasmopactual to set
     */
    public void setNum_rstotalcuentasmopactual(short num_rstotalcuentasmopactual) {
        this.num_rstotalcuentasmopactual = num_rstotalcuentasmopactual;
    }

    /**
     * @return the num_rslimitecreditocuentasactivas
     */
    public int getNum_rslimitecreditocuentasactivas() {
        return num_rslimitecreditocuentasactivas;
    }

    /**
     * @param num_rslimitecreditocuentasactivas the num_rslimitecreditocuentasactivas to set
     */
    public void setNum_rslimitecreditocuentasactivas(int num_rslimitecreditocuentasactivas) {
        this.num_rslimitecreditocuentasactivas = num_rslimitecreditocuentasactivas;
    }

    /**
     * @return the num_rssaldovencidocreditocuentasactivas
     */
    public int getNum_rssaldovencidocreditocuentasactivas() {
        return num_rssaldovencidocreditocuentasactivas;
    }

    /**
     * @param num_rssaldovencidocreditocuentasactivas the num_rssaldovencidocreditocuentasactivas to set
     */
    public void setNum_rssaldovencidocreditocuentasactivas(int num_rssaldovencidocreditocuentasactivas) {
        this.num_rssaldovencidocreditocuentasactivas = num_rssaldovencidocreditocuentasactivas;
    }

    /**
     * @return the num_rsporcentajelimitecreditocuentasactivas
     */
    public short getNum_rsporcentajelimitecreditocuentasactivas() {
        return num_rsporcentajelimitecreditocuentasactivas;
    }

    /**
     * @param num_rsporcentajelimitecreditocuentasactivas the num_rsporcentajelimitecreditocuentasactivas to set
     */
    public void setNum_rsporcentajelimitecreditocuentasactivas(short num_rsporcentajelimitecreditocuentasactivas) {
        this.num_rsporcentajelimitecreditocuentasactivas = num_rsporcentajelimitecreditocuentasactivas;
    }

    /**
     * @return the num_valorscore
     */
    public short getNum_valorscore() {
        return num_valorscore;
    }

    /**
     * @param num_valorscore the num_valorscore to set
     */
    public void setNum_valorscore(short num_valorscore) {
        this.num_valorscore = num_valorscore;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
