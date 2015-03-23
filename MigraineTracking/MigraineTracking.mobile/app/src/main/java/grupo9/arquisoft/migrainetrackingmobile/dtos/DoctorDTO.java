/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo9.arquisoft.migrainetrackingmobile.dtos;

import java.util.ArrayList;
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
     * La lista de pacientes
     */
    private List<PacienteDTO> pacientes;
    
    /**
     * La especialidad del doctor
     * (e.g. neurologia, medicina general)
     */
    private String especialidad;

    //-----------------------------------------------------
    // Constructores
    //-----------------------------------------------------
    
    /**
     * Constructor de la clase (con argumentos)
     * Se inicializan las listas de pacientes y de colegas
     */
    public DoctorDTO() {
        pacientes = new ArrayList<PacienteDTO>();
    }

    /**
     * Constructor de la clase(Con todos los argumentos)
     * @param nombre
     * @param noIdentificacion
     * @param fechaNacimiento
     * @param especialidad 
     */
    public DoctorDTO(String nombre, int noIdentificacion, Date fechaNacimiento, String especialidad) {
        this.nombre = nombre;
        this.noIdentificacion=noIdentificacion;
        this.fechaNacimiento=fechaNacimiento;
        this.especialidad = especialidad;
        pacientes = new ArrayList<PacienteDTO>();    
    }

    //-----------------------------------------------------
    // Metodos
    //-----------------------------------------------------


    
    /**
     * Metodo que retorna el nombre del doctor
     * @return el nombre del doctor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que cambia el nombre del doctor por el nombre que se da como parametro
     * @param nombre el nuevo nombre del doctor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que retrona el numero de identificacion del doctor
     * @return el numero de identificacion del doctor
     */
    public int getNoIdentificacion() {
        return noIdentificacion;
    }

    /**
     * Metodo que cambia el numero de identificacion del doctor por el numero que se da por parametro
     * @param noIdentificacion 
     */
    public void setNoIdentificacion(int noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    /**
     * Metodo que retorna la fecha de nacimiento del doctor
     * @return la fecha de nacimiento del doctor
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Metodo que cambia la fecha de nacimiento del doctor por la fecha dada por parametro
     * @param fechaNacimiento 
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    /**
     * Retorna la lista de pacientes asociados al doctor
     * @return la lista de pacientes
     */
    public List<PacienteDTO> getPacientes() {
        return pacientes;
    }

    /**
     * Modifica la lista de pacientes asociados al doctor
     * @param pacientes la lista de pacientes
     */
    public void setPacientes(List<PacienteDTO> pacientes) {
        this.pacientes = pacientes;
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
    
    /**
     * Metodo para comparar el doctor con otros objetos
     * @param obj el objeto con el que se va a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DoctorDTO other = (DoctorDTO) obj;
        if (this.noIdentificacion != other.noIdentificacion) {
            return false;
        }
        return true;
    }
}