/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.interfaces;

import java.util.List;
import javax.ejb.Remote;
import migrainetracking.excepciones.OperacionInvalidaException;

/**
 *
 * @author Personal
 */
@Remote
public interface IServiciosCRUDMockRemote {
    
    /**
     * Agrega un nuevo registro a la presistence unit
     * @param o - Es el objeto generico a agregar
     * @return El id del objeto creado
     * @throws OperacionInvalidaException si no se puede crear el objeto
     */
    public Long create(Object o) throws OperacionInvalidaException;
    
    /**
     * Borra un registro en la presistence unit
     * @param o - Es el objeto generico a agregar
     * @return El id del objeto borrado
     * @throws OperacionInvalidaException si el objeto no existe
     */
    public Long delete(Object o) throws OperacionInvalidaException;
    
    /**
     * Actualiza un registro en la presistence unit
     * @param o - Es el objeto generico a agregar
     * @return El id del objeto actualizado
     */
    public Long update(Object o);
    
    /**
     * Lee todos los registros de la Persistance Unit
     * @param clase - Es la clase del objeto que se quiere leer. 
     *       [ Usar --->  Doctor mock = ""; List<Doctor> l = (Doctor) getAll( mock.getClass() ) ]
     * @return La lista 
     */
    public List<Object> getAll(Class clase);
}