/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


/**
 *
 * @author estudiante
 */
@Entity
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;    
     /**
     * El nombre del doctor
     */
    private String nombre;
    
    /**
     * El número de identificación del doctor
     */
    @Id
    private int noIdentificacion; // pending non-clustered index
    
    /**
     * Fecha de Nacimiento del Usuario
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    /**
     * La lista de pacientes
     */
    
    @OneToMany(orphanRemoval = false)
    private List<Paciente> pacientes;
    
    /**
     * La especialidad del doctor
     * (e.g. neurologia, medicina general)
     */
    private String especialidad;
    
    /**
     * La contraseña del doctor
     */
    private String contrasenia;
    
    //--------------------------------
    //Constructor
    //--------------------------------
    
    /**
     * Metodo constructor sin argumentos
     */
    public Doctor(){
    
    }
    
    //--------------------------------------------------------------------------
    // METODOS
    //--------------------------------------------------------------------------
    

    /**
     * Metodo que retorna el nombre del doctor
     * @return el nombre del doctor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que cambia el nombre del doctor por el nombre dado por parametro
     * @param nombre el nuevo nombre del doctor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que retorna el numero de identificacion del doctor
     * @return el numero de indentificacion del doctor
     */
    public int getNoIdentificacion() {
        return noIdentificacion;
    }

    /**
     * Metodo que cambia el numero de identificacion por el numero dado por parametro
     * @param noIdentificacion el nuevo numero de identificacion del doctor
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
     * Metodo que cambia la fecha de nacimiento por la fecha dada por parametro
     * @param fechaNacimiento la nueva fecha de nacimiento del doctor
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Metodo que retorna los paciente que atiende el doctor
     * @return la lista de paciente que atiende el doctor
     */
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    /**
     * Metodo que cambia la lista de pacientes que atiende el doctor
     * @param pacientes la nueva lista de pacientes que atiende el doctor
     */
    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    /**
     * Metodo que retorna la especialidad del doctor
     * @return la especialidad del doctor
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Metodo que cambia la especialidad del doctor
     * @param especialidad la nueva epacialdad del doctor
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
        /**
     * Devuelve la contraseña del doctor
     * @return la contraseña
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Modifica la contraseña del doctor
     * @param contrasenia
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Metodo para comparar el doctor actual con otro objeto
     * @param object el objeto con el cual se va a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        if ( this.noIdentificacion != other.noIdentificacion ) {
            return false;
        }
        return true;
    }
}
