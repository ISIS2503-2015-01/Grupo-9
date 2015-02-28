/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author estudiante
 */
@Entity
public class Sintoma implements Serializable {

    //-----------------------------------------------
    //Constante de serializacion
    //-----------------------------------------------
    
    /**
     * La constante para manejar la serializacion de la clase
     */
    private static final long serialVersionUID = 1L;

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //-----------------------------------------------
    //Constructor
    //-----------------------------------------------
    
    /**
     * Metodo constructor sin argumentos
     */
    public Sintoma(){
        
    }
    
    //-----------------------------------------------------
    // Metodos
    //-----------------------------------------------------
    
    /**
     * Metodo que retorna el id del sintoma
     * @return el id del sintoma
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo que cambia el id del sintoma por el id dado por parametro
     * @param id el nuevo id del sintoma
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo que retorna la localizacion del sintoma
     * @return la localizacion del sintoma
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * Metodo que cambia la localizacion del sintoma por la localizacion dada por parametro
     * @param localizacion la nueva localizacion del sintoma
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
     * Metodo que cambia el nombre del sintoma por el nombre dado por parametro
     * @param nombre el nuevo nombre del sintoma
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que retorna la intensidad del dolor del sintoma
     * @return la intensidad de dolor
     */
    public int getIntensidad() {
        return intensidad;
    }

    /**
     * Metodo que cambia la intensidad por el valor dado por parametro
     * @param intensidad la nueva intensidad del sintoma
     */
    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }
    
    /**
     * Metodo que retorna el codigo hash del sintoma
     * @return el codigo hash del sintoma
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Metodo para comprar si el sintoma es igual a otro objeto
     * @param object el objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sintoma)) {
            return false;
        }
        Sintoma other = (Sintoma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Metodo que convierte el sintoma en un string
     * @return el string resultante
     */
    @Override
    public String toString() {
        return "migrainetracking.persistencia.Entities.Sintoma[ id=" + id + " ]";
    }

}
