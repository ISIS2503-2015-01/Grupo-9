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
public class EpisodioDolor {
    
    private String hora;
    
    private Date fecha;
    
    private String localizacion;
    
    private String intensidad;
    
    private int nivelDolor;
    
    private List<Sintoma> sintomas; 
    
    private List<Catalizador> catalizadores;
    
    private List<Medicamento> medicamentosActuales;
    
    

    public EpisodioDolor() {
        sintomas = new ArrayList<Sintoma>();
        catalizadores = new ArrayList<Catalizador>();
        medicamentosActuales = new ArrayList<Medicamento>();
    }

    public EpisodioDolor(String hora, Date fecha, String localizacion, String intensidad, int nivelDolor, List<Sintoma> sintomas, List<Catalizador> catalizadores, List<Medicamento> medicamentosActuales) {
        this.hora = hora;
        this.fecha = fecha;
        this.localizacion = localizacion;
        this.intensidad = intensidad;
        this.nivelDolor = nivelDolor;
        this.sintomas = sintomas;
        this.catalizadores = catalizadores;
        this.medicamentosActuales = medicamentosActuales;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    public String getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(String intensidad) {
        this.intensidad = intensidad;
    }

    public int getNivelDolor() {
        return nivelDolor;
    }

    public void setNivelDolor(int nivelDolor) {
        this.nivelDolor = nivelDolor;
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
    
    
    
    
}
