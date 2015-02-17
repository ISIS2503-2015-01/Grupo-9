/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.ejb;

import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.Doctor;
import migrainetracking.dto.Paciente;

import migrainetracking.excepciones.NoExisteException;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.logica.interfaces.IServicioRegistroUsuariosMockRemote;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaDoctor;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaPaciente;
import migrainetracking.persistencia.mock.ServicioPersistenciaDoctor;
import migrainetracking.persistencia.mock.ServicioPersistenciaPaciente;

/**
 * Este bean se encarga de los servicios de registro, ya sean CRUD o de otras
 * consultas, que se realizan sobre los usuarios (i.e Doctor y Paciente)
 */
@Stateless
public class ServicioRegistroUsuariosMock implements IServicioRegistroUsuariosMockRemote {

    //---------------------------------------------------------------------------
    // Constantes
    //---------------------------------------------------------------------------
    
    /**
     *Atributo para manejar la instancia de la clase 
     */
    private static ServicioRegistroUsuariosMock instancia;
    
    //---------------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------------
    
    /**
     *Atributo para persistir a los doctores 
     */
    IServicioPersistenciaDoctor persistenciaDoctor;
    
    /**
     * Atributo para persisitir a los pacientes
     */
    IServicioPersistenciaPaciente persistenciaPaciente;

    //---------------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------------
    /**
     * Metodo Constructor de la clase
     */
    public ServicioRegistroUsuariosMock() {
        this.persistenciaDoctor = ServicioPersistenciaDoctor.getInstance();
        this.persistenciaPaciente = ServicioPersistenciaPaciente.getInstance();
    }
    
    /**
     * Metodo que se encarga de retornar la instacia de la clase
     * @return la instancia de la clase
     */
    public static ServicioRegistroUsuariosMock getInstance(){
        if( instancia==null || true ){
            instancia = new ServicioRegistroUsuariosMock();
        }
        return instancia;
    }
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------
    
    /**
     * Metodo que se encarga de crear un nuevo ususario (Paciente o Doctor)
     * @param nuevo el nuevo usuario a crear
     * @return el id del usuario que se creo
     * @throws OperacionInvalidaException si no se puede crear el ususario
     */
    @Override
    public Long crearUsuario(Object nuevo) throws OperacionInvalidaException {
        if (nuevo instanceof Doctor) {
            this.persistenciaDoctor.create(nuevo);
            return (long) ((Doctor) nuevo).getNoIdentificacion();
        } else if (nuevo instanceof Paciente) {
            this.persistenciaPaciente.create(nuevo);
            return (long) ((Paciente) nuevo).getNoIdentificacion();
        } else {
            return (long) -1;
        }
    }

    /**
     * Metodo que se encarga de eliminar un usuario del sistema (Paciente o Doctor)
     * @param viejo el usuario a eliminar
     * @return el id del usuario que se elimino
     * @throws OperacionInvalidaException si no se puede eliminar el usuario
     * @throws NoExisteException si el usuario a ser eliminado no existe
     */
    @Override
    public Long eliminarUsuario(Object viejo) throws OperacionInvalidaException, NoExisteException {
        if (viejo instanceof Doctor) {
            this.persistenciaDoctor.delete(viejo);
            return (long) ((Doctor) viejo).getNoIdentificacion();
        } else if (viejo instanceof Paciente) {
            this.persistenciaPaciente.delete(viejo);
            return (long) ((Paciente) viejo).getNoIdentificacion();
        } else {
            return (long) -1;
        }
    }

    /**
     * Metodo que se encarga de retornar a todos los usuarios de una clase dada por parametro
     * @param c la clase de usuarios que se quiere
     * @return la lista de los usuarios de la clase que se dio como parametro
     */
    @Override
    public List getUsuarios(Class c) {
        if (c.equals(Doctor.class)) {
            return this.persistenciaDoctor.findAll(Doctor.class);
        } else if (c.equals(Paciente.class)) {
            return this.persistenciaPaciente.findAll(Paciente.class);
        }
        return null;
    }

    /**
     * Metodo que se encarga de actualizar un usuario
     * @param actualizar el usuario a actualizar
     * @return el id del usuario actualizado
     */
    @Override
    public Long actualizarUsuario(Object actualizar) {
        if (actualizar instanceof Doctor) {
            this.persistenciaDoctor.update(actualizar);
            return (long) ((Doctor) actualizar).getNoIdentificacion();
        } else if (actualizar instanceof Paciente) {
            this.persistenciaPaciente.update(actualizar);
            return (long) ((Paciente) actualizar).getNoIdentificacion();
        }
        return (long) -1;
    }

}