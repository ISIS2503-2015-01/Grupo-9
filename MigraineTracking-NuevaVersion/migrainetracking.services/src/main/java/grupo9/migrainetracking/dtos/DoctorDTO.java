/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo9.migrainetracking.dtos;

import java.util.Date;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class DoctorDTO {
    
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

    /**
     * La especialidad del doctor
     * eg Neurologia, Medicia General
     */
    private String especialidad;
    
    //-----------------------------------------------------
    // Constructores
    //-----------------------------------------------------

    public DoctorDTO() {
    }

    public DoctorDTO(String nombre, int noIdentificacion, Date fechaNacimiento, String especialidad) {
        this.nombre = nombre;
        this.noIdentificacion = noIdentificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.especialidad = especialidad;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
}
