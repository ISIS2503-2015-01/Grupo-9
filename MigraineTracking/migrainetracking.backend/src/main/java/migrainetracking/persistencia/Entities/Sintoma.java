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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Sintoma(){
        
    }
    //-----------------------------------------------------
    // Metodos
    //-----------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(int intensidad) {
        this.intensidad = intensidad;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "migrainetracking.persistencia.Entities.Sintoma[ id=" + id + " ]";
    }

}
