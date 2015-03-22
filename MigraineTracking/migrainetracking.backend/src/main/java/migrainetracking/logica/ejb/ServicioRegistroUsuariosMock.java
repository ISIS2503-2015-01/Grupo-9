/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.DoctorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.dto.PacienteDTO;

import migrainetracking.excepciones.NoExisteException;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.logica.interfaces.IServicioRegistroUsuariosMockRemote;
import migrainetracking.persistencia.Entities.Doctor;
import migrainetracking.persistencia.Entities.Medicamento;
import migrainetracking.persistencia.Entities.Paciente;
import migrainetracking.persistencia.converters.CatalizadorConverter;
import migrainetracking.persistencia.converters.MedicamentoConverter;
import migrainetracking.persistencia.converters.PacienteConverter;
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
        boolean sinSingleton = true;
        if( instancia==null || sinSingleton ){
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
        if (nuevo instanceof DoctorDTO) {
            this.persistenciaDoctor.create(nuevo);
            return (long) ((DoctorDTO) nuevo).getNoIdentificacion();
        } else if (nuevo instanceof PacienteDTO) {
            this.persistenciaPaciente.create(nuevo);
            return (long) ((PacienteDTO) nuevo).getNoIdentificacion();
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
        if (viejo instanceof DoctorDTO) {
            this.persistenciaDoctor.delete(viejo);
            return (long) ((DoctorDTO) viejo).getNoIdentificacion();
        } else if (viejo instanceof PacienteDTO) {
            this.persistenciaPaciente.delete(viejo);
            return (long) ((PacienteDTO) viejo).getNoIdentificacion();
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
        if (c.equals(DoctorDTO.class)) {
            return this.persistenciaDoctor.findAll(DoctorDTO.class);
        } else if (c.equals(PacienteDTO.class)) {
            return this.persistenciaPaciente.findAll(PacienteDTO.class);
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
        if (actualizar instanceof DoctorDTO) {
            this.persistenciaDoctor.update(actualizar);
            return (long) ((DoctorDTO) actualizar).getNoIdentificacion();
        } else if (actualizar instanceof PacienteDTO) {
            this.persistenciaPaciente.update(actualizar);
            return (long) ((PacienteDTO) actualizar).getNoIdentificacion();
        }
        return (long) -1;
    }
    
    @Override
    public Object getUsuarioById(Class c , Long id){
       if( c.equals( DoctorDTO.class ) )
           return (DoctorDTO) persistenciaDoctor.findById(Doctor.class, id.intValue());
       else {
           return (PacienteDTO) persistenciaPaciente.findById(Paciente.class,id.intValue());
       }
    }
    
    @Override 
    public List<PacienteDTO> getPacientesDeDoctor(Long docId){
        return persistenciaDoctor.getDocsPacients( docId.intValue() );
    }
    
    @Override
    public List<MedicamentoDTO> getMedicamentosDiariosPaciente(Long pacId){
        Paciente p = (Paciente) this.persistenciaPaciente.findById(pacId.intValue());
        List<Medicamento> entList = p.getMedicamentosDiarios();
        List<MedicamentoDTO> dtoList = new ArrayList<MedicamentoDTO>();
        for( Medicamento entMed : entList ){
            dtoList.add( MedicamentoConverter.entityToDto(entMed) );
        }
        return dtoList;
    }
    
    @Override
    public List<CatalizadorDTO> getHabitosPaciente(Long pacId)
    {
        Paciente p =  this.persistenciaPaciente.findById(pacId.intValue());
        return CatalizadorConverter.entityToDtoList( p.getHabitos() );
    }
    
}