/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ServerSide.Models.DTOs;

import java.util.Date;

/**
 *
 * @author Personal
 */
public class MedicamentoDTO {
    
    private String referencia;
       
    private Date fechaDePrescripcion;
    
    
    public MedicamentoDTO(){
        
    }

    
    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Date getFechaDePrescripcion() {
        return fechaDePrescripcion;
    }

    public void setFechaDePrescripcion(Date fechaDePrescripcion) {
        this.fechaDePrescripcion = fechaDePrescripcion;
    }
    
    
}
