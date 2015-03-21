/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author estudiante
 */
@Entity
public class Regla implements Serializable {
    
    //-----------------------------------------------
    //Constante de serializacion
    //-----------------------------------------------
    
    /**
     * Constante para manejar la serializacion de la clase
     */
    private static final long serialVersionUID = 1L;
    
    //-----------------------------------------------
    //Atributos
    //-----------------------------------------------
    
    /**
     * El id generado de la regla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
     * Las acciones o sugerencias cuando se presentan catalizadores para cuando se presenta una situacion con las condiciones de la regla
     */
    private String acciones;
    
    //-----------------------------------------------
    //Constructor
    //-----------------------------------------------
    
    /**
     * Metodo constructor sin argumentos
     */
    public Regla(){
        
    }
    
    //-----------------------------------------------
    //Metodos
    //-----------------------------------------------
    
    
    /**
     * Metodo para retornar el id de la regla
     * @return el id de la regla 
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo que cambia el id de la regla por el id dado por parametro
     * @param id el nuevo id de la regla
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo que retorna la intensidad minima de dolor
     * @return la intensidad minima de dolor
     */
    public int getIntensidadDolorMin() {
        return intensidadDolorMin;
    }

    /**
     * Metodo que cambia la intensidad de dolor minima por el valor dado por parametro
     * @param intensidadDolorMin la nueva intensidad minima
     */
    public void setIntensidadDolorMin(int intensidadDolorMin) {
        this.intensidadDolorMin = intensidadDolorMin;
    }

    /**
     * Metodo que retorna la intensidad maxima de dolor
     * @return la intensidad maxima de dolor
     */
    public int getIntensidadDolorMax() {
        return intensidadDolorMax;
    }

    /**
     * Metodo que cambia la intensidad maxima de la regla por la intensidad dada por parametro
     * @param intensidadDolorMax la nueva intensidad maxima
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
     * Metodo que cambia la localizacion del dolor por la localizacion dada por parametro
     * @param localizacionDolor la nueva localizacion del dolor
     */
    public void setLocalizacionDolor(String localizacionDolor) {
        this.localizacionDolor = localizacionDolor;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }
    
    /**
     * Metodo que convierte una regla en string
     * @return el string resultante
     */
    @Override
    public String toString() {
        return "migrainetracking.persistencia.Entities.Regla[ id=" + id + " ]";
    }
    
}