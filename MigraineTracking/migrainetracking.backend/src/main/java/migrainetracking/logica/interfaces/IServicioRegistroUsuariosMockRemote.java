/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.interfaces;

import java.util.List;
import javax.ejb.Remote;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.dto.PacienteDTO;
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
     * @return el id del usuario creado 
     * @throws OperacionInvalidaException Excepcion en caso de error operacional
     */
    public Long crearUsuario(Object nuevo) throws OperacionInvalidaException;
    
    /**
     * Crea elimina un usuario con un numero de identificacion
     * @param viejo el usuario que se va a eliminar
     * @return el id del usuario eliminado
     * @throws OperacionInvalidaException Excepcion en caso de error operacional
     * @throws NoExisteException en caso de que no exista el paciente que se quiere eliminar.
     */
    public Long eliminarUsuario( Object viejo ) throws OperacionInvalidaException, NoExisteException;
    
    /**
     * <b>pre:</b> El usuario que se va a actualizar ya existe
     * @param actualizar
     * @return el id del usuario actualizado
     */
    public Long actualizarUsuario( Object actualizar );
    
    /**
     * Devuelve la lista de usuarios de todo el sistema
     * @param c Clase del Usuario que se quiere retener los datos.
     * @return la lista de usuarios.
     *         null en caso de que la clase introducida no sea de tipo Paciente o Doctor.
     */
    public List getUsuarios(Class c);
    
     
    /**
     * Metodo que retorna el usuario cuy id es igual al id dado por parametro
     * @param c la clase a la que pertenece el usuario que se esta buscando
     * @param id el id del usuario que se esta busando
     * @return el usuario cuyo id es igual al id dado por parametro
     */
    public Object getUsuarioById(Class c , Long id);
    
    /**
     * Metodo que se encarga de retornar los pacientes de un doctor
     * @param docId el id del doctor del cual se quiere conocer los pacientes
     * @return los pacientes del doctor cuyo id se dio por parametro
     */
    public List<PacienteDTO> getPacientesDeDoctor(Long docId) throws NoExisteException;
   
    /**
     * Metodo que retorna los medicamentos que toma el paciente cuyo id es dado por parametro
     * @param pacId el id del paciente del cual se quiere conocer los medicamentos diarios
     * @return  una lista con los medicamentos que toma el paciente cuyo id se dio por parametro
     */
    public List<MedicamentoDTO> getMedicamentosDiariosPaciente(Long pacId);
    

    /**
     * Metodo que retorna los habitos del paciente cuyo id se da por parametro
     * @param pacId el id del paciente del cual se quiere conocer los habitos
     * @return los habitos del paciente cuyo id se da por parametro
     */
    public List<CatalizadorDTO> getHabitosPaciente(Long pacId);
}
