 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class EpisodioDolorDTO {
    
    //-----------------------------------------------
    //Atributos
    //-----------------------------------------------
    
    /**
     * El numero de identificacion del episodio de dolor
     */
    private Long id;
    
    /**
     * Fecha en la que ocurrio el episodio de dolor
     */
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
    private List<SintomaDTO> sintomas; 
    
    /**
     * La lista de catalizadores relacionados con el episodio de dolor
     */
    private List<CatalizadorDTO> catalizadores;
    
    /**
     * La lista de medicamentos que el paciente esta tomando en el momento del episodio de dolor
     */
    private List<MedicamentoDTO> medicamentosActuales;
    
    /**
     * El id del paciente
     */
    private Long pacienteId;
    
    //-----------------------------------------------
    //Constructor
    //-----------------------------------------------
    
    /**
     * Metodo constructor sin parametros
     */
    public EpisodioDolorDTO() {
        
    }

    /**
     * Metodo constructor con parametros
     * @param fecha la fecha en la que ocurre el episodio de dolor
     * @param localizacion la localizacion del episodio de dolor
     * @param horasDeSueño las horas de sueno que se tienen durante el espisodio de sueno
     * @param intensidadDolor la intensidad del dolor del episodio
     */
    public EpisodioDolorDTO( Date fecha, String localizacion, int horasDeSueño, int intensidadDolor) {
        this.fecha = fecha;
        this.localizacion = localizacion; 
        this.intensidadDolor = intensidadDolor;
        this.horasDeSueño = horasDeSueño;
        sintomas = new ArrayList<SintomaDTO>();
        catalizadores = new ArrayList<CatalizadorDTO>();
        medicamentosActuales = new ArrayList<MedicamentoDTO>();
    }

    //-----------------------------------------------
    //Metodos
    //-----------------------------------------------
    
    /**
     * Metodo que retorna el numero id del episodio
     * @return el id del episodiio de dolor
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo que modifica el numero de id por el numero dado como parametro
     * @param id el nuevo id del episodio de dolor
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Metodo que retorna la fecha del episodio de dolor
     * @return la fecha del episodio de dolor
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Metodo que cambia la fecha del episodio por la fecha dada como parametro
     * @param fecha la nueva fecha del episodio
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Metodo que retorna la intensidad del dolor del episodio
     * @return la intensidad del dolor
     */
    public int getIntensidadDolor() {
        return intensidadDolor;
    }

    /**
     * Metodo que cambia la intensidad del dolor por la nueva intensidad dada como parametro
     * @param intensidadDolor la nueva intensidad del dolor
     */
    public void setIntensidadDolor(int intensidadDolor) {
        this.intensidadDolor = intensidadDolor;
    }
    
    /**
     * Metodo que retorna la lista de sintomas relacionados con el episodio de dolor
     * @return los sintomas relacionados con el episodio
     */
    public List<SintomaDTO> getSintomas() {
        return sintomas;
    }

    /**
     * Metodo que cambia la lista de sintomas por los sintomas que se dan como parametro
     * @param sintomas los nuevos sintomas del episodio
     */
    public void setSintomas(List<SintomaDTO> sintomas) {
        this.sintomas = sintomas;
    }

    /**
     * Metodo que retorna la lista de los catalizadores relacionados con el episodio de dolor
     * @return la lista de catalizadores relacionados con el episodio de dolor
     */
    public List<CatalizadorDTO> getCatalizadores() {
        return catalizadores;
    }

    /**
     * Metodo que cambia la lista de los catalizadores por la nueva lista dada como parametro
     * @param catalizadores los nuevos catalizadores del episodio
     */
    public void setCatalizadores(List<CatalizadorDTO> catalizadores) {
        this.catalizadores = catalizadores;
    }

    /**
     * Metodo que retorna la lista de los medicamentos que actualmente utiliza el paciente
     * @return la lista de los medicamentos que actualmente esta tomando el paciente
     */
    public List<MedicamentoDTO> getMedicamentosActuales() {
        return medicamentosActuales;
    }

    /**
     * Metodo que cambia la lista de los medicamentos actuales por la lista dada como parametro
     * @param medicamentosActuales la nueva lista de los medicamentos
     */
    public void setMedicamentosActuales(List<MedicamentoDTO> medicamentosActuales) {
        this.medicamentosActuales = medicamentosActuales;
    }

    /**
     * Metodo que retorna la localizacion del dolor
     * @return la localizacion del dolor
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
    * Metodo que cambia la localizacion del dolor por la nueva localizacion dada como parametro
    * @param localizacion la nueva localizacion del dolor
    */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * Metodo que retorna las horas de sueno del episodio
     * @return las horas de sueno
     */
    public int getHorasDeSueño() {
        return horasDeSueño;
    }

    /**
     * Metodo que cambia la cantidad de horas de sueno por la cantidad dada como parametro
     * @param horasDeSueño las nuevas horas de sueno relacionadas con el episodio de dolor
     */
    public void setHorasDeSueño(int horasDeSueño) {
        this.horasDeSueño = horasDeSueño;
    }

    /**
     * Metodo que retorna el id del paciente
     * @return el id del paciente
     */
    public Long getPacienteId() {
        return pacienteId;
    }

    /**
     * Metodo que cmabia el id del paciente por el id dado por parametro
     * @param pacienteId el nuevo id del paciente
     */
    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
}