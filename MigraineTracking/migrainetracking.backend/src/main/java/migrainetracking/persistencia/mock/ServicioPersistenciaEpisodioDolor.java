/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.dto.PacienteDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaEpisodioDolor;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaPaciente;
import migrainetracking.utils.Utils;
import org.fluttercode.datafactory.impl.DataFactory;

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
        
        Utils.printf("EpisodioDolor(" + ep.getId() + ") was created");

    }

    /**
     * Metodo que se encarga de editar a un episodio
     *
     * @param obj el episodio a editar
     */
    @Override
    public void update(Object obj) {
        EpisodioDolorDTO toEdit = (EpisodioDolorDTO) obj;
       
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
        return null;
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
        
        return null;
    }

    @Override
    public List<EpisodioDolorDTO> getEpisodioByFechas(Date fecha_in, Date fecha_fin, List<EpisodioDolorDTO> episodiosDelPaciente) {
        List<EpisodioDolorDTO> resp = new ArrayList<EpisodioDolorDTO>();
       
        return resp;
    }

}
