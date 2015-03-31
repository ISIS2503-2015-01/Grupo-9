/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerSide.Models.DTOs;

/**
 *
 * @author Personal
 */
public class CatalizadorDTO {
    
    // Recomendar de una lista de especificaciones 
    private String especificacion;
    
    // { Actividad fisica, Comida, Bebida } ver link : http://www.webconsultas.com/migrana/factores-de-riesgo-de-la-migrana-630
    private String tipo;
    
    public CatalizadorDTO(){
        
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
