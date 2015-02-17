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
public class Paciente {
    
   
    //-----------------------------------------------------
    // Atributos
    //-----------------------------------------------------
    
    /**
     * El nombre del paciente
     */
    private String nombre;
    
    /**
     * El número de identificación del paciente
     */
    private int noIdentificacion;
    
    /**
     * Fecha de Nacimiento del Usuario
     */
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
    private List<Medicamento> medicamentosDiarios;
    
    /**
     * La lista de habitos que tiene el paciente
     */
    private List<Catalizador> habitos; // Estos deben ser de tipo habito...
    
    /**
     * La lista con los episodios de dolor que ha tenido el paciente
     */
    private List<EpisodioDolor> episodios;

    //-----------------------------------------------------
    // Constructores
    //-----------------------------------------------------
    
    /**
     * Metodo constructor sin argumentos
     */
    public Paciente(){}
    
    /**
     * Metodo constructor con argumentos
     * @param nombre El nombre del paciente
     * @param noIdentificacion el numero de identificacion del paciente
     * @param fechaNacimiento la fecha de nacimiento del paciente
     * @param peso el peso del paciente
     * @param estatura la estatura del paciente
     * @param medicamentosDiarios los medicamentos que actualmente esta tomando el paciente
     * @param habitos los habitos del paciente (Los catalizadores)
     * @param episodios Los episodios de dolor que ha tendido el paciente 
     */
    public Paciente(String nombre, int noIdentificacion, Date fechaNacimiento, int peso, int estatura, List<Medicamento> medicamentosDiarios, List<Catalizador> habitos, List<EpisodioDolor> episodios) {
       this.nombre = nombre;
        this.noIdentificacion=noIdentificacion;
        this.fechaNacimiento=fechaNacimiento;
        this.peso = peso;
        this.estatura = estatura;
        this.medicamentosDiarios = medicamentosDiarios;
        this.habitos = habitos;
        this.episodios = episodios;
    }

    /**
     * Metodo constructor con los datos relacionados al usuario
     * @param nombre El nombre del paciente
     * @param noIdentificacion el numero de identificaicon del paciente
     * @param fechaNacimiento la fecha de nacimiento del paciente
     */
    public Paciente(String nombre, int noIdentificacion, Date fechaNacimiento) {
        this.nombre = nombre;
        this.noIdentificacion=noIdentificacion;
        this.fechaNacimiento=fechaNacimiento;
        habitos = new ArrayList<Catalizador>();
        medicamentosDiarios = new ArrayList<Medicamento>();
        episodios = new ArrayList<EpisodioDolor>();
    }

    //-----------------------------------------------------
    // Metodos
    //-----------------------------------------------------

    /**
     * Metodo que retorna el nombre del paciente
     * @return el nombre del paciente
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Metodo que se encarga de modificar el nombre del paciente por el nombre dado como parametro
     * @param nombre el nuevo nombre del paciente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que se encarga de retornar el numero de identificacion del paciente
     * @return el numero de identificacion del paciente
     */
    public int getNoIdentificacion() {
        return noIdentificacion;
    }

    /**
     * Metodo que cambia el numero de identificiacion por el numero dado como parametro
     * @param noIdentificacion el nuevo numero de identificacion
     */
    public void setNoIdentificacion(int noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    /**
     * Metodo que se encarga de retornar la fecha de nacimiento del paciente
     * @return la fecha de nacimiento del paciente
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Metodo que se encarga de cambiar la fecha de nacimiento del paciente por la fecha dada como parametro
     * @param fechaNacimiento la nueva fecha de nacimiento
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    /**
     * Metodo que se encarga de retornar el peso del paciente
     * @return el peso del paciente
     */
    public int getPeso() {
        return peso;
    }

    /**
     * Metodo que se encarga de modificar el peso del paciente por el nuevo peso dado como parametro
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
     * Metodo que modifica la estatura del paciente, por la estatura dada como parametro
     * @param estatura la nueva estatura del paciente
     */
    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    /**
     * Lista de los medicamentos diarios que toma el paciente
     * @return la lista de los medicamentos que actualmente toma el paciente
     */
    public List<Medicamento> getMedicamentosDiarios() {
        return medicamentosDiarios;
    }

    /**
     * Metodo que cambia los medicamentos diarios por la nueva lista dada como parametro
     * @param medicamentosDiarios los nuevos medicamentos del paciente
     */
    public void setMedicamentosDiarios(List<Medicamento> medicamentosDiarios) {
        this.medicamentosDiarios = medicamentosDiarios;
    }

    /**
     * Metodo que retorna los habitos del paciente
     * @return los habitos del paciente (Catalizadores)
     */
    public List<Catalizador> getHabitos() {
        return habitos;
    }

    /**
     * Metodo que se encarga de reemplazar los habitos del paciente por los que se reciben como parametro
     * @param habitos los nuevos habitos del paciente (Catalizadores)
     */
    public void setHabitos(List<Catalizador> habitos) {
        this.habitos = habitos;
    }

    /**
     *Metodo que retorna la lista de los episodios de dolor del paciente 
     * @return la lista de los episodios de dolor
     */
    public List<EpisodioDolor> getEpisodios() {
        return episodios;
    }

    /**
     * Metodo que se encarga de cambiar los episodios de dolor por la lista dada como parametro
     * @param episodios los nuevos episodios de dolor
     */
    public void setEpisodios(List<EpisodioDolor> episodios) {
        this.episodios = episodios;
    }

    /**
     * Metodo que se encarga de decidir si dos pacientes son igual o no.
     * @param obj El objeto con el cual se va a comparar el paciente
     * @return true si el tienen el mismo numero de identificacion y obj es paciente, false de lo contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
        if (this.noIdentificacion != other.noIdentificacion) {
            return false;
        }
        return true;
    }
}