/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import static java.util.Calendar.DATE;
import java.util.Date; // sql
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import org.eclipse.persistence.jaxb.javamodel.Helper;


/**
 *
 * @author estudiante
 */
@Entity
public class EpisodioDolor implements Serializable {
    
    //--------------------------------
    //Constante de Serializacion
    //--------------------------------
    
    /**
     * Constante para manejar la serializacion de la clase
     */
    private static final long serialVersionUID = 1L;
    
    //--------------------------------
    //Atributos
    //--------------------------------
    
    /**
     * EL id del episodio de dolor
     */
    @Id
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
    private int horasDeSueno;
    
    /**
     * La lista de sintomas que presenta el episodio de dolor
     */
    @OneToMany
    private List<Sintoma> sintomas;
    
    /**
     * La lista de catalizadores relacionados con el episodio de dolor. Se llenan con evitables en la busqueda.
     */
    @OneToMany
    private List<Catalizador> catalizadores;// = new ArrayList<Catalizador>();
    
    /**
     * La lista de medicamentos que el paciente esta tomando en el momento del episodio de dolor
     */
    @OneToMany
    private List<Medicamento> medicamentosActuales;// = new ArrayList<Medicamento>();
    
    /**
     * El paciente dueno del episodio
     */
    @ManyToOne
    private Paciente paciente;

    //--------------------------------
    //Constructor
    //--------------------------------
    
    /**
     * Metodo constructor sin argumentos
     */
    public EpisodioDolor(){
        this.id =  UUID.randomUUID().getLeastSignificantBits() ;
    }
    
    //-------------------------------------------------------------------------
    //Metodos
    //-------------------------------------------------------------------------
    
    /**
     * Metodo que retorna el id del episodio de dolor
     * @return el id del episodio de dolor
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo que cambia el id del episodio por el id dado por parametro
     * @param id el nuevo id del episodio
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo que retorna la fehca del episodio
     * @return la fecha del episodio
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Metodo que cambia la fecha del episodio por la fecha dada por parametro
     * @param fecha la nueva fecha del episodio
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Metodo que retorna la localizacion del episodio
     * @return la localizacion del episodio
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * Metodo que cambia la localizacion por la localizacion dada por parametro
     * @param localizacion la nueva localizacion del episodio
     */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * Metodo que retorna la intensidad del dolor 
     * @return la intendisd de dolor
     */
    public int getIntensidadDolor() {
        return intensidadDolor;
    }

    /**
     * Metodo que cambia la intensidad del dolor por la intensidad dada por parametro
     * @param intensidadDolor la nueva intensidad del dolor
     */
    public void setIntensidadDolor(int intensidadDolor) {
        this.intensidadDolor = intensidadDolor;
    }

    /**
     * Metodo que retorna las horas de sueno del episodio
     * @return las horas de sueno del episodio
     */
    public int getHorasDeSueno() {
        return horasDeSueno;
    }

    /**
     * Parametro que cambia las horas de sueno por las horas dadas por parametro
     * @param horasDeSueno las nuevas horas de sueno
     */
    public void setHorasDeSueno(int horasDeSueno) {
        this.horasDeSueno = horasDeSueno;
    }

    /**
     * Metodo que retorna los sintomas del episodio
     * @return los sintomas del episodio
     */
    public List<Sintoma> getSintomas() {
        return sintomas;
    }

    /**
     * Metodo que cambia los sintomas del episodio, por los sintomas dados por parametro
     * @param sintomas los nuevos sintomas del episodio
     */
    public void setSintomas(List<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }

    /**
     * Metodo que retorna los catalizadores del episodio
     * @return los episodios de los catalizadores
     */
    public List<Catalizador> getCatalizadores() {
        return catalizadores;
    }

    /**
     * Metodo que cambia los catalizadores del episodio, por los catalizadores dados por parametro
     * @param catalizadores los nuevos catalizadores
     */
    public void setCatalizadores(List<Catalizador> catalizadores) {
        this.catalizadores = catalizadores;
    }

    /**
     * Metodo que retorna la lista de los medicamentos del episodio
     * @return los medicamentos del episodio
     */
    public List<Medicamento> getMedicamentosActuales() {
        return medicamentosActuales;
    }

    /**
     * Metodo que cambia los medicamentos, por los medicamentos dados por parametro
     * @param medicamentosActuales los nuevos medicamentos
     */
    public void setMedicamentosActuales(List<Medicamento> medicamentosActuales) {
        this.medicamentosActuales = medicamentosActuales;
    }

    /**
     * Metodo que retorna el paciente del cual el episodio se genero
     * @return el paciente que registro el episodio
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Metodo que cambia el paciente por el paciente dado como parametro
     * @param paciente el nuevo paciente del episodio
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    /**
     * Metodo que retorna el codigo hash del episodio
     * @return el codigo hash del episodio
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Metodo para compara el episodio con otro objeto
     * @param object el objeto con el cual se va a comparar
     * @return true si son iguales, false en caso contrario
     */
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

    /**
     * Metodo para convertir el episodio en string
     * @return el strign resultante
     */
    @Override
    public String toString() {
        return "migrainetracking.persistencia.Entities.EpisodioDolor[ id=" + id + " ]";
    }
    
}
