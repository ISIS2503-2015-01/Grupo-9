/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityTransaction;
import javax.transaction.UserTransaction;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.dto.PacienteDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.converters.PacienteConverter;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaPaciente;
import migrainetracking.utils.Utils;
import org.fluttercode.datafactory.impl.DataFactory;
import migrainetracking.persistencia.Entities.Paciente;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.SystemException;
import migrainetracking.persistencia.converters.DoctorConverter;
import migrainetracking.persistencia.converters.EpisodioDolorConverter;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaPaciente extends PersistenceServiceMaster implements IServicioPersistenciaPaciente 
{
    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------

    /**
     * Atruto para manejar la instanciacion de la clase
     */
    private static ServicioPersistenciaPaciente instancia;

    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    /**
     * Constructor sin argumentos
     */
    public ServicioPersistenciaPaciente() {
        super();

    }

    /**
     * Metodo que se encarga de la instanciacion de la clase
     *
     * @return la instancia de la clase
     */
    public static ServicioPersistenciaPaciente getInstance() {
        if (instancia == null || true) {
            return new ServicioPersistenciaPaciente();
        } else {
            return instancia;
        }
    }
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------

    /**
     * Metodo que se encarga de crear a un paciente
     *
     * @param obj el paciente que se va a crear
     * @throws OperacionInvalidaException si no se puede crear el paciente
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        PacienteDTO newPac = (PacienteDTO) obj;
        if ( findById( newPac.getNoIdentificacion() ) != null ){
               throw new OperacionInvalidaException("El paciente que quiere agregar ya existe en el sistema");  
           }
        try {
           EntityTransaction tran = entityMgr.getTransaction(); 
           
           try {
                tran.begin();
                Paciente p = PacienteConverter.dtoToEntity(newPac);
                this.entityMgr.persist( p );
                tran.commit();
                this.entityMgr.refresh( p );
                Utils.printf("New paciente(" + newPac.getNombre() + ") was ADDED");       
           } catch(Exception e){
               tran.rollback();
               Utils.printf(">>>>> Exception : "+e.getMessage());
               e.printStackTrace();
           }
        } catch (Exception e) {
        }
        
            
    }

    /**
     * Metodo que se encarga de editar el paciente que se da por parametro
     *
     * @param obj el paciente que se va a editar
     */
    @Override
    public void update(Object obj){
        PacienteDTO existe = (PacienteDTO)obj;
        int id = existe.getNoIdentificacion();
        String nombre = existe.getNombre();
        Date fechaNacimiento = existe.getFechaNacimiento();
        int estatura = existe.getEstatura();
        int peso=existe.getPeso();
        Query q = this.entityMgr.createQuery("UPDATE APP.PACIENTE SET ESTATURA:=param3, FECHANACIMIENTO:=param2,NOMBRE:=param1, PESO:=param4 WHERE NOIDENTIFICACION:=param5");
        q.setParameter("param1", nombre);
        q.setParameter("param2", fechaNacimiento);
        q.setParameter("param3", estatura);
        q.setParameter("param4", peso);
        q.setParameter("param5", id);
    }

    /**
     * Metodo que se encarga de eliminar el paciente que se da por parametro
     *
     * @param obj el paciente que se quiere eliminar
     * @throws OperacionInvalidaException si no se puede eliminar el paciente
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        PacienteDTO eliminar = (PacienteDTO)obj;
        if(findById(PacienteDTO.class, eliminar)==null)
        {
            throw new OperacionInvalidaException("No se puede eliminar el objeto porque no existe");
        }
        Paciente cat = PacienteConverter.dtoToEntity(eliminar);
        EntityTransaction tran = this.entityMgr.getTransaction();
        try
        {
            tran.begin();
            this.entityMgr.remove(cat);
            tran.commit();
            this.entityMgr.refresh(cat);
            Utils.printf("El paciente ha sido eliminado");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            tran.rollback();
            Utils.printf("Se ha producido un error: " + e.getMessage());
        }
    }

    /**
     * Metodo que se encarga de retornar a todos los paciente
     *
     * @param c la clase a la que pertenecen los elementos que se quieren
     * retornar
     * @return todos los pacientes
     */
    @Override
    public List findAll(Class c) {
        Query q = this.entityMgr.createQuery("SELECT p FROM Paciente p", null);
        List<Paciente> resp = q.getResultList();
        return PacienteConverter.entityToDtoList(resp);
    }

    /**
     * Metodo que retorna al paciente que tiene el id dado por parametro
     *
     * @param c la clase a la que pertence el elemento que se va a retornar
     * @param id el id del paciente
     * @return el paciente con el id dado por parameto
     */
    @Override
    public Object findById(Class c, Object id) {
        int noId = Integer.parseInt(id.toString());
        try {
            
            Paciente pac =this.entityMgr.find(Paciente.class, noId);
            PacienteDTO p =  PacienteConverter.entityToDto( pac );
            return p;
        } catch (NoResultException e) {
        }
        return null;
    }

    //----- find all hace la misma monda.
    public List<PacienteDTO> getPacientes() {
        Query q = this.entityMgr.createNativeQuery("SELECT p.NoIdentificacion,p.nombre, FROM Paciente p",Paciente.class);

        //q.setMaxResults();
        List<Paciente> resp = q.getResultList();
        return PacienteConverter.entityToDtoList(resp);
    }
    
    public Paciente findById(Object id) {
        try {
            Paciente p = this.entityMgr.find(Paciente.class, id);
            return p;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * metodo que se encarga de agregarle un episodio al paciente
     *
     * @deprecated El metodo se deprecia porque esta operacion se mantiene en los modulos de registros de Episodios.
     * @param nuevo el nuevo episodio
     * @param noIdPaciente el id del paciente al cual se le va a agregar el
     * episodio
     * @return el id del paciente al cual se le agrego el episodio. null en caso
     * que no se halla agregado
     */
    @Override
    @Deprecated
    public Long agregarEpsiodio(EpisodioDolorDTO nuevo, int noIdPaciente) {
        PacienteDTO p = (PacienteDTO) findById(PacienteDTO.class, noIdPaciente);
        if (p != null) {
            
            return (long) noIdPaciente;
        } else {
            return null;
        }
    }

    /**
     * Metodo que se encarga de eliminar un episodio de un paciente
     *
     * @deprecated El metodo se deprecia porque esta operacion se mantiene en los modulos de registros de Episodios.
     * @param viejo el episodio que se va a eliminar
     * @param noIdPaciente el id del paciene dueno del episodio
     * @return el id del paciente al cual se le elimino el episodio
     */
    @Override
    @Deprecated
    public Long eliminarEpisodio(EpisodioDolorDTO viejo, int noIdPaciente) {
        PacienteDTO p = (PacienteDTO) findById(PacienteDTO.class, noIdPaciente);
        if (p != null) {
          
            return (long) noIdPaciente;
        } else {
            return null;
        }
    }

    /**
     * Metodo que se encarga de actualizar un episodio
     *
     * @deprecated El metodo se deprecia porque esta operacion se mantiene en los modulos de registros de Episodios.
     * @param toEdit el episodio que se va a editar
     * @param noIdPaciente el id del paciente dueno del episodio
     * @return el id del paciente al cual se le edito el episodio
     * 
     */
    @Override
    @Deprecated
    public Long actualizarEpsiodio(EpisodioDolorDTO toEdit, int noIdPaciente) {
        PacienteDTO p = (PacienteDTO) findById(PacienteDTO.class, noIdPaciente);
        if (p != null) {
           
        }
        return null;
    }

    /**
     * Metodo que se encarga de retornar los episodios de un paciente dado su id
     *
     * @param noId el id del paciente
     * @return los episodios del paciente
     */
    @Override
    public List<EpisodioDolorDTO> getEpisodiosByPaciente(int noId) {

        try {
            Paciente p = this.entityMgr.find(Paciente.class, noId);
            return EpisodioDolorConverter.entityToDtoList( p.getEpisodios() );
        } catch (NoResultException e) {
        }
        return null;
    }  
}