/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo9.migrainetracking.dtos;

import java.util.Date;

/**
 *
 * @author estudiante
 */
public class PacienteDTO {
    
    //-----------------------------------------------------
    // Atributos
    //-----------------------------------------------------
    
    
    /**
     * El nombre del paciente
     */
    private String nombre;
    
    /**
     * El número de identificación del paciente
     */
    private int noIdentificacion;
    
    /**
     * Fecha de Nacimiento del Usuario
     */
    private Date fechaNacimiento;

    /**
     * El peso del paciente
     */
    private int peso;
    
    /**
     * La estatura del paciente
     */
    private int estatura;
    
    //-----------------------------------------------------
    // Constructores
    //-----------------------------------------------------
    
    public PacienteDTO() {
    }
    
    public PacienteDTO(String nombre, int noIdentificacion, Date fechaNacimiento, int peso, int estatura) {
        this.nombre = nombre;
        this.noIdentificacion = noIdentificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.estatura = estatura;
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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }
    
    
}
