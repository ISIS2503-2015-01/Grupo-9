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
    
    //-----------------------------------------------
    //COnstante de Serializacion
    //-----------------------------------------------
    
    /**
     * Constante para manejra la serializacion de la clase
     */
    private static final long serialVersionUID = 1L;
   
    //-----------------------------------------------
    //Atributos
    //-----------------------------------------------
   
    /**
     * El id del medicamento
     */
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
    
    //-----------------------------------------------
    //Constructor
    //-----------------------------------------------
    
    /**
     * Metodo constructor sin argumentos
     */
    public Medicamento(){
    
    }
    
    //-----------------------------------------------
    //Metodos
    //-----------------------------------------------
    
    /**
     * Metodo que retorna el id del medicamento
     * @return el id del medicamento
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo que cambia el id del medicamento por el id dado por parametro
     * @param id el nuevo id del medicamento
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo que retorna el nomvbre del medicamento
     * @return el nombre del medicamento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que cambia el nombre del medicamento por el nombre dado por parametro
     * @param nombre el nuevo nombre del medicamento
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que cambia la cantidad de veces al dia que se debe tomar el medicamento
     * @return la cantidad de veces que se debe tomar el medicamento
     */
    public int getCantidadVecesAlDia() {
        return cantidadVecesAlDia;
    }

    /**
     * MEtodo que cambia la cantidad de veces por la cantidad dada por parametro
     * @param cantidadVecesAlDia la nueva cantidad que se debe tomar el medicamento
     */
    public void setCantidadVecesAlDia(int cantidadVecesAlDia) {
        this.cantidadVecesAlDia = cantidadVecesAlDia;
    }

    /**
     * Metodo que retorna los intervalos de horas en los que se debe tomar el medicamento
     * @return los intervalos de horas en los que se debe tomar los medicamentos
     */
    public int getIntervaloHoras() {
        return intervaloHoras;
    }

    /**
     * Metodo que canbia el intervalo de horas por el intervalo dado por parametro
     * @param intervaloHoras el nuevo intervalo de horas
     */
    public void setIntervaloHoras(int intervaloHoras) {
        this.intervaloHoras = intervaloHoras;
    }

    /**
     * Metodo que retorna la cantidad que se debe tomar del medicamento
     * @return la cantidad que se debe tomar del medicamento
     */
    public int getMiligramos() {
        return miligramos;
    }

    /**
     * Metodo que cambia la cantidad de miligramos que se debe tomar por la cantidad dada por parametro
     * @param miligramos la nueva cantidad del medicamento
     */
    public void setMiligramos(int miligramos) {
        this.miligramos = miligramos;
    }

    /**
     * Metodo que retorna la fecha en que se receto el medicamento
     * @return la fecha en que el medicamento fue recetado
     */
    public Date getFechaRecetado() {
        return fechaRecetado;
    }

    /**
     * Metodo que cambia la fecha por la fecha dada por parametro
     * @param fechaRecetado la nueva fecha en que fue recetado el medicamento
     */
    public void setFechaRecetado(Date fechaRecetado) {
        this.fechaRecetado = fechaRecetado;
    }

    /**
     * Metodo que retorna el codigo hash del medicamento
     * @return el codigo hash del medicamento
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * metodo para comparar el medicamento con otro objeto
     * @param object el objeto con el cual se va a comparar
     * @return true si son igual, false en caso contrario
     */
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

    /**
     * Metodo que transforma el medicamento en string
     * @return el string resultante
     */
    @Override
    public String toString() {
        return "migrainetracking.persistencia.Entities.Medicamento[ id=" + id + " ]";
    }
}
