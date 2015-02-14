/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class Doctor extends Usuario{
    
    //-----------------------------------------------------
    // Atributos
    //-----------------------------------------------------
    
    /**
     * La lista de pacientes
     */
    private List<Paciente> pacientes;
    
    /**
     * La lista de colegas que pueden brindar una segunda opinión
     */
    private List<Doctor> colegas;
    
    /**
     * La especialidad del doctor
     */
    private String especialidad;

    //-----------------------------------------------------
    // Constructores
    //-----------------------------------------------------
    
    public Doctor(String nombre, int noIdentificacion, Date fechaNacimiento) {
        super(nombre, noIdentificacion, fechaNacimiento);
        pacientes = new ArrayList<Paciente>();
        colegas = new ArrayList<Doctor>();
    }

    public Doctor(String nombre, int noIdentificacion, Date fechaNacimiento, List<Paciente> pacientes, List<Doctor> colegas, String especialidad) {
        super(nombre, noIdentificacion, fechaNacimiento);
        this.pacientes = pacientes;
        this.colegas = colegas;
        this.especialidad = especialidad;
    }

    //-----------------------------------------------------
    // Metodos
    //-----------------------------------------------------
    
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Doctor> getColegas() {
        return colegas;
    }

    public void setColegas(List<Doctor> colegas) {
        this.colegas = colegas;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
    
    
    
}
