/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppel.utils;

import java.text.Normalizer;

/**
 * Clase de utilerias generales de uso común.
 * @author smatty
 */
public class UtileriasGenerales 
{
    
    /**
     * Método que permite eliminar el caracter que se le envie en una cadena en especifico.
     * @param s_cadena cadena que se usara de base
     * @param s_caracteres caracteres que se van a quitar de s_cadena.
     * @return
     */
    public static String eliminaCaracteres(String s_cadena, String s_caracteres)
    {
      String nueva_cadena = "";
      Character caracter = null;
      boolean valido = true;

      /* Va recorriendo la cadena s_cadena y copia a la cadena que va a regresar,
         sólo los caracteres que no estén en la cadena s_caracteres */
      for (int i=0; i<s_cadena.length(); i++)
          {
           valido = true;
           for (int j=0; j<s_caracteres.length(); j++)
               {
                caracter = s_caracteres.charAt(j);

                if (s_cadena.charAt(i) == caracter)
                   {
                    valido = false;
                    break;
                   }
               }
           if (valido)
               nueva_cadena += s_cadena.charAt(i);
          }

      return nueva_cadena;
    }
    
    /**
     * Método que permite hacer mayusculas las palabras con acentos.
     * @param cadena cadena con acentos.
     * @return 
     */
    public static String limpiarAcentos(String cadena) {
        String limpio =null;
        if (cadena !=null) {
            String valor = cadena;
            valor = valor.toUpperCase();
            // Normalizar texto para eliminar acentos, dieresis, cedillas y tildes
            limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
            // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, 
            // exclamacion que abre, grados, U con dieresis.
            limpio = limpio
                    //.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", 
                    //        "");
                    .replaceAll("[^\\p{ASCII}]", "");
            // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
            limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
        }
        return limpio;
    }
    
    /**
     * Método que permite quitar los acentos de una cadena de texto.
     * @param str
     * @return 
     */
    public static String eliminarAcentos(String str) {
 
        final String ORIGINAL = "ÁáÉéÍíÓóÚúÑñÜü";
        final String REEMPLAZO = "AaEeIiOoUuNnUu";
 
        if (str == null) {
            return null;
        }
        char[] array = str.toCharArray();
        for (int indice = 0; indice < array.length; indice++) {
            int pos = ORIGINAL.indexOf(array[indice]);
            if (pos > -1) {
                array[indice] = REEMPLAZO.charAt(pos);
            }
        }
        return new String(array).replaceAll("[^\\p{ASCII}]", "");
    }
}

