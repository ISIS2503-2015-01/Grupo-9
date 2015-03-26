/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.dto.SintomaDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.Entities.EpisodioDolor;
import migrainetracking.persistencia.Entities.Paciente;
import migrainetracking.persistencia.Entities.Sintoma;
import migrainetracking.persistencia.converters.CatalizadorConverter;
import migrainetracking.persistencia.converters.EpisodioDolorConverter;
import migrainetracking.persistencia.converters.MedicamentoConverter;
import migrainetracking.persistencia.converters.SintomaConverter;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaEpisodioDolor;
import migrainetracking.utils.Utils;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaEpisodioDolor extends PersistenceServiceMaster implements IServicioPersistenciaEpisodioDolor {

    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    /**
     * Atributo para manejar la instanciacion de la clase
     */
    private static ServicioPersistenciaEpisodioDolor instancia;

    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    /**
     * Metodo constructor sin argumentos
     */
    public ServicioPersistenciaEpisodioDolor() {

        super();
    }

    /**
     * Metodo que se encarga de retornar la instancia de la clase
     *
     * @return la instancia de la clase
     */
    public static ServicioPersistenciaEpisodioDolor getInstance() {
        boolean sinSingleton = true;
        if (instancia == null || sinSingleton) {
            instancia = new ServicioPersistenciaEpisodioDolor();
        }
        return instancia;
    }
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------

    /**
     * Metodo que se encarga de crear un episodio de dolor
     *
     * @param obj el episodio que se va a crear
     * @throws OperacionInvalidaException si no se puede crear el episodio
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        EpisodioDolorDTO ep = (EpisodioDolorDTO) obj;

        EntityTransaction tran = this.entityMgr.getTransaction();
        try {
            tran.begin();
            EpisodioDolor epEntity = EpisodioDolorConverter.dtoToEntity(ep);
            this.entityMgr.persist(epEntity);
            tran.commit();
            this.entityMgr.refresh(epEntity);
            Utils.printf("EpisodioDolor(" + epEntity.getId() + ") was created");
            ep.setId(epEntity.getId());

        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            throw new OperacionInvalidaException(e.getMessage());
        }finally {
            entityMgr.clear();
            entityMgr.close();
        }

    }

    /**
     * Metodo que se encarga de editar a un episodio
     *
     * @param obj el episodio a editar
     */
    @Override
    public void update(Object obj) {
        EpisodioDolorDTO existe = (EpisodioDolorDTO) obj;
        EpisodioDolor epi = EpisodioDolorConverter.dtoToEntity(existe);
        EntityTransaction tran = this.entityMgr.getTransaction();
        try {
            tran.begin();
            this.entityMgr.merge(epi);
            tran.commit();
            Utils.printf("El episodio ha sido actualizado");
        } catch (Exception e) {
            e.printStackTrace();
            tran.rollback();
            Utils.printf("Se ha producido un error: " + e.getMessage());
        }finally {
            entityMgr.clear();
            entityMgr.close();
        }
    }

    /**
     * Metodo que se encarga de eliminar el episodio que se da por parametro
     *
     * @param obj el episodio a eliminar
     * @throws OperacionInvalidaException si no se puede eliminar el episodio
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        EpisodioDolorDTO ep = (EpisodioDolorDTO) obj;
        if (findById(EpisodioDolorDTO.class, ep) != null) {

            Utils.printf("EpisodioDolor(" + ep.getId() + ") was deleted");
        } else {
            throw new OperacionInvalidaException("El EpisodioDolor(" + ep.getId() + ") no existe en el sistema");
        }
    }

    /**
     * Metodo que se encarga de retornar todos los episodios en el sistema
     *
     * @param c la clase a la que pertencen los elementos que se quieren buscar
     * @return todos los episodios
     */
    @Override
    public List findAll(Class c) {
        return EpisodioDolorConverter.entityToDtoList(this.entityMgr.createNativeQuery(
                "SELECT e.* FROM APP.EpisodioDolor e FETCH FIRST 10 ROWS ONLY", EpisodioDolor.class).getResultList());
    }

    /**
     * Metodo que se encarga de retornar un episodio dado su id
     *
     * @param c la clase a la cual pertence el elemento que se quiere retornar
     * @param id el id del objeto que se quiere retornar
     * @return el episodio cuyo id es igual al id dado por parametro
     */
    @Override
    public Object findById(Class c, Object id) {
        Long nId = Long.parseLong(id.toString());

//        Query q = this.entityMgr.createQuery("SELECT e FROM EpisodioDolor e WHERE e.id=:nId").setParameter("nId", nId);
//        List<EpisodioDolor> temp = q.getResultList();
        EpisodioDolor resp = this.entityMgr.find(EpisodioDolor.class, id);
        if(resp == null){
            return null;
        }
        return EpisodioDolorConverter.entityToDto(resp);

    }

    /**
     * Metodo que retorna los episodios de un paciente en un periodo de tiempo
     *
     * @param fecha_in fecha en que comienzan los episodios
     * @param fecha_fin fecha en que terminan
     * @param episodiosDelPaciente los episodios del paciente
     * @return los episodios que ocurrieron entre la fecha de inicio y la fecha
     * de fin
     */
    @Override
    public List<EpisodioDolorDTO> getEpisodioByFechas(Date fecha_in, Date fecha_fin, int noId) {
        Query q = this.entityMgr.createQuery("SELECT ep FROM EpisodioDolor ep WHERE ep.paciente.noIdentificacion = :noID AND  :fecha1 <= ep.fecha AND ep.fecha <= :fecha2 ");
        q.setParameter("noID", noId);
        q.setParameter("fecha1", fecha_in);
        q.setParameter("fecha2", fecha_fin);
        List<EpisodioDolor> eps = q.getResultList();
        return EpisodioDolorConverter.entityToDtoList(eps);
    }
    
    /**
     * Metodo que retorna los episodios de los ultimos dos 2 
     * @return una lista con los episodis registrados en los ultimos dos dias
     */
    @Override
    public List<EpisodioDolorDTO> getEpisodios2Dias()
    {   
        Date fechaActual = new Date();
        Date fecha = new Date(fechaActual.getTime()-2*24*3600*1000);
        Query q = this.entityMgr.createQuery("SELECT ep FROM EpisodioDolor ep WHERE ep.fecha>=:dias");
        q.setParameter("dias", fecha);
        List<EpisodioDolor> eps = q.getResultList();
        return EpisodioDolorConverter.entityToDtoList(eps);
    } 
    
    @Override
    public List<SintomaDTO> getSintomas(Long id) {
        EpisodioDolor e = this.entityMgr.find(EpisodioDolor.class, id);
        List<SintomaDTO> resp = new ArrayList<SintomaDTO>();
        for( Sintoma s : e.getSintomas() ){
            resp.add( SintomaConverter.entityToDto(s) );
        }
        
        return resp;
    }

    /**
     * Metodo que retorna los catalizadores de un episodio dado su id
     * @param id el id del episodio
     * @return la lista de los catalizadores del episodio
     */
    @Override
    public List<CatalizadorDTO> getCatalizadores(Long id) {
        EpisodioDolor e = this.entityMgr.find(EpisodioDolor.class, id);
        List<CatalizadorDTO> resp = CatalizadorConverter.entityToDtoList( e.getCatalizadores() );
        return resp;
    }

    /**
     * Metodo que retorna los medicamentos de un episodio dado su id
     * @param id el id del episodio
     * @return los medicamentos del episodio
     */
    @Override
    public List<MedicamentoDTO> getMedicamentos(Long id) {
        EpisodioDolor e = this.entityMgr.find(EpisodioDolor.class, id);
        List<MedicamentoDTO> resp = MedicamentoConverter.entityToDtoList(e.getMedicamentosActuales());
        return resp;
    } 
}
