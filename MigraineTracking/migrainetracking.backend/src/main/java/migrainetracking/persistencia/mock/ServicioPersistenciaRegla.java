/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import com.sun.media.sound.SoftEnvelopeGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.dto.ReglaDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.Entities.Catalizador;
import migrainetracking.persistencia.Entities.Regla;
import migrainetracking.persistencia.converters.CatalizadorConverter;
import migrainetracking.persistencia.converters.ReglaConverter;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaRegla;
import migrainetracking.utils.Utils;

/**
 *
 * @author Personal
 */
 
public class ServicioPersistenciaRegla extends PersistenceServiceMaster  implements IServicioPersistenciaRegla {

    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    /**
     * Atributo para manejar la instanciacion
     */
    public static ServicioPersistenciaRegla instancia;

    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    /**
     * Metodo constructor de la clase
     */
    public ServicioPersistenciaRegla() {
        super();
    }

    /**
     * Metodo para manejar la instanciacion de la clase
     *
     * @return la instanciacion de la clase
     */
    public static ServicioPersistenciaRegla getInstance() {
        boolean sinSingleton = true;
        if (instancia == null || sinSingleton) {
            instancia = new ServicioPersistenciaRegla();
        }
        return instancia;
    }

    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------
    /**
     * metodo que se encarga de crear una nuevva regla
     *
     * @param obj la regla que se va a agregar
     * @throws OperacionInvalidaException si no se puede agregar la regla
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        ReglaDTO nueva = (ReglaDTO) obj;
        if(findById(ReglaDTO.class, nueva) !=null)
        {
            throw new OperacionInvalidaException("No se puede agregar la regla porque ya existe en el sistema");
        }
        EntityTransaction tran = this.entityMgr.getTransaction();
        Regla reg = ReglaConverter.dtoToEntity(nueva);
        try
        {
            tran.begin();
            this.entityMgr.persist(reg);
            tran.commit();
            this.entityMgr.refresh(reg);
            Utils.printf("Se ha creado una nueva regla");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            tran.rollback();
            Utils.printf("Se ha producido un error " + e.getMessage());
        }
    }

    /**
     * Metodo que se encarga de editar una regla
     *
     * @param obj la regla que se va a editar
     */
    @Override
    public void update(Object obj) {
        ReglaDTO existe = (ReglaDTO) obj;
        List<CatalizadorDTO> catalizadores =  existe.getEvitables();
        int id = existe.getId();
        int dolorMax = existe.getIntensidadDolorMax();
        int dolorMin = existe.getIntensidadDolorMin();
        String localizacion = existe.getLocalizacionDolor();
        Query q = this.entityMgr.createQuery("UPDATE REGLA SET INTENSIDADDOLORMAX:=param1, INTENDIADDOLORMIN:=param2, LOCALIZACIONDOLOR:=param3 WHERE ID:=param4");
        q.setParameter("param1", dolorMax);
        q.setParameter("param2", dolorMin);
        q.setParameter("param3", localizacion);
        q.setParameter("param4", id);
    }

    /**
     * Metodo que se encarga de eliminar una regla
     *
     * @param obj la regla que se va a eliminar
     * @throws OperacionInvalidaException si no se puede eliminar la regla
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        ReglaDTO borrar = (ReglaDTO)obj;
        if(findById(ReglaDTO.class, borrar)==null)
        {
            throw new OperacionInvalidaException("No se puede borrar la regla porque no existe");
        }
        EntityTransaction tran = this.entityMgr.getTransaction();
        Regla reg = ReglaConverter.dtoToEntity(borrar);
        try
        {
            tran.begin();
            this.entityMgr.remove(reg);
            tran.commit();
            this.entityMgr.refresh(reg);
            Utils.printf("Se ha borrado la regla exitosamente");
        }
        catch(Exception e )
        {
            e.printStackTrace();
            tran.rollback();
            Utils.printf("Se ha producido un error al borrar la regla: "+ e.getMessage());
        }
    }

    /**
     * Metodo que se encarga de retornar todas las reglas
     *
     * @param c la clase a la cual pertenecen los elementos que se quieren
     * retornar
     * @return todas las reglas
     */
    @Override
    public List findAll(Class c) {
        Query q = this.entityMgr.createQuery("SELECT r FROM REGLA r;");
        List<Regla> reglas = q.getResultList();
        List<ReglaDTO> reglasDTO = new ArrayList<ReglaDTO>();
        for(int i=0;i<reglas.size();i++)
        {
            Regla actual = reglas.get(i);
            ReglaDTO dto = ReglaConverter.entityToDto(actual);
            reglasDTO.add(dto);
        }
        return reglasDTO;
    }

    /**
     * Metodo que retorna una regla dado su id
     *
     * @param c la clase a la cual pertenece el objeto que se quiere retornar
     * @param id el id de la regla que se esta buscando
     * @return la regla que se esta buscando
     */
    @Override
    public Object findById(Class c, Object id) {
        int idReg = Integer.parseInt(id.toString());
        Query q = this.entityMgr.createQuery("SELECT r  FROM REGLA r WHERE r.id=param;");
        q.setParameter("param", idReg);
        Regla sol;
        try
        {
            sol = (Regla)q.getSingleResult();
        }
        catch(Exception e)
        {
            return null;
        }
        return ReglaConverter.entityToDto(sol);
    }

    @Override
    // NOTA: HAY TENTACION DE PASAR ESTA FUNCIONALIDAD AL EJB DE ANALISIS. 
    public List<CatalizadorDTO> getEvitables(EpisodioDolorDTO episodio) {
        //HashSet<Catalizador> conjuntoEvitables = new HashSet<Catalizador>();
        

        String strQuery = 
                "SELECT c.* FROM App.REGLA AS r JOIN App.REGLA_CATALIZADOR AS rc ON r.id=rc.Regla_id JOIN APP.CATALIZADOR AS c ON rc.Evitables_id=c.id WHERE r.localizacionDolor = ?locDolor AND ?intenDolor >= r.intensidadDolorMin AND  ?intenDolor <= r.intensidadDolorMax";
        //strQuery=
                //"SELECT c.* FROM APP.REGLA_CATALIZADOR as rc JOIN CATALIZADOR as c ON  rc.evitables_id=c.id WHERE rc.REGLA_ID in (SELECT r.ID FROM App.Regla AS r WHERE r.localizacionDolor = 'cuello' AND 5 >= r.intensidadDolorMin AND 5 <= r.intensidadDolorMax) --DERBY-PROPERTIES index=idx_Regla_intensidadesDolor"; 
             
                
        Query  q = this.entityMgr.createNativeQuery(strQuery,Catalizador.class); 
        q.setParameter("locDolor", episodio.getLocalizacion());
        q.setParameter("intenDolor", episodio.getIntensidadDolor());
        List<Catalizador> conjuntoEvitables = q.getResultList();

        return CatalizadorConverter.entityToDtoList( conjuntoEvitables );
    }
    
    /**
     * NOTA: HAY TENTACION DE PASAR ESTE METODO PARA EL EJB DE ANALISIS. (!) HAY POQUITAS REGLAS
     * Metodo donde se definen los criterios para validar un episodio de dolor versus las reglas.
     * @param episodio - Episodio de dolor
     * @return true en caso de que el episodio concuerde con los criterios definidos en las reglas. False en caso contrario
     */
    private boolean cumpleCriterios(EpisodioDolorDTO episodio , ReglaDTO regla){
       
        return false;
    }
}
