/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author estudiante
 */
@Entity
public class Medicamento implements Serializable {
    private static final long serialVersionUID = 1L;
   

    //-----------------------------------------------
    //Atributos
    //-----------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * El nombre del medicamento
     */
    private String nombre;
    
    /**
     * La cantidad de veces que se debe tomar el medicamento al dia
     */
    private int cantidadVecesAlDia;
    
    /**
     * Los intervalos de horas en los que se debe tomar el medicamento
     */
    private int intervaloHoras;
    
    /**
     * La cantidad que se debe tomar del medicamento cada vez
     */
    private int miligramos;
    
    /**
     * La fecha en que fue recetado el medicamento. En caso de no ser recetado por un medico es null.
     */
    @Temporal(DATE)
    private Date fechaRecetado;
    
    public Medicamento(){
    
    }
    //-----------------------------------------------
    //Metodos
    //-----------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadVecesAlDia() {
        return cantidadVecesAlDia;
    }

    public void setCantidadVecesAlDia(int cantidadVecesAlDia) {
        this.cantidadVecesAlDia = cantidadVecesAlDia;
    }

    public int getIntervaloHoras() {
        return intervaloHoras;
    }

    public void setIntervaloHoras(int intervaloHoras) {
        this.intervaloHoras = intervaloHoras;
    }

    public int getMiligramos() {
        return miligramos;
    }

    public void setMiligramos(int miligramos) {
        this.miligramos = miligramos;
    }

    public Date getFechaRecetado() {
        return fechaRecetado;
    }

    public void setFechaRecetado(Date fechaRecetado) {
        this.fechaRecetado = fechaRecetado;
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
        if (!(object instanceof Medicamento)) {
            return false;
        }
        Medicamento other = (Medicamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "migrainetracking.persistencia.Entities.Medicamento[ id=" + id + " ]";
    }
    
}
