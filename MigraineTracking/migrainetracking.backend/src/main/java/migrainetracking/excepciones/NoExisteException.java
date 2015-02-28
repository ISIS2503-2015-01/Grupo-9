
package migrainetracking.excepciones;

/**
 * Clase de excepcion en caso de no conseguirse un objeto o lista de objetos
 * @author Maria Paula Mancipe
 */
public class NoExisteException extends Exception{
    
    //------------------------------------------------
    //Constructor
    //------------------------------------------------
    
    /**
     * Constructor de la clase
     * @param mensaje Mensaje de la excepcion
     */
    public NoExisteException(String mensaje){
        super(mensaje);
    }    
}