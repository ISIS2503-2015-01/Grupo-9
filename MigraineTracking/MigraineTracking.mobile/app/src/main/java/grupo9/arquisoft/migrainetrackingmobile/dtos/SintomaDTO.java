/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo9.arquisoft.migrainetrackingmobile.dtos;

/**
 *
 * @author estudiante
 */
public class SintomaDTO {
    
    //-----------------------------------------------------
    // Atributos
    //-----------------------------------------------------
    
    /**
     * La localizacion del sintoma
     */
    private String especificacion;

    
    //-----------------------------------------------------
    // Constructor
    //-----------------------------------------------------

    /**
     * Metodo constructor con especificacion
     * @param esp
     */
    public SintomaDTO(String esp) {
       especificacion=esp;
    }

    /**
     * Constructor sin argumentos
     */
    public SintomaDTO() {
    }

    //--------------------------
    //GETTERS AND SETTERS
    //---------------------------

    /**
     * Metodo que cambia la especificacion del sintoma
     * @param especificacion
     */
    public void setEspecificacion(String especificacion){this.especificacion=especificacion;}

    /**
     * Metodo que retorna la especificacion del sintoma
     * @return la especificacion del sintoma
     */
    public String getEspecificacion(){return especificacion;}
}