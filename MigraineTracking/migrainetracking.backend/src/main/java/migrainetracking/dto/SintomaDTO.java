/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.dto;

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
    private String localizacion;
    
    /**
     * El nombre del sintoma
     */
    private String nombre;
    
    /**
     * La intensidad del dolor del sintoma
     */
    private int intensidad;
    
    /**
     * El id del sintoma
     */
    private Long id;
    
    //-----------------------------------------------------
    // Constructor
    //-----------------------------------------------------
    
    /**
     * Metodo constructor con argumentos  
     * @param localizacion la localizacion del sintoma
     * @param nombre el nombre del sintoma
     * @param intensidad la intensidad del dolor del sintoma
     */
    public SintomaDTO(String localizacion, String nombre, int intensidad) {
        this.localizacion = localizacion;
        this.nombre = nombre;
        this.intensidad = intensidad;
    }

    /**
     * Constructor sin argumentos
     */
    public SintomaDTO() {
    }
    
    /**
     * Metodo que retorna la localizacion del sintoma
     * @return la localizacion del sintoma
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * Metodo que modifica la localizacion del sintoma
     * @param localizacion la nueva localizacion
     */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * Metodo que retorna el nombre del sintoma
     * @return el nombre del sintoma
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que modifica el nombre del sintoma
     * @param nombre el nuevo nombre del sintoma
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Metodo que retorna la intensidad del sintoma
     * @return 
     */
    public int getIntensidad()
    {
        return intensidad;
    }
    
    /**
     * Metodo que modifica la intensidad del sintoma por la intensidad dada por parametro
     * @param intensidad la nueva intensidad del sintoma
     */
    public void setIntensidad(int intensidad)
    {
        this.intensidad = intensidad;
    }
    
    /**
     * Metodo que retorna el id del sintoma
     * @return el id del sintoma
     */
    public Long getId()
    {
        return id;
    }
    
    /**
     * Metodo que cambia el id por el id dado por parametro
     * @param id el nuevo id
     */
    public void setId(Long id)
    {
        this.id = id;
    }
}