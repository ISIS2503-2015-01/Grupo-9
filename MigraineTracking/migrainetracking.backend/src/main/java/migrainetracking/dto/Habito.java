/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.dto;

/**
 *
 * @author estudiante
 */
public class Habito {
    
    private String tipo;
    
    private String especificacion;
    
    /**
     * El habito es diario, semanal, mensual
     */
    private String frecuencia;

    public Habito() {
    }

    public Habito(String tipo, String especificacion, String frecuencia) {
        this.tipo = tipo;
        this.especificacion = especificacion;
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

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    
    
}
