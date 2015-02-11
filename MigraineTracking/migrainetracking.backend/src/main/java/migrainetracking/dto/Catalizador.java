/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.dto;

import java.util.Date;

/**
 *
 * @author estudiante
 */
public class Catalizador {
    
    private String tipo;
    
    private String especificacion;
    
    private Date ultimaFecha;
    
    private String ultimaHora;
    
    /**
     * La cantidad de veces que se ha hecho o ingerido el catalizador
     */
    private int frecuencia;

    public Catalizador() {
    }

    public Catalizador(String tipo, String especificacion, Date ultimaFecha, String ultimaHora, int frecuencia) {
        this.tipo = tipo;
        this.especificacion = especificacion;
        this.ultimaFecha = ultimaFecha;
        this.ultimaHora = ultimaHora;
        this.frecuencia = frecuencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    public Date getUltimaFecha() {
        return ultimaFecha;
    }

    public void setUltimaFecha(Date ultimaFecha) {
        this.ultimaFecha = ultimaFecha;
    }

    public String getUltimaHora() {
        return ultimaHora;
    }

    public void setUltimaHora(String ultimaHora) {
        this.ultimaHora = ultimaHora;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    
    
}
