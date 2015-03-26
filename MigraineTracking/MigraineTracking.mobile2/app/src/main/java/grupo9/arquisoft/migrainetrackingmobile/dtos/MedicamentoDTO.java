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
    private String nombre;
    
    /**
     * La cantidad de veces que se debe tomar el medicamento al dia
     */
    private int cantidadVecesAlDia;
    
    /**
     * Los intervalos de horas en los que se debe tomar el medicamento
     */
    private int intervaloHoras;
    
    /**
     * La cantidad que se debe tomar del medicamento cada vez
     */
    private int miligramos;
    
    /**
     * La fecha en que fue recetado el medicamento. En caso de no ser recetado por un medico es null.
     */
    private Date fechaRecetado;
    
    /**
     * El identificador del medicamento
     */
    private Long id;

    //-----------------------------------------------
    //Constructor
    //-----------------------------------------------
    
    /**
     * Constructor con argumentos
     * @param nombre el nombre del medicamento
     * @param cantidadVecesAlDia la cantidad de veces al dia que se debe tomar el medicamento
     * @param intervaloHoras el intervalo de horas en que se debe tomar el medicamento
     * @param miligramos la cantidad que se debe tomar del medicamento cada vez
     */
    public MedicamentoDTO(String nombre, int cantidadVecesAlDia, int intervaloHoras, int miligramos) {
        this.nombre = nombre;
        this.cantidadVecesAlDia = cantidadVecesAlDia;
        this.intervaloHoras = intervaloHoras;
        this.miligramos = miligramos;
    }

    /**
     * Constructor sin argumentos
     */
    public MedicamentoDTO() {
    }

    //-----------------------------------------------
    //Metodos
    //-----------------------------------------------
    
    /**
     * Metodo que retorna el nombre del medicamento
     * @return el nombre del medicamento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que cambia el nombre del medicamento por el nombre dado como parametro
     * @param nombre el nuevo nombre del medicamento
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que retorna la cantidad de veces al dia que se debe tomar el medicamento
     * @return la cantidad de veces al dia que se debe tomar el medicamento
     */
    public int getCantidadVecesAlDia() {
        return cantidadVecesAlDia;
    }

    /**
     * Metodo que se encarga de cambia la cantidad de veces que se debe tomar un medicamento por la nueva cantidad dada como parametro
     * @param cantidadVecesAlDia la nueva cantidad de veces que se debe tomar el medicamento al dia
     */
    public void setCantidadVecesAlDia(int cantidadVecesAlDia) {
        this.cantidadVecesAlDia = cantidadVecesAlDia;
    }

    /**
     * Metodo que retorna la cantidad de horas que deben pasar entre la tomada de un medicamento 
     * @return el intervalo de horas en las que se debe tomar el medicamento
     */
    public int getIntervaloHoras() {
        return intervaloHoras;
    }

    /**
     * Metodo que se encarga de cambiar el intervalo de horas del medicamento por la nueva cantidad dada ccomo parametro
     * @param intervaloHoras el nuevo intervalo de horas
     */
    public void setIntervaloHoras(int intervaloHoras) {
        this.intervaloHoras = intervaloHoras;
    }

    /**
     * La cantiadad en miligramos que se debe tomar del medicamento
     * @return la cantidad en miligramos que se debe tomar
     */
    public int getMiligramos() {
        return miligramos;
    }

    /**
     * Metodo que se encarga de cambiar la cantidad que se debe tomar del medicamento por la nueva cantidad dada como parametro
     * @param miligramos la nueva cantidad que se debe tomar 
     */
    public void setMiligramos(int miligramos) {
        this.miligramos = miligramos;
    }
    
    /**
     * Metodo que retorna el id del medicamento
     * @return el id del medicamento
     */
    public long getId()
    {
        return id;
    }
    
    /**
     * Metodo que cambia el id por el id dado por parametro
     * @param id el id nuevo
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /**
     * Metodo que retorna la fecha que el medicamento fue recetado
    */
    public Date getFecha()
    {
        return fechaRecetado;
    }  
    
    /**
     * Metodo que cambia la fecha por la fecha dada por parametro
     * @param fecha la nueva fecha
     */
    public void setFecha(Date fecha)
    {
        this.fechaRecetado=fecha;
    }
}