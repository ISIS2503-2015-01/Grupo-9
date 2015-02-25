/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Personal
 */
public class ReglaDTO {
    
    //-----------------------------------------------------
    // Atributos
    //-----------------------------------------------------
    
    /**
     * Id de la regla
     */
    private int id;
    
    /**
     * La intesidad minima requerida para que la regla sea valida
     */
    private int intensidadDolorMin;
    
    /**
     * La intensidad maxima requerida para que la regla sea valida
     */
    private int intensidadDolorMax;
    
    /**
     * La localizacion del dolor para que la regla sea valida
     */
    private String localizacionDolor;
    
    /**
     * Los catalizadores para cuando se presenta una situacion con las condiciones de la regla
     */
    List<CatalizadorDTO> evitables;
    
    //-----------------------------------------------------
    // Constructor
    //-----------------------------------------------------
    
    /**
     * Metodo constructor sin argumentos
     */
    public ReglaDTO(){ 
        evitables = new ArrayList<CatalizadorDTO>();
    }
    
    /**
     * Metodo constructor con argumentos
     * @param intensidadDolorMin la intensidad minima de dolor
     * @param intensidadDolorMax la intensidad maxima de dolor
     * @param localizacionDolor la localizacion del dolor
     * @param evitables la lista de catalizadores
     */
    public ReglaDTO(int intensidadDolorMin, int intensidadDolorMax, String localizacionDolor, List<CatalizadorDTO> evitables)
    {
        this.intensidadDolorMin = intensidadDolorMin;
        this.intensidadDolorMax = intensidadDolorMax;
        this.localizacionDolor = localizacionDolor;
        this.evitables = evitables;
    }

    //-----------------------------------------------------
    // Metodos
    //-----------------------------------------------------
    
    /**
     *Metodo que se encarga de retorna la intensidad minima del dolor 
     * @return la intensidad minima de dolor
     */
    public int getIntensidadDolorMin() {
        return intensidadDolorMin;
    }

    /**
     * Metodo que modifica la intensidad minima de dolor requerida
     * @param intensidadDolorMin la nueva intensidad minima del dolor
     */
    public void setIntensidadDolorMin(int intensidadDolorMin) {
        this.intensidadDolorMin = intensidadDolorMin;
    }

    /**
     * Metodo que se encarga de retornar la intensidad maxima de dolor 
     * @return la intensidad maxima de dolor
     */
    public int getIntensidadDolorMax() {
        return intensidadDolorMax;
    }

    /**
     * Metodo que modifica la intensidad maxima de dolor requerida
     * @param intensidadDolorMax la nueva intensidad de dolor
     */
    public void setIntensidadDolorMax(int intensidadDolorMax) {
        this.intensidadDolorMax = intensidadDolorMax;
    }

    /**
     * Metodo que retorna la localizacion del dolor
     * @return la localizacion del dolor
     */
    public String getLocalizacionDolor() {
        return localizacionDolor;
    }

    /**
     * Metodo que cambia la localizacion del dolor por la localizacion dada como parametro
     * @param localizacionDolor la nueva localizacion del dolor
     */
    public void setLocalizacionDolor(String localizacionDolor) {
        this.localizacionDolor = localizacionDolor;
    }

    /**
     * Metodo que retorna la lista de catalizadores a la regla
     * @return 
     */
    public List<CatalizadorDTO> getEvitables() {
        return evitables;
    }

    /**
     * Metodo que se encarga de cambiar la lista de evitables por la lista dada como parametro
     * @param evitables la nueva lista de catalizadores a la regla
     */
    public void setEvitables(List<CatalizadorDTO> evitables) {
        this.evitables = evitables;
    }  
    
    /**
     * Metodo que retorna el id de la regla
     * @return el id de la regla
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Metodo que se encarga de modificar el id y lo cambia por el que se da como parametro
     * @param id el nuevo id de la regla
     */
    public void setId(int id)
    {
        this.id = id;
    }
}