/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.interfaces;

import java.util.List;
import javax.ejb.Local;
import migrainetracking.dto.Usuario;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.excepciones.OperacionInvalidaException;


/**
 *
 * @author Personal
 */
@Local
public interface IServicioRegistroUsuariosMockLocal extends  IServiciosCRUDMockLocal{
    
    /**
     * Crea un nuevo usuario
     * @param nuevo El usuario nuevo
     * @throws OperacionInvalidaException Excepcion en caso de error operacional
     */
    public void crearUsuario(Usuario nuevo) throws OperacionInvalidaException;
    
    /**
     * Crea elimina un usuario con un numero de identificacion
     * @param noIdentificacion El numero de identicacion 
     * @throws OperacionInvalidaException Excepcion en caso de error operacional
     * @throws NoExisteException en caso de que no exista un paciente con ese noIdentificacion asociado
     */
    public void eliminarUsuario( int noIdentificacion ) throws OperacionInvalidaException, NoExisteException;
    
    /**
     * Devuelve la lista de usuarios de todo el sistema
     * @return la lista de usuarios
     */
    public List<Usuario> getUsuarios();
  
}
