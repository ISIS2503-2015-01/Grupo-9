/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerSide.Models.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Personal
 */
@Entity
public class EpisodioDolorEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    //-------------------------------------------------------------------------
    // Attributes
    //-------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private String localizacion;
    
    private Integer hoursSlept;
    
    
    @ManyToOne
    private PacienteEntity paciente;
    
    //<!-- Atributos que guardan JSON arrays -->
    
    private String medicamentos;
    
    private String sintomas;
    
    private String catalizadores;
    
    //-------------------------------------------------------------------------
    // Constructor
    //-------------------------------------------------------------------------
    public EpisodioDolorEntity(){}
    
    //-------------------------------------------------------------------------
    // Getters and Setters
    //-------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
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

    public Integer getHoursSlept() {
        return hoursSlept;
    }

    public void setHoursSlept(Integer hoursSlept) {
        this.hoursSlept = hoursSlept;
    }


    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getCatalizadores() {
        return catalizadores;
    }

    public void setCatalizadores(String catalizadores) {
        this.catalizadores = catalizadores;
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
        if (!(object instanceof EpisodioDolorEntity)) {
            return false;
        }
        EpisodioDolorEntity other = (EpisodioDolorEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ServerSide.Models.Entities.EpisodioDolorEntity[ id=" + id + " ]";
    }
    
}