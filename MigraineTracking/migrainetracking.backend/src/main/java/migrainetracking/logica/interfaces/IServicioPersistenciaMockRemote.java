/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.interfaces;

import migrainetracking.excepciones.OperacionInvalidaException;
import javax.ejb.Remote;

/**
 *
 * @author estudiante
 */
@Remote
public interface IServicioPersistenciaMockRemote {
   
    /**
     * Crea un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere crear.
     * @throws migrainetracking.excepciones.OperacionInvalidaException si no se puede crear el objeto
     */
    public void create(java.lang.Object obj)throws OperacionInvalidaException;

    /**
     * Modifica un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere modificar.
     */
    public void update(java.lang.Object obj);

    /**
     * Elimina un objeto dentro de la persistencia del sistema.
     * @param obj Objeto que representa la instancia de la entidad que se quiere borrar.
     * @throws migrainetracking.excepciones.OperacionInvalidaException si no se puede borrar el objeto
     */
    public void delete(java.lang.Object obj)throws OperacionInvalidaException;

    /**
     * Devuelve una lista con de todos los elementos de una clase dada que se encuentran en el sistema.
     * @param c Clase cuyos objetos quieren encontrarse en el sistema.
     * @return list Listado de todos los objetos de una clase.
     */
    public java.util.List findAll(java.lang.Class c);

    /**
     * Retorna la instancia de una entidad dado un identificador y la clase de la entidad.
     * @param c Clase de la instancia que se quiere buscar.
     * @param id Identificador unico del objeto.
     * @return obj Resultado de la consulta.
     */
    public java.lang.Object findById(java.lang.Class c, Object id);
    
}
