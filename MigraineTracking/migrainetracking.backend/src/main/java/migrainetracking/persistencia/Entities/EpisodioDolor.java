/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.Entities;

import java.io.Serializable;
import static java.util.Calendar.DATE;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;


/**
 *
 * @author estudiante
 */
@Entity
public class EpisodioDolor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Fecha en la que ocurrio el episodio de dolor
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    /**
     * La localizacion del episodio de dolor
     */
    private String localizacion;
    
    /**
     * La intensidad del dolor del episodio
     */
    private int intensidadDolor;
    
    /**
     *Aproximado de las horas diarias promdedio que el paciente durmio en los ultimos 3 dias previos. 
     */
    private int horasDeSueño;
    
    /**
     * La lista de sintomas que presenta el episodio de dolor
     */
    @OneToMany(orphanRemoval = false,fetch = FetchType.LAZY)
    private List<Sintoma> sintomas; 
    
    /**
     * La lista de catalizadores relacionados con el episodio de dolor. Se llenan con evitables en la busqueda.
     */
    @OneToMany(orphanRemoval = false,fetch = FetchType.LAZY)
    private List<Catalizador> catalizadores;
    
    /**
     * La lista de medicamentos que el paciente esta tomando en el momento del episodio de dolor
     */
    @OneToMany(orphanRemoval = false,fetch = FetchType.LAZY)
    private List<Medicamento> medicamentosActuales;
    
    @ManyToOne
    private Paciente paciente;

    public EpisodioDolor(){
        
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public int getIntensidadDolor() {
        return intensidadDolor;
    }

    public void setIntensidadDolor(int intensidadDolor) {
        this.intensidadDolor = intensidadDolor;
    }

    public int getHorasDeSueño() {
        return horasDeSueño;
    }

    public void setHorasDeSueño(int horasDeSueño) {
        this.horasDeSueño = horasDeSueño;
    }

    public List<Sintoma> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }

    public List<Catalizador> getCatalizadores() {
        return catalizadores;
    }

    public void setCatalizadores(List<Catalizador> catalizadores) {
        this.catalizadores = catalizadores;
    }

    public List<Medicamento> getMedicamentosActuales() {
        return medicamentosActuales;
    }

    public void setMedicamentosActuales(List<Medicamento> medicamentosActuales) {
        this.medicamentosActuales = medicamentosActuales;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
        if (!(object instanceof EpisodioDolor)) {
            return false;
        }
        EpisodioDolor other = (EpisodioDolor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "migrainetracking.persistencia.Entities.EpisodioDolor[ id=" + id + " ]";
    }
    
}
