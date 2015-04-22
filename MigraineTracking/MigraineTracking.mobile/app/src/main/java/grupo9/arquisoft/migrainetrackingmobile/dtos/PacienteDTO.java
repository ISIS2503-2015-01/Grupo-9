/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo9.arquisoft.migrainetrackingmobile.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class PacienteDTO {
    
    //-----------------------------------------------------
    // Atributos
    //-----------------------------------------------------
    
    private int doctorid;
    /**
     * El nombre del paciente
     */
    private String nombre;

    private String username;

    private String password;
    
    /**
     * El número de identificación del paciente
     */
    private int noIdentificacion;
    
    /**
     * Fecha de Nacimiento del Usuario
     */
    private String fechaNacimiento;

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
    private List<MedicamentoDTO> medicamentosDiarios;
    
    /**
     * La lista de habitos que tiene el paciente
     */
    private List<CatalizadorDTO> habitos; // Estos deben ser de tipo habito...
    
    /**
     * La lista con los episodios de dolor que ha tenido el paciente
     */
    private List<EpisodioDolorDTO> episodios;

    //-----------------------------------------------------
    // Constructores
    //-----------------------------------------------------
    
    /**
     * Metodo constructor con argumentos
     * @param nombre El nombre del paciente
     * @param noIdentificacion el numero de identificacion del paciente
     * @param fechaNacimiento la fecha de nacimiento del paciente
     * @param peso el peso del paciente
     * @param estatura la estatura del paciente
     */
    public PacienteDTO(String username, String password,int doctorid,String nombre, int noIdentificacion, String fechaNacimiento, int peso, int estatura) {
       this.nombre = nombre;
        this.noIdentificacion=noIdentificacion;
        this.fechaNacimiento=fechaNacimiento;
        this.peso = peso;
        this.estatura = estatura;
        this.doctorid=doctorid;
        this.username=username;
        this.password=password;
        habitos = new ArrayList<CatalizadorDTO>();
        medicamentosDiarios = new ArrayList<MedicamentoDTO>();
        episodios = new ArrayList<EpisodioDolorDTO>();
    }

    /**
     * Metodo constructor con los datos relacionados al usuarios sin argumentos
     */
    public PacienteDTO() {
        habitos = new ArrayList<CatalizadorDTO>();
        medicamentosDiarios = new ArrayList<MedicamentoDTO>();
        episodios = new ArrayList<EpisodioDolorDTO>();
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
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Metodo que se encarga de cambiar la fecha de nacimiento del paciente por la fecha dada como parametro
     * @param fechaNacimiento la nueva fecha de nacimiento
     */
    public void setFechaNacimiento(String fechaNacimiento) {
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
    public List getMedicamentosDiarios() {
        return medicamentosDiarios;
    }

    /**
     * Metodo que cambia los medicamentos diarios por la nueva lista dada como parametro
     * @param medicamentosDiarios los nuevos medicamentos del paciente
     */
    public void setMedicamentosDiarios(List medicamentosDiarios) {
        this.medicamentosDiarios = medicamentosDiarios;
    }

    /**
     * Metodo que retorna los habitos del paciente
     * @return los habitos del paciente (Catalizadores)
     */
    public List getHabitos() {
        return habitos;
    }

    /**
     * Metodo que se encarga de reemplazar los habitos del paciente por los que se reciben como parametro
     * @param habitos los nuevos habitos del paciente (Catalizadores)
     */
    public void setHabitos(List<CatalizadorDTO> habitos) {
        this.habitos = habitos;
    }

    /**
     *Metodo que retorna la lista de los episodios de dolor del paciente 
     * @return la lista de los episodios de dolor
     */
    public List getEpisodios() {
        return episodios;
    }

    /**
     * Metodo que se encarga de cambiar los episodios de dolor por la lista dada como parametro
     * @param episodios los nuevos episodios de dolor
     */
    public void setEpisodios(List<EpisodioDolorDTO> episodios) {
        this.episodios = episodios;
    }

    public int getDoctorid(){
        return doctorid;
    }

    public void setDoctorid(int doctorid)
    {
        this.doctorid=doctorid;
    }

    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}

    public String getPassword(){return password;}
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
        final PacienteDTO other = (PacienteDTO) obj;
        if (this.noIdentificacion != other.noIdentificacion) {
            return false;
        }
        return true;
    }
}
