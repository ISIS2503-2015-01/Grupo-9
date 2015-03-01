/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.List;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaMedicamento;
import migrainetracking.utils.Utils;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaMedicamento extends PersistenceServiceMaster implements IServicioPersistenciaMedicamento {
    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------

    /**
     * Atributo para modelar la instanciacion de la clase
     */
    public static ServicioPersistenciaMedicamento instancia;

    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    /**
     * Metodo constructor de la clase sin atributos
     */
    public ServicioPersistenciaMedicamento() {
        super();
    }

    /**
     * metodo que se encarga de la instanciacion de la clase
     *
     * @return la instancia de la clase
     */
    public static ServicioPersistenciaMedicamento getInstance() {

        instancia = new ServicioPersistenciaMedicamento();
        return instancia;

    }

    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------
    /**
     * Metodo que se encarga de crear un nuevo medicamento
     *
     * @param obj el medicamento a crear
     * @throws OperacionInvalidaException si no se puede crear el medicamento
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        MedicamentoDTO nuevo = (MedicamentoDTO) obj;

    }

    /**
     * Metodo que se encarga de editar un medicamento
     *
     * @param obj el medicamento a editar
     */
    @Override
    public void update(Object obj) {
        MedicamentoDTO actualizar = (MedicamentoDTO) obj;

    }

    /**
     * Metodo que se encarga de eliminar un medicamento
     *
     * @param obj el medicamento a eliminar
     * @throws OperacionInvalidaException si no se puede eliminar el medicamento
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        MedicamentoDTO borrar = (MedicamentoDTO) obj;

        if (findById(MedicamentoDTO.class, borrar.getNombre()) != null) {
            
            Utils.printf("El medicamento " + borrar.getNombre() + " ha sido eliiminado");
        } else {
            throw new OperacionInvalidaException("No se pudo eliminar el medicamento " + borrar.getNombre());
        }
    }

    /**
     * Metodo que se encarga de retornar a todos los medicamentos
     *
     * @param c la clase a la que pertenecen los elementos que se quieren
     * retornar
     * @return la lista con los medicamentos
     */
    @Override
    public List findAll(Class c) {
        return null;
    }

    /**
     * Metodo que retorna el medicamento con el id dado por parametro
     *
     * @param c la clase a la que pertence el objeto que se quiere buscar
     * @param id el id del medicamento
     * @return el medicamento con el id igual al que se dio por parametro
     */
    @Override
    public Object findById(Class c, Object id) {
        String nombre = id.toString();
        
        return null;
    } 
}
