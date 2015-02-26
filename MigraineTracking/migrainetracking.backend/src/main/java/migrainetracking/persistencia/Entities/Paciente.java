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
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

     /**
     * El nombre del paciente
     */
    private String nombre;
    
    /**
     * El número de identificación del paciente.(Pendiente un non-clust index) 
     */
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
    
    
    public Paciente(){
        
    }
    
    //-------------------------------------------------------------------------
    //Metodos
    //-------------------------------------------------------------------------
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

    public int getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(int noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public List<Medicamento> getMedicamentosDiarios() {
        return medicamentosDiarios;
    }

    public void setMedicamentosDiarios(List<Medicamento> medicamentosDiarios) {
        this.medicamentosDiarios = medicamentosDiarios;
    }

    public List<Catalizador> getHabitos() {
        return habitos;
    }

    public void setHabitos(List<Catalizador> habitos) {
        this.habitos = habitos;
    }

    public List<EpisodioDolor> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<EpisodioDolor> episodios) {
        this.episodios = episodios;
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
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "migrainetracking.persistencia.Entities.Paciente[ id=" + id + " ]";
    }
    
}
