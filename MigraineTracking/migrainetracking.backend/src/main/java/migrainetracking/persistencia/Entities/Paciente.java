/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


/**
 *
 * @author estudiante
 */
@Entity
public class Paciente implements Serializable {
    
    //-----------------------------------------------
    //Constante de serializacion
    //-----------------------------------------------
    
    /**
     * Constante para manejar la serializacion de la clase
     */
    private static final long serialVersionUID = 1L;

     /**
     * El nombre del paciente
     */
    private String nombre;
    
    /**
     * El número de identificación del paciente.(Pendiente un non-clust index) 
     */
    @Id
    private int noIdentificacion;
    
    /**
     * Fecha de Nacimiento del Usuario
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;

    /**
     * El peso del paciente
     */
    private int peso;
    
    /**
     * La estatura del paciente
     */
    private int estatura;
    
    /**
     * Los medicamentos que toma a diario el paciente
     */
    @OneToMany(orphanRemoval = true)
    private List<Medicamento> medicamentosDiarios;
    
    /**
     * La lista de habitos que tiene el paciente
     */
    @OneToMany(orphanRemoval = true)
    private List<Catalizador> habitos; // Estos deben ser de tipo habito...
    
    /**
     * La lista con los episodios de dolor que ha tenido el paciente
     */
    @OneToMany(orphanRemoval=true,mappedBy="paciente")
    private List<EpisodioDolor> episodios;
    
    //-----------------------------------------------
    //Constructor
    //-----------------------------------------------
    
    /**
     * Metodo constructor sin argumentos
     */
    public Paciente(){
        
    }
    
    //-------------------------------------------------------------------------
    //Metodos
    //-------------------------------------------------------------------------
   
    /**
     * Metodo que retorna el nombre del paciente
     * @return el nombre del paciente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que cambia el nombre del paciente por el nombre dado por parametro
     * @param nombre el nuevo nombre del paciente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que retorna el numero de identificacion del paciente
     * @return el numero de identificacion del paciente
     */
    public int getNoIdentificacion() {
        return noIdentificacion;
    }

    /**
     * Metodo que cambia el numero de identificacion del paciente, por el numero dado por parametro
     * @param noIdentificacion el nuevo numero de identifiacion
     */
    public void setNoIdentificacion(int noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    /**
     * Metodo que retorna la fecha de nacimiento del paciente
     * @return la fecha de nacimiento del paciente
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Metodo que cambia la fecha de nacimiento del paciente por la fecha dada por parametro
     * @param fechaNacimiento la nueva fecha de nacimiento
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Metodo que retorna el peso del paciente
     * @return el peso del paciente
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Metodo que cambia el peso del paciente por el peso dado por parametro
     * @param peso el nuevo peso del paciente
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * Metodo que retorna la estatura del paciente
     * @return la estatura del paciente
     */
    public int getEstatura() {
        return estatura;
    }

    /**
     * Metodo que cambia la estatura del paciente por la estatura dada por parametro
     * @param estatura la nueva estatura del paciente
     */
    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    /**
     * Metodo que retorna la lista de medicamentos que toma el paciente
     * @return la lista de medicamentos
     */
    public List<Medicamento> getMedicamentosDiarios() {
        return medicamentosDiarios;
    }

    /**
     * Metodo que cambia la lista de los medicamentos por los medicamentos dados por parametro
     * @param medicamentosDiarios la nueva lista de los medicamentos
     */
    public void setMedicamentosDiarios(List<Medicamento> medicamentosDiarios) {
        this.medicamentosDiarios = medicamentosDiarios;
    }

    /**
     * Metodo que retorna los habitos del paciente
     * @return los habitos del paciente
     */
    public List<Catalizador> getHabitos() {
        return habitos;
    }

    /**
     * Metodo que cambia la lista de habitos del paciente
     * @param habitos la nueva lista de habitos del paciente
     */
    public void setHabitos(List<Catalizador> habitos) {
        this.habitos = habitos;
    }

    /**
     * Metodo que retorna los episodios del paciente
     * @return los episodios del paciente
     */
    public List<EpisodioDolor> getEpisodios() {
        return episodios;
    }

    /**
     * Metodo que cambia los episodios del paciente por los episodios dados por parametro
     * @param episodios los nuevos episodios del paciente
     */
    public void setEpisodios(List<EpisodioDolor> episodios) {
        this.episodios = episodios;
    }


    /**
     * Metodo para comparar el paciente con otros objetos
     * @param object el objeto con el cual se va a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ( this.noIdentificacion != other.noIdentificacion ) {
            return false;
        }
        return true;
    }
}
