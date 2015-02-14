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
public class Paciente extends Usuario{
    
    //-----------------------------------------------------
    // Atributos
    //-----------------------------------------------------
    
    private int peso;
    
    private int estatura;
    
    /**
     * Los medicamentos que toma a diario el paciente
     */
    private List<Medicamento> medicamentosDiarios;
    
    private List<Catalizador> habitos; // Estos deben ser de tipo habito...
    
    private List<EpisodioDolor> episodios;

    //-----------------------------------------------------
    // Constructores
    //-----------------------------------------------------
    
    public Paciente(String nombre, int noIdentificacion, Date fechaNacimiento, int peso, int estatura, List<Medicamento> medicamentosDiarios, List<Catalizador> habitos, List<EpisodioDolor> episodios) {
        super(nombre, noIdentificacion, fechaNacimiento);
        this.peso = peso;
        this.estatura = estatura;
        this.medicamentosDiarios = medicamentosDiarios;
        this.habitos = habitos;
        this.episodios = episodios;
    }

           
    public Paciente(String nombre, int noIdentificacion, Date fechaNacimiento) {
        super(nombre, noIdentificacion, fechaNacimiento);
        habitos = new ArrayList<Catalizador>();
        medicamentosDiarios = new ArrayList<Medicamento>();
        episodios = new ArrayList<EpisodioDolor>();
    }

    //-----------------------------------------------------
    // Metodos
    //-----------------------------------------------------

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
    
    
}
