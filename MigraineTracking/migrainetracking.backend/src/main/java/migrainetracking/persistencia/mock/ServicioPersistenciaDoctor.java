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
public class ServicioPersistenciaDoctor implements IServicioPersistenciaDoctor {

    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    
    /**
     * Atributo para manejar la instanciacion
     */
    public static ServicioPersistenciaDoctor instancia;
    
    /**
     * Lista de doctores en el sistema
     */
    private List<DoctorDTO> doctores;
    
    @PersistenceContext(unitName="migraineTrackingPU")
    private EntityManager em;
    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    
    /**
     * Constructor sin argumentos
     */
    public ServicioPersistenciaDoctor() {
        try{
            this.em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        }
        catch(Exception e){}
        
        if (doctores == null) {
            doctores = new ArrayList<DoctorDTO>();
              /*Datos para prueba funcionalidad*/
//            int numData = 8;
//            int j = 0;
//            for (int i = 0; i < numData ; i++) {
//                DataFactory df = new DataFactory();
//                String nomb = df.getFirstName() +" "+ df.getLastName() ;
//                int ced = df.getNumberBetween(80000000, 110000000);
//                Date fechNac = df.getBirthDate();
//                Doctor temp = new Doctor(nomb, ced, fechNac);
//                List<Paciente> pacientes = ServicioPersistenciaPaciente.getInstance().getPacientes();
//                try{
//                    temp.setPacientes(pacientes.subList(j,j+2));
//                }
//                catch(IndexOutOfBoundsException e){
//                    // No hay pacientes en el sistema...
//                }
//                this.doctores.add(temp);
//                j+=2;
//            }   
            /*Datos para prueba de carga*/ 

        }
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
            doctores.add(newDoc);
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
        for (int i = 0; i < doctores.size(); i++) {
            DoctorDTO tempDoc = doctores.get(i);
            if (toEdit.equals(tempDoc)) {
                doctores.set(i, toEdit);
                Utils.printf("Doctor(" + tempDoc.getNombre() + ")was UPDATED");
                break;
            }
        }
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
            doctores.remove(oldDoc);
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
        // prueba
        em.createNativeQuery("SELECT u.* FROM SYS.SYSCOLUMNS u ");
        return this.doctores;
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
        for (DoctorDTO doctor : doctores) {
            if (doctor.getNoIdentificacion() == noId) {
                return doctor;
            }
        }
        return null;
    }
    
    //------------------------------------------------------------------------
    // Metodos auxiliares
    //------------------------------------------------------------------------
}