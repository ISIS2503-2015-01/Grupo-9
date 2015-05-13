/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo9.arquisoft.migrainetrackingmobile.dtos;

import java.util.Date;

/**
 *
 * @author estudiante
 */
public class MedicamentoDTO {
    
    //-----------------------------------------------
    //Atributos
    //-----------------------------------------------

    private String referencia;

    private String fechaDePrescripcion;

    //-----------------------------------------------
    //Constructor
    //-----------------------------------------------
    

    public MedicamentoDTO(String referencia, String fechaDePrescripcion) {
       this.referencia=referencia;
        this.fechaDePrescripcion=fechaDePrescripcion;
    }

    /**
     * Constructor sin argumentos
     */
    public MedicamentoDTO() {
    }

    //-----------------------------------------------
    //Metodos
    //-----------------------------------------------
    
    public void setReferencia(String referencia){this.referencia=referencia;}

    public String getReferencia(){return referencia;}

    public void setFechaDePrescripcion(String fechaDePrescripcion){this.fechaDePrescripcion=fechaDePrescripcion;}

    public String getFechaDePrescripcion(){return fechaDePrescripcion;}
}