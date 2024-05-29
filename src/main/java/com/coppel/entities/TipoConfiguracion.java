/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.entities;

/**
 *
 * @author smatty
 */
public enum TipoConfiguracion {
    CONFIG_TIMPO_CACHE,
    CONFIG_CREAR_LOG_ENTRADAS,
    CONFIG_CONSULTAR_TOKEN,
    CONFIG_IS_MEMORY_TRANSACTION,
    CONFIG_CARTERALINEA_DEFAULT,
    CONFIG_CREAR_MENSAJE_ERROR;
    
    public int GetVal(){
        switch (this) {            
            case CONFIG_TIMPO_CACHE:
                return 1;
            case CONFIG_CREAR_LOG_ENTRADAS:
                return 2;
            case CONFIG_CONSULTAR_TOKEN:
                return 3;
            case CONFIG_IS_MEMORY_TRANSACTION:
                return 4;
            case CONFIG_CARTERALINEA_DEFAULT:
                return 5;
            case CONFIG_CREAR_MENSAJE_ERROR:
                return 8;
            default:
                return -1;
        }
    }
}

