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

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Long birthdate) {
        this.birthdate = birthdate;
    }

    public Long getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Long doctorid) {
        this.doctorid = doctorid;
    }
}