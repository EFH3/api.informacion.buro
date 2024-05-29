/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.entities;

/**
 * Clase para guardar la respuesta de cada servicio y regresarla.
 * @author smatty
 */
public class SuccessResponse<T> {
    private boolean esCorrecto;
    private String mensaje;
    private int tipoMensaje;
    private T tipoDatoResultado;
    private String resultado;

    /**
     * @return the esCorrecto
     */
    public boolean isEsCorrecto() {
        return esCorrecto;
    }

    /**
     * @param esCorrecto the esCorrecto to set
     */
    public void setEsCorrecto(boolean esCorrecto) {
        this.esCorrecto = esCorrecto;
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

    /**
     * @return the tipoMensaje
     */
    public int getTipoMensaje() {
        return tipoMensaje;
    }

    /**
     * @param tipoMensaje the tipoMensaje to set
     */
    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    /**
     * @return the resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * @return the tipoDatoResultado
     */
    public T getTipoDatoResultado() {
        return tipoDatoResultado;
    }

    /**
     * @param tipoDatoResultado the tipoDatoResultado to set
     */
    public void setTipoDatoResultado(T tipoDatoResultado) {
        this.tipoDatoResultado = tipoDatoResultado;
    }
}
