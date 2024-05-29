/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hever.fierro Se crea nueva clase a la capa entidad para agregar las
 * 16 variables nuevas de buro
 */
@XmlRootElement
public class MaeInfoModeloPrestamoPersonal {

    private long num_infoburocredito;
    private short num_consultasndiashitsininformacion;
    private short num_consultasndiashitconinformacion;
    private short num_consultasprestamos;
    private short num_direccionesreportadas;
    private short num_mopreciente;
    private int num_saldomopreciente;
    private short num_mesesmopreciente;
    private short num_tarjetascredito;
    private short num_peormop;
    private short num_porcentajeusocuentasabiertas;
    private short num_duracionmaximacuenta;
    private short num_maximoplazopagofijo;
    private short num_mesespromedioaperturatarjetacredito;
    private short num_promedioporcentajecomportamientocorriente;
    private short num_mesespromediocomportamientocuenta;
    private short num_cuentasconinformacion;
    private String mensaje;

    public MaeInfoModeloPrestamoPersonal() {
        this.num_infoburocredito = -3;
        this.num_consultasndiashitsininformacion = 0;
        this.num_consultasndiashitconinformacion = 0;
        this.num_consultasprestamos = 0;
        this.num_direccionesreportadas = 0;
        this.num_mopreciente = 0;
        this.num_saldomopreciente = 0;
        this.num_mesesmopreciente = 0;
        this.num_tarjetascredito = 0;
        this.num_peormop = 0;
        this.num_porcentajeusocuentasabiertas = 0;
        this.num_duracionmaximacuenta = 0;
        this.num_maximoplazopagofijo = 0;
        this.num_mesespromedioaperturatarjetacredito = 0;
        this.num_promedioporcentajecomportamientocorriente = 0;
        this.num_mesespromediocomportamientocuenta = 0;
        this.num_cuentasconinformacion = 0;
        this.mensaje = "Cliente sin información registrada.";
    }

    public MaeInfoModeloPrestamoPersonal(int numerror, String mensajeError) {
        this.num_infoburocredito = numerror;
        this.num_consultasndiashitsininformacion = 0;
        this.num_consultasndiashitconinformacion = 0;
        this.num_consultasprestamos = 0;
        this.num_direccionesreportadas = 0;
        this.num_mopreciente = 0;
        this.num_saldomopreciente = 0;
        this.num_mesesmopreciente = 0;
        this.num_tarjetascredito = 0;
        this.num_peormop = 0;
        this.num_porcentajeusocuentasabiertas = 0;
        this.num_duracionmaximacuenta = 0;
        this.num_maximoplazopagofijo = 0;
        this.num_mesespromedioaperturatarjetacredito = 0;
        this.num_promedioporcentajecomportamientocorriente = 0;
        this.num_mesespromediocomportamientocuenta = 0;
        this.num_cuentasconinformacion = 0;
        mensaje = mensajeError;
    }

    public long getNum_infoburocredito() {
        return num_infoburocredito;
    }

    public void setNum_infoburocredito(long num_infoburocredito) {
        this.num_infoburocredito = num_infoburocredito;
    }

    public short getNum_consultasndiashitsininformacion() {
        return num_consultasndiashitsininformacion;
    }

    public void setNum_consultasndiashitsininformacion(short num_consultasndiashitsininformacion) {
        this.num_consultasndiashitsininformacion = num_consultasndiashitsininformacion;
    }

    public short getNum_consultasndiashitconinformacion() {
        return num_consultasndiashitconinformacion;
    }

    public void setNum_consultasndiashitconinformacion(short num_consultasndiashitconinformacion) {
        this.num_consultasndiashitconinformacion = num_consultasndiashitconinformacion;
    }

    public short getNum_consultasprestamos() {
        return num_consultasprestamos;
    }

    public void setNum_consultasprestamos(short num_consultasprestamos) {
        this.num_consultasprestamos = num_consultasprestamos;
    }

    public short getNum_direccionesreportadas() {
        return num_direccionesreportadas;
    }

    public void setNum_direccionesreportadas(short num_direccionesreportadas) {
        this.num_direccionesreportadas = num_direccionesreportadas;
    }

    public short getNum_mopreciente() {
        return num_mopreciente;
    }

    public void setNum_mopreciente(short num_mopreciente) {
        this.num_mopreciente = num_mopreciente;
    }

    public int getNum_saldomopreciente() {
        return num_saldomopreciente;
    }

    public void setNum_saldomopreciente(int num_saldomopreciente) {
        this.num_saldomopreciente = num_saldomopreciente;
    }

    public short getNum_mesesmopreciente() {
        return num_mesesmopreciente;
    }

    public void setNum_mesesmopreciente(short num_mesesmopreciente) {
        this.num_mesesmopreciente = num_mesesmopreciente;
    }

    public short getNum_tarjetascredito() {
        return num_tarjetascredito;
    }

    public void setNum_tarjetascredito(short num_tarjetascredito) {
        this.num_tarjetascredito = num_tarjetascredito;
    }

    public short getNum_peormop() {
        return num_peormop;
    }

    public void setNum_peormop(short num_peormop) {
        this.num_peormop = num_peormop;
    }

    public short getNum_porcentajeusocuentasabiertas() {
        return num_porcentajeusocuentasabiertas;
    }

    public void setNum_porcentajeusocuentasabiertas(short num_porcentajeusocuentasabiertas) {
        this.num_porcentajeusocuentasabiertas = num_porcentajeusocuentasabiertas;
    }

    public short getNum_duracionmaximacuenta() {
        return num_duracionmaximacuenta;
    }

    public void setNum_duracionmaximacuenta(short num_duracionmaximacuenta) {
        this.num_duracionmaximacuenta = num_duracionmaximacuenta;
    }

    public short getNum_maximoplazopagofijo() {
        return num_maximoplazopagofijo;
    }

    public void setNum_maximoplazopagofijo(short num_maximoplazopagofijo) {
        this.num_maximoplazopagofijo = num_maximoplazopagofijo;
    }

    public short getNum_mesespromedioaperturatarjetacredito() {
        return num_mesespromedioaperturatarjetacredito;
    }

    public void setNum_mesespromedioaperturatarjetacredito(short num_mesespromedioaperturatarjetacredito) {
        this.num_mesespromedioaperturatarjetacredito = num_mesespromedioaperturatarjetacredito;
    }

    public short getNum_promedioporcentajecomportamientocorriente() {
        return num_promedioporcentajecomportamientocorriente;
    }

    public void setNum_promedioporcentajecomportamientocorriente(short num_promedioporcentajecomportamientocorriente) {
        this.num_promedioporcentajecomportamientocorriente = num_promedioporcentajecomportamientocorriente;
    }

    public short getNum_mesespromediocomportamientocuenta() {
        return num_mesespromediocomportamientocuenta;
    }

    public void setNum_mesespromediocomportamientocuenta(short num_mesespromediocomportamientocuenta) {
        this.num_mesespromediocomportamientocuenta = num_mesespromediocomportamientocuenta;
    }

    public short getNum_cuentasconinformacion() {
        return num_cuentasconinformacion;
    }

    public void setNum_cuentasconinformacion(short num_cuentasconinformacion) {
        this.num_cuentasconinformacion = num_cuentasconinformacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
