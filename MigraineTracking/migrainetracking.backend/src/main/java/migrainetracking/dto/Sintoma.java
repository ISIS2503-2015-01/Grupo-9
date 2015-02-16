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
public class Sintoma {
    
    private String localizacion;
    
    private String nombre;
    
    public Sintoma(String localizacion, String nombre, int intensidad) {
        this.localizacion = localizacion;
        this.nombre = nombre;
       
    }

    public Sintoma() {
    }
    
    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
