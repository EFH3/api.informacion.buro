/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.entities;

import com.coppel.utils.ManejadorFechas;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author amilcar.trujillo
 */
@XmlRootElement
public class MaeInfoBuroCliente implements Serializable{

private long num_infoburocredito;
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="PST")
private Date fec_movimiento;
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="PST")
private Date fec_actuliazacioncliente;
private int num_cliente;
private long num_solicitud;
private String clv_tipoconsulta;
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="PST")
private Date fec_consulta;
private String des_mensaje;
private int num_longitudtramaburo;
private String clv_grupohit1;
private String clv_grupohit2;
private String clv_grupohit3;
private String fec_grupohit;
private short num_grupohit;
private short num_rstotalcuentasmopactual;
private int num_rslimitecreditocuentasactivas;
private int num_rssaldovencidocreditocuentasactivas;
private short num_rsporcentajelimitecreditocuentasactivas;
private short num_valorscore;

public MaeInfoBuroCliente( int numeroCliente ) {
        this.num_infoburocredito = -3;
        this.fec_movimiento = ManejadorFechas.deStringToDate("01-01-1900");
        this.fec_actuliazacioncliente = ManejadorFechas.deStringToDate("01-01-1900");
        this.num_cliente = numeroCliente;
        this.num_solicitud = 0;
        this.clv_tipoconsulta = "";
        this.fec_consulta = ManejadorFechas.deStringToDate("01-01-1900");
        this.des_mensaje = "Cliente sin información registrada.";
        this.num_longitudtramaburo = 0;
        this.clv_grupohit1 = "";
        this.clv_grupohit2 = "";
        this.clv_grupohit3 = "";
        this.fec_grupohit = "";
        this.num_grupohit = 0;
        this.num_rstotalcuentasmopactual = 0;
        this.num_rslimitecreditocuentasactivas = 0;
        this.num_rssaldovencidocreditocuentasactivas = 0;
        this.num_rsporcentajelimitecreditocuentasactivas = 0;
        this.num_valorscore = -100;
    }
        
    public MaeInfoBuroCliente(int numerror, int numeroCliente,String mensajeError) {
        this.num_infoburocredito = numerror;
        this.fec_movimiento = ManejadorFechas.deStringToDate("01-01-1900");
        this.fec_actuliazacioncliente = ManejadorFechas.deStringToDate("01-01-1900");
        this.num_cliente = numeroCliente;
        this.num_solicitud = 0;
        this.clv_tipoconsulta = "";
        this.fec_consulta =ManejadorFechas.deStringToDate("01-01-1900");
        this.des_mensaje = mensajeError;
        this.num_longitudtramaburo = 0;
        this.clv_grupohit1 = "";
        this.clv_grupohit2 = "";
        this.clv_grupohit3 = "";
        this.fec_grupohit = "";
        this.num_grupohit = 0;
        this.num_rstotalcuentasmopactual = 0;
        this.num_rslimitecreditocuentasactivas = 0;
        this.num_rssaldovencidocreditocuentasactivas = 0;
        this.num_rsporcentajelimitecreditocuentasactivas = 0;
        this.num_valorscore = -100;
    }
        
    public long getNum_infoburocredito() {
        return num_infoburocredito;
    }

    public void setNum_infoburocredito(long num_infoburocredito) {
        this.num_infoburocredito = num_infoburocredito;
    }

    public Date getFec_movimiento() {
        return fec_movimiento;
    }

    public void setFec_movimiento(Date fec_movimiento) {
        this.fec_movimiento = fec_movimiento;
    }

    public Date getFec_actuliazacioncliente() {
        return fec_actuliazacioncliente;
    }

    public void setFec_actuliazacioncliente(Date fec_actuliazacioncliente) {
        this.fec_actuliazacioncliente = fec_actuliazacioncliente;
    }

    public int getNum_cliente() {
        return num_cliente;
    }

    public void setNum_cliente(int num_cliente) {
        this.num_cliente = num_cliente;
    }

    public long getNum_solicitud() {
        return num_solicitud;
    }

    public void setNum_solicitud(long num_solicitud) {
        this.num_solicitud = num_solicitud;
    }

    public String getClv_tipoconsulta() {
        return clv_tipoconsulta;
    }

    public void setClv_tipoconsulta(String clv_tipoconsulta) {
        this.clv_tipoconsulta = clv_tipoconsulta;
    }

    public Date getFec_consulta() {
        return fec_consulta;
    }

    public void setFec_consulta(Date fec_consulta) {
        this.fec_consulta = fec_consulta;
    }

    public String getDes_mensaje() {
        return des_mensaje;
    }

    public void setDes_mensaje(String des_mensaje) {
        this.des_mensaje = des_mensaje;
    }

    public int getNum_longitudtramaburo() {
        return num_longitudtramaburo;
    }


    public void setNum_longitudtramaburo(int num_longitudtramaburo) {
        this.num_longitudtramaburo = num_longitudtramaburo;
    }

    public String getClv_grupohit1() {
        return clv_grupohit1;
    }

    public void setClv_grupohit1(String clv_grupohit1) {
        this.clv_grupohit1 = clv_grupohit1;
    }

    public String getClv_grupohit2() {
        return clv_grupohit2;
    }

    public void setClv_grupohit2(String clv_grupohit2) {
        this.clv_grupohit2 = clv_grupohit2;
    }

    public String getClv_grupohit3() {
        return clv_grupohit3;
    }

    public void setClv_grupohit3(String clv_grupohit3) {
        this.clv_grupohit3 = clv_grupohit3;
    }

    public String getFec_grupohit() {
        return fec_grupohit;
    }

    public void setFec_grupohit(String fec_grupohit) {
        this.fec_grupohit = fec_grupohit;
    }

    public short getNum_grupohit() {
        return num_grupohit;
    }

    public void setNum_grupohit(short num_grupohit) {
        this.num_grupohit = num_grupohit;
    }

    public short getNum_rstotalcuentasmopactual() {
        return num_rstotalcuentasmopactual;
    }

    public void setNum_rstotalcuentasmopactual(short num_rstotalcuentasmopactual) {
        this.num_rstotalcuentasmopactual = num_rstotalcuentasmopactual;
    }

    public int getNum_rslimitecreditocuentasactivas() {
        return num_rslimitecreditocuentasactivas;
    }

    public void setNum_rslimitecreditocuentasactivas(int num_rslimitecreditocuentasactivas) {
        this.num_rslimitecreditocuentasactivas = num_rslimitecreditocuentasactivas;
    }

    public int getNum_rssaldovencidocreditocuentasactivas() {
        return num_rssaldovencidocreditocuentasactivas;
    }

    public void setNum_rssaldovencidocreditocuentasactivas(int num_rssaldovencidocreditocuentasactivas) {
        this.num_rssaldovencidocreditocuentasactivas = num_rssaldovencidocreditocuentasactivas;
    }

    public short getNum_rsporcentajelimitecreditocuentasactivas() {
        return num_rsporcentajelimitecreditocuentasactivas;
    }

    public void setNum_rsporcentajelimitecreditocuentasactivas(short num_rsporcentajelimitecreditocuentasactivas) {
        this.num_rsporcentajelimitecreditocuentasactivas = num_rsporcentajelimitecreditocuentasactivas;
    }

    public short getNum_valorscore() {
        return num_valorscore;
    }

    public void setNum_valorscore(short num_valorscore) {
        this.num_valorscore = num_valorscore;
    }
}
