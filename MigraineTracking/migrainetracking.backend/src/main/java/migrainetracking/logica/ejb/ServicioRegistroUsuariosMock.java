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
     * 
     */
    IServicioPersistenciaDoctor persistenciaDoctor;
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
    
    public static ServicioRegistroUsuariosMock getInstance(){
        if( instancia==null || true ){
            instancia = new ServicioRegistroUsuariosMock();
        }
        return instancia;
    }
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------
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

    @Override
    public List getUsuarios(Class c) {
        if (c.equals(Doctor.class)) {
            return this.persistenciaDoctor.findAll(Doctor.class);
        } else if (c.equals(Paciente.class)) {
            return this.persistenciaPaciente.findAll(Paciente.class);
        }
        return null;
    }

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
