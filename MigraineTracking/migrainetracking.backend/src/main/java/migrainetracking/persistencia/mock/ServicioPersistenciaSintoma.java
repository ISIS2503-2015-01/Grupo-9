/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import migrainetracking.dto.ReglaDTO;
import migrainetracking.dto.SintomaDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.Entities.Sintoma;
import migrainetracking.persistencia.converters.SintomaConverter;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaSintoma;
import migrainetracking.utils.Utils;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaSintoma extends PersistenceServiceMaster implements IServicioPersistenciaSintoma {

    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    /**
     * Atributo para manejar la instanciacion
     */
    public static ServicioPersistenciaSintoma instancia;

    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    /**
     * Metodo constructor de la clase
     */
    public ServicioPersistenciaSintoma() {
        super();
    }

    /**
     * Metodo que retorna la instancia de la clase
     *
     * @return la instanciacion de la clase
     */
    public static ServicioPersistenciaSintoma getInstance() {
        if (instancia == null || true) {
            instancia = new ServicioPersistenciaSintoma();
        }
        return instancia;
    }

    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------
    /**
     * Metodo que se encarga de crear un sintoma
     *
     * @param obj el sintoma que se va a crear
     * @throws OperacionInvalidaException si no se puede crear el sintoma
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        SintomaDTO nuevo = (SintomaDTO) obj;
        if (findById(SintomaDTO.class, nuevo.getNombre()) != null) 
        {
            throw new OperacionInvalidaException("El sintoma " + nuevo.getNombre() + " no se pudo agregar en el sistema, porque ya hay un sintoma con el mismsmo nombre");
        }
        EntityTransaction tran = this.entityMgr.getTransaction();
        Sintoma sint = SintomaConverter.dtoToEntity(nuevo);
        try
        {
            tran.begin();
            this.entityMgr.persist(sint);
            tran.commit();
            this.entityMgr.refresh(sint);
            Utils.printf("Se ha creado un sintoma nuevo cuyo nombre es " + nuevo.getNombre());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            tran.rollback();
            Utils.printf("Se ha producido una excepcion " + e.getMessage());
        }
    }

    /**
     * Metodo que se encarga de editar el sintoma que se da por parametor
     *
     * @param obj el sintoma que se va a editar
     */
    @Override
    public void update(Object obj) {
        SintomaDTO nuevo = (SintomaDTO) obj;
        Utils.printf("Se ha modificado el sintoma con nombre " + nuevo.getNombre());

    }

    /**
     * Metodo para eliminar un sintoma dado por parametro
     *
     * @param obj el sintoma que se va a eliminar
     * @throws OperacionInvalidaException si no se puede eliminar el sintoma
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        SintomaDTO borrar = (SintomaDTO) obj;

        Utils.printf("Se ha eliminado el sintoma con nombre " + borrar.getNombre());

        throw new OperacionInvalidaException("El sintoma " + borrar.getNombre() + " no se puede eliminar porque no existe en el sistema");
    }

    /**
     * Metodo que se encarga de retornar todos los sintomas
     *
     * @param c la clase a la que pertenecen los elementos que se quieren
     * retornar
     * @return todos los sintomas
     */
    @Override
    public List findAll(Class c) {
        Query q = this.entityMgr.createQuery("SELECT s FROM SINTOMA s;");
        List<Sintoma> sintomas = q.getResultList();
        List<SintomaDTO> sintomasDTO = new ArrayList();
        for(int i=0;i<sintomas.size();i++)
        {   
            Sintoma actual = sintomas.get(i);
            SintomaDTO dto = SintomaConverter.entityToDto(actual);
            sintomasDTO.add(dto);
        }
        return sintomasDTO;
    }

    /**
     * Metodo que busca un sintoma dado su id
     *
     * @param c la clase a la que pertenece el elemento que se quiere retornar
     * @param id el id del sintoma que se busca
     * @return el sintoma que se esta buscando
     */
    @Override
    public SintomaDTO findById(Class c, Object id) {
        int SintId = Integer.parseInt(id.toString());
        Query q = this.entityMgr.createQuery("SELECT s FROM SINTOMA s WHERE s.id=:param;");
        q.setParameter("param", SintId);
        Sintoma sol;
        try
        {
            sol = (Sintoma)q.getSingleResult();
        }
        catch(Exception e)
        {
            return null;
        }
        return SintomaConverter.entityToDto(sol);
    }
}
