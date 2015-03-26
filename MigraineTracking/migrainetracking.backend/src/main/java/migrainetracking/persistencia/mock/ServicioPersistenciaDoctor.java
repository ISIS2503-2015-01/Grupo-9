/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transaction;
import javax.transaction.UserTransaction;
import migrainetracking.dto.DoctorDTO;
import migrainetracking.dto.PacienteDTO;
import migrainetracking.excepciones.NoExisteException;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.Entities.Doctor;
import migrainetracking.persistencia.conexion.PersistenceManager;
import migrainetracking.persistencia.converters.DoctorConverter;
import migrainetracking.persistencia.converters.PacienteConverter;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaDoctor;
import migrainetracking.utils.Utils;

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
        super(); 
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
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Object obj) throws OperacionInvalidaException {
        DoctorDTO newDoc = (DoctorDTO) obj;
        if ( this.entityMgr.find( Doctor.class,newDoc.getNoIdentificacion() ) != null ){
               throw new OperacionInvalidaException("El doctor que quiere agregar ya existe en el sistema");  
        }
        
            EntityTransaction tran = this.entityMgr.getTransaction() ;
            Doctor d = DoctorConverter.dtoToEntity(newDoc);
            try { 
                tran.begin();
                this.entityMgr.persist( d );
                tran.commit();
                this.entityMgr.refresh(d);
                Utils.printf("New doctor(" + newDoc.getNombre() + ") was added");
            }  catch( Exception e){
                e.printStackTrace();
                tran.rollback();
                Utils.printf(">>>>> EXCEPCION : "+e.getMessage());
            }finally {
            entityMgr.clear();
            entityMgr.close();
        }
    }

    /**
     * Metodo para editar la informacion de un doctor
     * @param obj el doctor que se va a editar
     */
    @Override
    public void update(Object obj) {
        DoctorDTO existe = (DoctorDTO)obj;
        Doctor doc=DoctorConverter.dtoToEntity(existe);
        EntityTransaction tran=this.entityMgr.getTransaction();
        try
        {
            tran.begin();
            this.entityMgr.merge(doc);
            tran.commit();
            Utils.printf("El doctor ha sido actualizado");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            tran.rollback();
            Utils.printf("Se ha producido un error: " + e.getMessage());
        }finally {
            entityMgr.clear();
            entityMgr.close();
        }
    }

    /**
     * Metodo que se encarga de eliminar al doctor que se da por parametro
     * @param obj el doctor a eliminar
     * @throws OperacionInvalidaException si no se puede eliminar 
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        DoctorDTO eliminar = (DoctorDTO)obj;
        if(findById(DoctorDTO.class, eliminar)==null)
        {
            throw new OperacionInvalidaException("No se puede eliminar el objeto porque no existe");
        }
        Doctor cat = DoctorConverter.dtoToEntity(eliminar);
        EntityTransaction tran = this.entityMgr.getTransaction();
        try
        {
            tran.begin();
            this.entityMgr.remove(cat);
            tran.commit();
            this.entityMgr.refresh(cat);
            Utils.printf("El doctor ha sido eliminado");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            tran.rollback();
            Utils.printf("Se ha producido un error: " + e.getMessage());
        }finally {
            entityMgr.clear();
            entityMgr.close();
        }
    }

    /**
     *Metodo que se encarga de retornar a todos los doctores 
     * @param c la clase de los elementos que se quiere retornar
     * @return una lista con los doctores
     */
    @Override
    public List findAll(Class c) {
        Query q =  this.entityMgr.createQuery("SELECT d FROM Doctor d",Doctor.class);
        q.setMaxResults(5);
        List<Doctor> Doctores = q.getResultList();
        
        return DoctorConverter.entityToDtoList(Doctores);
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
        Doctor resp ;
        try{
              resp =  this.entityMgr.find(Doctor.class, noId);
        }catch(NoResultException e){
            return null;
        }
        return DoctorConverter.entityToDTO(resp);
    }
    
    @Override
    public List<PacienteDTO> getDocsPacients(int noId) throws NoExisteException{
        Doctor d = this.entityMgr.find(Doctor.class, noId);
        if(d!=null)
            return PacienteConverter.entityToDtoList( d.getPacientes() );
        else
            throw new NoExisteException("El doctor del cual se quieren los pacientes no existe");
    } 
}
