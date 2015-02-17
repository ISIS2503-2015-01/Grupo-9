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
public class Doctor {
    
    
   
    //-----------------------------------------------------
    // Atributos
    //-----------------------------------------------------
    
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
     * La lista de pacientes
     */
    private List<Paciente> pacientes;
    
    /**
     * La lista de colegas que pueden brindar una segunda opinión
     */
    private List<Doctor> colegas;
    
    /**
     * La especialidad del doctor
     * (e.g. neurologia, medicina general)
     */
    private String especialidad;

    //-----------------------------------------------------
    // Constructores
    //-----------------------------------------------------
    
    public Doctor(){}
    /**
     * Constructor de la clase
     * (sin argumentos de la clase doctor, pero con argumentos de la super clase Usuarios)
     * @param nombre
     * @param noIdentificacion
     * @param fechaNacimiento 
     */
    public Doctor(String nombre, int noIdentificacion, Date fechaNacimiento) {
        this.nombre = nombre;
        this.noIdentificacion=noIdentificacion;
        this.fechaNacimiento=fechaNacimiento;
        pacientes = new ArrayList<Paciente>();
        colegas = new ArrayList<Doctor>();
    }

    /**
     * Constructor de la clase(Con todos los argumentos)
     * @param nombre
     * @param noIdentificacion
     * @param fechaNacimiento
     * @param pacientes
     * @param colegas
     * @param especialidad 
     */
    public Doctor(String nombre, int noIdentificacion, Date fechaNacimiento, List<Paciente> pacientes, List<Doctor> colegas, String especialidad) {
        this.nombre = nombre;
        this.noIdentificacion=noIdentificacion;
        this.fechaNacimiento=fechaNacimiento;
        this.pacientes = pacientes;
        this.colegas = colegas;
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
    
    /**
     * Retorna la lista de pacientes asociados al doctor
     * @return la lista de pacientes
     */
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    /**
     * Modifica la lista de pacientes asociados al doctor
     * @param pacientes la lista de pacientes
     */
    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    /**
     * Devuelve la lista de colegas asociados al doctor para dar una segunda opinion
     * @return la lista de doctores
     */
    public List<Doctor> getColegas() {
        return colegas;
    }

    /**
     * Modifica la lista de colegas asociados al doctor
     * @param colegas la lista de doctores
     */
    public void setColegas(List<Doctor> colegas) {
        this.colegas = colegas;
    }

    /**
     * Devuelve la especialidad del doctor
     * @return la especialidad
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Modifica la especialidad del doctor
     * @param especialidad (e.g. neurologia)
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Doctor other = (Doctor) obj;
        if (this.noIdentificacion != other.noIdentificacion) {
            return false;
        }
        return true;
    }
    
    
}
