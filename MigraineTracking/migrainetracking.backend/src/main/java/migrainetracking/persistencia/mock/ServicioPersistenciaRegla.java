/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.dto.ReglaDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
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
        
        Utils.printf("Se ha agregado la nueva regla");
    }

    /**
     * Metodo que se encarga de editar una regla
     *
     * @param obj la regla que se va a editar
     */
    @Override
    public void update(Object obj) {
        ReglaDTO editar = (ReglaDTO) obj;
        int id = editar.getId();
        
    }

    /**
     * Metodo que se encarga de eliminar una regla
     *
     * @param obj la regla que se va a eliminar
     * @throws OperacionInvalidaException si no se puede eliminar la regla
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        
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
        return null;
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
        int idL = Integer.parseInt(id.toString());
       
        return null;
    }

    @Override
    // NOTA: HAY TENTACION DE PASAR ESTA FUNCIONALIDAD AL EJB DE ANALISIS. 
    public List<CatalizadorDTO> getEvitables(EpisodioDolorDTO episodio) {
        HashSet<CatalizadorDTO> conjuntoEvitables = new HashSet<CatalizadorDTO>();
        
        return new ArrayList<CatalizadorDTO>(conjuntoEvitables);
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
