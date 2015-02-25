/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.List;
import migrainetracking.dto.ReglaDTO;
import migrainetracking.dto.SintomaDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaSintoma;
import migrainetracking.utils.Utils;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaSintoma implements IServicioPersistenciaSintoma{
    
    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    
    /**
     * Lista de sintomas en el sistema
     */
    private List<SintomaDTO> sintomas;
    
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
    public ServicioPersistenciaSintoma()
    {
        if(sintomas == null)
        {
            sintomas = new ArrayList<SintomaDTO>();
        }
    }
    
    /**
     *Metodo que retorna la instancia de la clase 
     * @return la instanciacion de la clase
     */
    public static ServicioPersistenciaSintoma getInstance()
    {
        if(instancia==null || true)
        {
            instancia = new ServicioPersistenciaSintoma();
        }
        return instancia;
    }
    
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------

    /**
     * Metodo que se encarga de crear un sintoma
     * @param obj el sintoma que se va a crear
     * @throws OperacionInvalidaException si no se puede crear el sintoma
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        SintomaDTO nuevo = (SintomaDTO) obj;
        if(findById(SintomaDTO.class, nuevo.getNombre())!=null)
        {
            sintomas.add(nuevo);
            Utils.printf("Se ha agregado el sintoma con el nombre " + nuevo.getNombre());
        }
        else
        {
            throw new OperacionInvalidaException("El sintoma " + nuevo.getNombre() + " no se pudo agregar en el sistema, porque ya hay un sintoma con el mismsmo nombre");
        }
    }

    /**
     * Metodo que se encarga de editar el sintoma que se da por parametor
     * @param obj el sintoma que se va a editar
     */
    @Override
    public void update(Object obj) {
        SintomaDTO nuevo = (SintomaDTO) obj;
        for(int i=0;i<sintomas.size();i++)
        {
            SintomaDTO actual = sintomas.get(i);
            if(actual.getNombre().equals(nuevo.getNombre()))
            {
                sintomas.set(i, nuevo);
                Utils.printf("Se ha modificado el sintoma con nombre " + nuevo.getNombre());
            }
        }
    }

    /**
     * Metodo para eliminar un sintoma dado por parametro
     * @param obj el sintoma que se va a eliminar
     * @throws OperacionInvalidaException si no se puede eliminar el sintoma
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        SintomaDTO borrar = (SintomaDTO) obj;
        
        for(int i=0;i<sintomas.size();i++)
        {
            SintomaDTO actual = sintomas.get(i);
            if(actual.getNombre().equals(borrar.getNombre()))
            {
                sintomas.remove(i);
                Utils.printf("Se ha eliminado el sintoma con nombre " + borrar.getNombre());
                return;
            }
        }
        throw new OperacionInvalidaException ("El sintoma " + borrar.getNombre() + " no se puede eliminar porque no existe en el sistema");
    }

    /**
     * Metodo que se encarga de retornar todos los sintomas
     * @param c la clase a la que pertenecen los elementos que se quieren retornar
     * @return todos los sintomas
     */
    @Override
    public List findAll(Class c) {
        return sintomas;
    }

    /**
     * Metodo que busca un sintoma dado su id
     * @param c la clase a la que pertenece el elemento que se quiere retornar
     * @param id el id del sintoma que se busca
     * @return el sintoma que se esta buscando
     */
    @Override
    public Object findById(Class c, Object id) {
        String nombre = id.toString();
        for(int i=0;i<sintomas.size();i++)
        {
            SintomaDTO actual = sintomas.get(i);
            if(actual.getNombre().equals(nombre))
            {
                return actual;
            }
        }
        return null;
    }
}
