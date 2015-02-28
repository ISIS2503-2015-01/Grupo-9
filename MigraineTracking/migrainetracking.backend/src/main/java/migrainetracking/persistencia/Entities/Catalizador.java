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
import javax.persistence.OneToMany;

/**
 *
 * @author estudiante
 */
@Entity
public class Catalizador implements Serializable {
    
    //-----------------------------------
    //Consante de serializacion
    //-----------------------------------
    
    /**
     * Constante para poder serializar la informacion
     */
    private static final long serialVersionUID = 1L;
    
    //-----------------------------------
    //Atributos
    //-----------------------------------
    
    /**
     *El id del catalizador 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * El tipo del catalizador
     * Puede ser un alimento, actividad fisica, bebida, o habito
     */
    private String tipo; 
    
    /**
     * Especificacion adicional del doctor
     */
    private String especificacion;
    
    /**
     * La cantidad de veces que se ha hecho o ingerido el catalizador
     */
    private int frecuencia;
    
    //-----------------------------------
    //Constructor
    //-----------------------------------
    
    /**
     * Metodo constructor sin argumentos
     */
    public Catalizador(){
        
    }
    
    //-----------------------------------
    //Metodos
    //-----------------------------------
    
    /**
     * Metodo que retorna el id del catalizador
     * @return el id del catalizador
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo que cambia el id del catalizador por el id dado por parametro
     * @param id el nuevo id del catalizador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo que se encarga de retornar el tipo del catalizador
     * @return el tipo del catalizador
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Metodo que modifica el tipo del catalizador por el tipo dado por parametro
     * @param tipo el nuevo tipo del catalizador
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo que retorna la especificacion del catalizador
     * @return la especificacion del catalizador
     */
    public String getEspecificacion() {
        return especificacion;
    }

    /**
     * Metodo que cambia a la especificacion por la especificacion dada por parametro
     * @param especificacion la nueva especificacion del catalizador
     */
    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    /**
     * Metodo que retorna la frecuencia del catalizador
     * @return la frecuancia con la que se deberia tomar el catalizador
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**
     * Metodo que cambia la frecuancia del catalizador por la frecuanci dada por parametro
     * @param frecuencia 
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    /**
     * Metodo para retornar el hash que identifica al catalizador
     * @return el hash del catalizador
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Metodo para comparar el catalizador
     * @param object el objeto con el cual se quiere comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalizador)) {
            return false;
        }
        Catalizador other = (Catalizador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Metodo que retorna el catalizador en string
     * @return el string que identifica al catalizador
     */
    @Override
    public String toString() {
        return "migrainetracking.persistencia.Entities.Catalizador[ id=" + id + " ]";
    }
}
