 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package grupo9.arquisoft.migrainetrackingmobile.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class EpisodioDolorDTO {

    /**
     * El id del episodio. Este es autogenerado
     */
    private Long id;

    /**
     * La fecha del episodio.
     */
    private Long fecha;

    /**
     * La localizacion del dolor
     */
    private String localizacion;

    // Del 1 al 10...
    /**
     * La intensidad del dolor
     */
    private Integer intensidad;

    /**
     * La cantidad de horas de sueño
     */
    private Integer hoursSlept;

    /**
     * La cedula del paciente que registro el episodio
     */
    private Long cedulaPaciente;

    // <!-- Ojo: Estas listas se persisten como JSONArray >

    /**
     * Los sintomas que tenia el paciente en el momento del episodio
     */
    private List<SintomaDTO> sintomas;

    /**
     * La lista de catalizadores relacionada con el episodio
     */
    private List<CatalizadorDTO> catalizadores;

    /**
     * La lista de medicamentos que el paciente se encontraba tomando en el momento del episodio
     */
    private List<MedicamentoDTO> medicamentos;

    //----------------------------------------------------------------------
    // Constructor
    //----------------------------------------------------------------------

    /**
     * Metodo constructor sin argumentos
     */
    public EpisodioDolorDTO(){

    }

    //----------------------------------------------------------------------
    // Getters and Setters
    //----------------------------------------------------------------------

    /**
     * Retorna el id del episodio
     * @return el id del episodio
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el nuevo id del episodio
     * @param id el id del episodio
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna la cedula del paciente
     * @return la cedula del paciente
     */
    public Long getCedulaPaciente() {
        return cedulaPaciente;
    }

    /**
     * Cambia la cedula del paciente
     * @param cedulaPaciente la nueva cedula del paciente
     */
    public void setCedulaPaciente(Long cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }

    /**
     * Retorna la fecha del episodio
     * @return la fecha del episodio
     */
    public Long getFecha() {
        return fecha;
    }

    /**
     * Cambia la fecha del episodio
     * @param fecha
     */
    public void setFecha(Long fecha) {this.fecha = fecha; }

    /**
     * Retorna la localizacion del episodio
     * @return la localizacion del episodio
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * Cambia la localizacion del episodio
     * @param localizacion la nueva localizacion
     */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * Retorna la intensidad del dolor
     * @return la intensidad del dolor
     */
    public Integer getIntensidad() {
        return intensidad;
    }

    /**
     * Cambia la intensidad del dolor
     * @param intensidad la nueva intensidad del episodio
     */
    public void setIntensidad(Integer intensidad) {
        this.intensidad = intensidad;
    }

    /**
     * Retorna las horas de suenio
     * @return las horas de suenio
     */
    public Integer getHoursSlept() {
        return hoursSlept;
    }

    /**
     * Cambia la cantidad de horas de suenio
     * @param hoursSlept las nuevas horas de suenio
     */
    public void setHoursSlept(Integer hoursSlept) {
        this.hoursSlept = hoursSlept;
    }

    /**
     * Metodo que retorna la lista de sintomas del episodio
     * @return la lista de sintomas
     */
    public List<SintomaDTO> getSintomas() {return sintomas;}

    /**
     * Cambia la lista de sintomas del paciente
     * @param sintomas la nueva lista de sintomas
     */
    public void setSintomas(List<SintomaDTO> sintomas) {
        this.sintomas = sintomas;
    }

    /**
     * Retorna los catalizadores del episodio
     * @return los catalizadores del episodio
     */
    public List<CatalizadorDTO> getCatalizadores() {
        return catalizadores;
    }

    /**
     * Cambia los catalizadores por los que se dan por parametro
     * @param catalizadores los nuevos catalizadores
     */
    public void setCatalizadores(List<CatalizadorDTO> catalizadores) {
        this.catalizadores = catalizadores;
    }

    /**
     * Retorna la lista de medicamentos que estaba tomando el paciente
     * @return la lista de medicamentos
     */
    public List<MedicamentoDTO> getMedicamentos() {
        return medicamentos;
    }

    /**
     * Cambia la lista de medicamentos
     * @param medicamentos la nueva lista de medicamentos
     */
    public void setMedicamentos(List<MedicamentoDTO> medicamentos) {
        this.medicamentos = medicamentos;
    }
}