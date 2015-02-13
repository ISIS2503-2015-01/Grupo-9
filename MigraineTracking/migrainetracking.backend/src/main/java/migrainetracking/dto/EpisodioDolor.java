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
    
    private Date fecha;
    
    private String localizacion;
    
    private int intensidadDolor;
    
    // Aproximado de las horas diarias promdedio que el paciente durmio en los ultimos 3 dias previos. .
    private int horasDeSueño;
    
    private List<Sintoma> sintomas; 
    
    private List<Catalizador> catalizadores;
    
    private List<Medicamento> medicamentosActuales;
    
    public EpisodioDolor() {
        sintomas = new ArrayList<Sintoma>();
        catalizadores = new ArrayList<Catalizador>();
        medicamentosActuales = new ArrayList<Medicamento>();
    }

    public EpisodioDolor( Date fecha, String localizacion, List<Sintoma> sintomas,int horasDeSueño, int intensidadDolor, List<Catalizador> catalizadores, List<Medicamento> medicamentosActuales) {
        this.fecha = fecha;
        this.localizacion = this.localizacion; 
        this.intensidadDolor = intensidadDolor;
        this.horasDeSueño = horasDeSueño;
        this.sintomas = sintomas;
        this.catalizadores = catalizadores;
        this.medicamentosActuales = medicamentosActuales;
    }

   
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIntensidadDolor() {
        return intensidadDolor;
    }

    public void setIntensidadDolor(int intensidadDolor) {
        this.intensidadDolor = intensidadDolor;
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

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public int getHorasDeSueño() {
        return horasDeSueño;
    }

    public void setHorasDeSueño(int horasDeSueño) {
        this.horasDeSueño = horasDeSueño;
    }
    
    
    
    
}
