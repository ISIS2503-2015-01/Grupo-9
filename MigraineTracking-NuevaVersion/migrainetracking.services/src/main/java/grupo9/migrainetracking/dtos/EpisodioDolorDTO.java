/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo9.migrainetracking.dtos;

import java.util.Date;

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
     * 
     */
    private int horasDeSueno;
    
    //-----------------------------------------------
    //Constructores
    //-----------------------------------------------

    public EpisodioDolorDTO() {
    }

    public EpisodioDolorDTO(Long id, Date fecha, String localizacion, int intensidadDolor, int horasDeSueno) {
        this.id = id;
        this.fecha = fecha;
        this.localizacion = localizacion;
        this.intensidadDolor = intensidadDolor;
        this.horasDeSueno = horasDeSueno;
    }
    
    //-----------------------------------------------
    //Metodos
    //-----------------------------------------------

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

    public int getHorasDeSueno() {
        return horasDeSueno;
    }

    public void setHorasDeSueno(int horasDeSueno) {
        this.horasDeSueno = horasDeSueno;
    }
    
    
    
}
