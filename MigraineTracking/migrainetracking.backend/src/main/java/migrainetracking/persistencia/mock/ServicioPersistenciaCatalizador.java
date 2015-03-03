/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.mock;

import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.Entities.Catalizador;
import migrainetracking.persistencia.converters.CatalizadorConverter;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaCatalizador;
import migrainetracking.utils.Utils;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaCatalizador extends PersistenceServiceMaster implements IServicioPersistenciaCatalizador {
    
    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    
    /**
     * Instncia de la clase
     */
    public static ServicioPersistenciaCatalizador instancia;
    
    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    
    /**
     * Constructor sin argumentos
     */
    public ServicioPersistenciaCatalizador()
    {   
        super();
    }
    
    /**
     * Metodo que retorna la instancia de la clase
     * @return la instancia de la clase
     */
    public static ServicioPersistenciaCatalizador getInstance()
    {
        if(instancia==null || true)
            return new ServicioPersistenciaCatalizador();
        else
            return instancia;
    }
    
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------

    /**
     * Metodo que se encarga de crear un nuevo catalizador
     * @param obj el catalizador qeu se va a crear
     * @throws OperacionInvalidaException si el catalizador no existe
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        CatalizadorDTO nuevo = (CatalizadorDTO) obj;
        if(findById(CatalizadorDTO.class, nuevo)!=null)
        {
            throw new OperacionInvalidaException("No se puede crear el catalizador porque ya existe en el sistema");
        }
        EntityTransaction tran = this.entityMgr.getTransaction();
        Catalizador cat = CatalizadorConverter.dtoToEntity(nuevo);
        try
        {
            tran.begin();
            this.entityMgr.persist(cat);
            tran.commit();
            this.entityMgr.refresh(cat);
            Utils.printf("Se ha creado un catalizador nuevo");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            tran.rollback();
            Utils.printf("Ha ocurrido un error " +e.getMessage());
        }
    }

    /**
     * Metodo que se encarga de actualizar un catalizador
     * @param obj el catalizador que se va a actualizar
     */
    @Override
    public void update(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que se encarga de eliminar un catalizador
     * @param obj el catalizador que se va a eliminar
     * @throws OperacionInvalidaException si el catalizador no existe
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que retorna todos los catalizadores
     * @param c la clase a la que pertenecen los catalizadores
     * @return los catalizadores
     */
    @Override
    public List findAll(Class c) {
        Query q = this.entityMgr.createQuery("SELECT c FROM CATALIZADOR c;");
        List<Catalizador> catalizadores = q.getResultList();
        return CatalizadorConverter.entityToDtoList(catalizadores);
    }

    @Override
    public Object findById(Class c, Object id) {
        int idCat = Integer.parseInt(id.toString());
        Query q = this.entityMgr.createQuery("SELECT c FROM CATALIZADOR c WHERE c.id=:param;");
        q.setParameter("param", idCat);
        Catalizador sol;
        try
        {
            sol = (Catalizador)q.getSingleResult();
        }
        catch(Exception e)
        {
            return null;
        }
        return CatalizadorConverter.entityToDTO(sol);
    }    
}
