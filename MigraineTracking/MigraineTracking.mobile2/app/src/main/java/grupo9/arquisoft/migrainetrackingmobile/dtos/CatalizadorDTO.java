package grupo9.arquisoft.migrainetrackingmobile.dtos;


/**
 *
 * @author estudiante
 */
public class CatalizadorDTO {
    
    //-----------------------------------------------------
    // Atributos
    //-----------------------------------------------------
    
    /**
     * El id del catalizador
     */
    private Long id;
    
    /**
     * El tipo del catalizador
     * Puede ser un alimento, actividad fisica, bebida, o habito
     */
    private String tipo; 
    
    /**
     * Especificacion adicional del doctor
     */
    private String especificacion;
    
    /**
     * La cantidad de veces que se ha hecho o ingerido el catalizador
     */
    private int frecuencia;

     //-----------------------------------------------------
    // Constructores
    //-----------------------------------------------------
    
    /**
     * Constructor de la clase(sin argumentos)
     */
    public CatalizadorDTO() {
    }

    /**
     * Constructor de la clase(con argumentos)
     * @param id
     * @param tipo
     * @param especificacion
     * @param frecuencia 
     */
    public CatalizadorDTO(Long id, String tipo, String especificacion, int frecuencia) {
        this.tipo = tipo;
        this.especificacion = especificacion;
        this.frecuencia = frecuencia;
        this.id = id;
    }
    
     //-----------------------------------------------------
    // Metodos
    //-----------------------------------------------------

    /**
     * Retorna el id del catalizador
     * @return el id del catalizador
     */
    public Long getId(){
        return this.id;
    }
    
    /**
     * Cambia el id del catalizador por el id dado como parametro
     * @param id el nuevo id del catalizador
     */
    public void setId(Long id){
        this.id = id;
    }
    /**
     * Devuelve el tipo del catalizador
     * @return el tipo
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Modifica el tipo del catalizador
     * @param tipo puede ser actividad fisica, habito, bebida, alimento
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve la especificacion del catalizador
     * @return la especificacion
     */
    public String getEspecificacion() {
        return especificacion;
    }

    /**
     * Modifica la especificacion del catalizador
     * @param especificacion Comentarios que tenga que agregar el doctor
     */
    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    /**
     * Devuelve la frecuencia del catalizador
     * @return la frecuencia
     */
    public int getFrecuencia() {
        return frecuencia;
    }
    
    /**
     * Modifica la frecuencia del catalizador
     * @param frecuencia un numero de 1 a 10 
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
}