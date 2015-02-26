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
public class Regla implements Serializable {
    private static final long serialVersionUID = 1L;
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
     * Los catalizadores para cuando se presenta una situacion con las condiciones de la regla
     */
    @OneToMany(orphanRemoval=true)
    List<Catalizador> evitables;
    
    public Regla(){
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntensidadDolorMin() {
        return intensidadDolorMin;
    }

    public void setIntensidadDolorMin(int intensidadDolorMin) {
        this.intensidadDolorMin = intensidadDolorMin;
    }

    public int getIntensidadDolorMax() {
        return intensidadDolorMax;
    }

    public void setIntensidadDolorMax(int intensidadDolorMax) {
        this.intensidadDolorMax = intensidadDolorMax;
    }

    public String getLocalizacionDolor() {
        return localizacionDolor;
    }

    public void setLocalizacionDolor(String localizacionDolor) {
        this.localizacionDolor = localizacionDolor;
    }

    public List<Catalizador> getEvitables() {
        return evitables;
    }

    public void setEvitables(List<Catalizador> evitables) {
        this.evitables = evitables;
    }

    @Override
    public String toString() {
        return "migrainetracking.persistencia.Entities.Regla[ id=" + id + " ]";
    }
    
}
