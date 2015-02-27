/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.DoctorDTO;
import migrainetracking.dto.PacienteDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.conexion.PersistenceManager;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaDoctor;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaPaciente;
import migrainetracking.utils.Utils;
import org.fluttercode.datafactory.impl.DataFactory;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaDoctor extends PersistenceServiceMaster implements IServicioPersistenciaDoctor {

    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    
    /**
     * Atributo para manejar la instanciacion
     */
    public static ServicioPersistenciaDoctor instancia;
    
    
    
    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    
    /**
     * Constructor sin argumentos
     */
    public ServicioPersistenciaDoctor() {
        super(); // inicializo el entity manager
        
    }

    /**
     * Metodo que se encarga de retornar la instancia de la clase
     * @return la instancia de la clase
     */
    public static ServicioPersistenciaDoctor getInstance(){
        boolean sinSingleton = true;
        if(instancia == null || sinSingleton ){
            return new ServicioPersistenciaDoctor();
        }
            return instancia;
    }
     
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------
    
    /**
     * Metodo que se encarga de crear un doctor
     * @param obj el doctor que se va a crear
     * @throws OperacionInvalidaException si no se puede crear el doctor
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        DoctorDTO newDoc = (DoctorDTO) obj;
        if (findById(DoctorDTO.class, newDoc.getNoIdentificacion()) == null) {
            
            Utils.printf("New doctor(" + newDoc.getNombre() + ") was added");
        } else {
            throw new OperacionInvalidaException("El doctor, con ese id, ya existe en el sistema");
        }
    }

    /**
     * Metodo para editar la informacion de un doctor
     * @param obj el doctor que se va a editar
     */
    @Override
    public void update(Object obj) {
        DoctorDTO toEdit = (DoctorDTO) obj;
        
    }

    /**
     * Metodo que se encarga de eliminar al doctor que se da por parametro
     * @param obj el doctor a eliminar
     * @throws OperacionInvalidaException si no se puede eliminar 
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        DoctorDTO oldDoc = (DoctorDTO) obj;
        if (findById(DoctorDTO.class, oldDoc.getNoIdentificacion()) != null) {
            
            Utils.printf("Doctor(" + oldDoc.getNombre() + ")was deleted");
        } else {
            throw new OperacionInvalidaException("El doctor que quiere eliminar no existe en el sistema");
        }
    }

    /**
     *Metodo que se encarga de retornar a todos los doctores 
     * @param c la clase de los elementos que se quiere retornar
     * @return una lista con los doctores
     */
    @Override
    public List findAll(Class c) {
        return null;
    }

    /**
     * Metodo que se encarga de retornar al doctor con el id dado como parametro
     * @param c la clase del objeto que se va a buscar, en este caso doctor
     * @param id el id del doctor que se va a buscar
     * @return el doctor con el id dado por parametro
     */
    @Override
    // El id en este caso es la cedula del doctor.
    public DoctorDTO findById(Class c, Object id) {
        int noId = Integer.parseInt(id.toString());
       
        return null;
    }
    
    //------------------------------------------------------------------------
    // Metodos auxiliares
    //------------------------------------------------------------------------
}