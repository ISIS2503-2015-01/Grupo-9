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
public class PacienteDTO {

    //------------------------------
    //Atributos
    //-----------------------------

    /**
     * La cedula del paciente
     */
    private Long cedula;

    /**
     * El nombre de usuario del paciente
     */
    private String username;

    /**
     * La clave del paciente
     */
    private String password;

    /**
     * El nombre del paciente
     */
    private String name;

    /**
     * La fecha de nacimientos del paciente
     */
    private Long birthdate;

    /**
     * El id del doctor que atiende el paciente
     */
    private Long doctorid; // Solo se usa para agregar el paciente

    //---------------
    //Constructor
    //----------------------

    /**
     * Metodo constructor sin argumentos
     */
    public PacienteDTO(){

    }

    //------------------------------
    //GETTERS AND SETTERS
    //------------------------------

    /**
     * Retorna la cedula del paciente
     * @return la cedula del paciente
     */
    public Long getCedula() {
        return cedula;
    }

    /**
     * Cambia la cedula del paciente
     * @param cedula la nueva cedula del paciente
     */
    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    /**
     * Retorna el nombre de usuario del paciente
     * @return el nombre de usuario del paciente
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metodo que cambia el nombre de usuario del paciente
     * @param username el nuevo nombre de usuario del paciente
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retorna la contrasenia del paciente
     * @return la contrasenia del paciente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Cambia la clave por la nueva dada por parametro
     * @param password la nueva clave del paciente
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retorna el nombre del paciente
     * @return el nombre del paciente
     */
    public String getName() {
        return name;
    }

    /**
     * Cambia el nombre del paciente
     * @param name el nuevo nombre del paciente
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna la fecha de nacimiento del paciente
     * @return la fecha de nacimiento del paciente
     */
    public Long getBirthdate() {
        return birthdate;
    }

    /**
     * Cambia la fecha de nacimiento del paciente
     * @param birthdate la nueva fecha de nacimiento del paciente
     */
    public void setBirthdate(Long birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Retorna el id del paciente que lo atiende
     * @return el id del doctor que lo atiende
     */
    public Long getDoctorid() {
        return doctorid;
    }

    /**
     * Cambia el id del doctor que lo atiende
     * @param doctorid el doctor que lo atiende
     */
    public void setDoctorid(Long doctorid) {
        this.doctorid = doctorid;
    }
}