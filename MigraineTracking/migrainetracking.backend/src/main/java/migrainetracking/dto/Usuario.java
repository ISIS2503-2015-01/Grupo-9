/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.dto;

import java.util.Date;

/**
 * Es una clase abstracta para encapsular el objeto de tipo Usuario
 * @author estudiante
 */
public abstract class Usuario {
    
    //-----------------------------------------------------
    // Atributos
    //-----------------------------------------------------
    
    /**
     * El nombre del doctor
     */
    private String nombre;
    
    /**
     * El número de identificación del doctor
     */
    private int noIdentificacion;
    
    /**
     * Fecha de Nacimiento del Usuario
     */
    private Date fechaNacimiento;

    //-----------------------------------------------------
    // Constructor
    //-----------------------------------------------------
    
    public Usuario(String nombre, int noIdentificacion, Date fechaNacimiento) {
        this.nombre = nombre;
        this.noIdentificacion = noIdentificacion;
        this.fechaNacimiento = fechaNacimiento;
    }
    

    //-----------------------------------------------------
    // Metodos
    //-----------------------------------------------------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(int noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
 
}
