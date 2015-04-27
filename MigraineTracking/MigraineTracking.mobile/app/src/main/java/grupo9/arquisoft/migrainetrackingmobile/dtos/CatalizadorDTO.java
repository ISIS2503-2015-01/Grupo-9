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
     * Especificacion adicional del doctor
     */
    private String especificacion;

     //-----------------------------------------------------
    // Constructores
    //-----------------------------------------------------
    
    /**
     * Constructor de la clase(sin argumentos)
     */
    public CatalizadorDTO() {
    }


    public CatalizadorDTO(String especificacion) {

        this.especificacion = especificacion;

    }
    
     //-----------------------------------------------------
    // Metodos
    //-----------------------------------------------------



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

}