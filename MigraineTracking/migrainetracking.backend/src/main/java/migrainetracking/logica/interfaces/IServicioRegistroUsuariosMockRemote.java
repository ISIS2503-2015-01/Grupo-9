/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.interfaces;

import java.util.List;
import javax.ejb.Remote;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.excepciones.OperacionInvalidaException;

/**
 *
 * @author Personal
 */
@Remote
public interface IServicioRegistroUsuariosMockRemote {
    /**
     * Crea un nuevo usuario
     * @param nuevo El usuario nuevo
     * @throws OperacionInvalidaException Excepcion en caso de error operacional
     */
    public Long crearUsuario(Object nuevo) throws OperacionInvalidaException;
    
    /**
     * Crea elimina un usuario con un numero de identificacion
     * @throws OperacionInvalidaException Excepcion en caso de error operacional
     * @throws NoExisteException en caso de que no exista el paciente que se quiere eliminar.
     */
    public Long eliminarUsuario( Object viejo ) throws OperacionInvalidaException, NoExisteException;
    
    /**
     * <b>pre:</b> El usuario que se va a actualizar ya existe
     * @param actualizar
     * @return 
     */
    public Long actualizarUsuario( Object actualizar );
    
    /**
     * Devuelve la lista de usuarios de todo el sistema
     * @param c Clase del Usuario que se quiere retener los datos.
     * @return la lista de usuarios.
     *         null en caso de que la clase introducida no sea de tipo Paciente o Doctor.
     */
    public List getUsuarios(Class c);
}
