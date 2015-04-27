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
    
    /**
     * El nombre del medicamento
     */
    private String referencia;

    /**
     * La fecha en que fue recetado el medicamento. En caso de no ser recetado por un medico es null.
     */
    private Date fechaDePrescripcion;

    //-----------------------------------------------
    //Constructor
    //-----------------------------------------------
    

    public MedicamentoDTO(String referencia, Date fechaDePrescripcion) {
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

    public void setFechaDePrescripcion(Date fechaDePrescripcion){this.fechaDePrescripcion=fechaDePrescripcion;}

    public Date getFechaDePrescripcion(){return fechaDePrescripcion;}
}