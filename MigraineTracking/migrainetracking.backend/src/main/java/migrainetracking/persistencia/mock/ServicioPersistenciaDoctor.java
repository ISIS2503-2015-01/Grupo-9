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
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.Doctor;
import migrainetracking.dto.Paciente;
import migrainetracking.excepciones.OperacionInvalidaException;
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
    public static ServicioPersistenciaDoctor instancia;
    
    private List<Doctor> doctores;

    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    public ServicioPersistenciaDoctor() {
        if (doctores == null) {
            doctores = new ArrayList<Doctor>();
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

     public static ServicioPersistenciaDoctor getInstance(){
        if(instancia == null || true){
            return new ServicioPersistenciaDoctor();
        }
            return instancia;
    }
     
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        Doctor newDoc = (Doctor) obj;
        if (findById(Doctor.class, newDoc.getNoIdentificacion()) == null) {
            doctores.add(newDoc);
            Utils.printf("New doctor(" + newDoc.getNombre() + ") was added");
        } else {
            throw new OperacionInvalidaException("El doctor, con ese id, ya existe en el sistema");
        }
    }

    @Override
    public void update(Object obj) {
        Doctor toEdit = (Doctor) obj;
        for (int i = 0; i < doctores.size(); i++) {
            Doctor tempDoc = doctores.get(i);
            if (toEdit.equals(tempDoc)) {
                doctores.set(i, toEdit);
                Utils.printf("Doctor(" + tempDoc.getNombre() + ")was UPDATED");
                break;
            }
        }
    }

    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        Doctor oldDoc = (Doctor) obj;
        if (findById(Doctor.class, oldDoc.getNoIdentificacion()) != null) {
            doctores.remove(oldDoc);
            Utils.printf("Doctor(" + oldDoc.getNombre() + ")was deleted");
        } else {
            throw new OperacionInvalidaException("El doctor que quiere eliminar no existe en el sistema");
        }
    }

    @Override
    public List findAll(Class c) {
        return this.doctores;
    }

    @Override
    // El id en este caso es la cedula del doctor.
    public Doctor findById(Class c, Object id) {
        int noId = Integer.parseInt(id.toString());
        for (Doctor doctor : doctores) {
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
