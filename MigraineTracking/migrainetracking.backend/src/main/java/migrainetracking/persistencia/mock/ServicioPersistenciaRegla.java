/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.List;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.Regla;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaRegla;
import migrainetracking.utils.Utils;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaRegla implements IServicioPersistenciaRegla{
   

    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    
    /**
     * Lista de reglas en el sistema
     */
    private List<Regla> reglas;
    
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
    public ServicioPersistenciaRegla()
    {
        if(reglas==null)
        {
            reglas = new ArrayList<Regla>();
        }
    }

    /**
     * Metodo para manejar la instanciacion de la clase
     * @return la instanciacion de la clase
     */
    public static ServicioPersistenciaRegla getInstance()
    {
        if(instancia==null)
        {
            instancia = new ServicioPersistenciaRegla();
        }
        return instancia;
    }
    
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------

    /**
     * metodo que se encarga de crear una nuevva regla
     * @param obj la regla que se va a agregar
     * @throws OperacionInvalidaException si no se puede agregar la regla
     */
    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        Regla nueva = (Regla) obj;
        reglas.add(nueva);
        Utils.printf("Se ha agregado la nueva regla");
    }

    /**
     *Metodo que se encarga de editar una regla  
     * @param obj la regla que se va a editar
     */
    @Override
    public void update(Object obj) {
        Regla editar = (Regla) obj;
        int id = editar.getId();
        for(int i=0;i<reglas.size();i++)
        {
            Regla actual = reglas.get(i);
            if(actual.getId()==id)
            {
                reglas.set(i, editar);
            }
        }
    }

    /**
     * Metodo que se encarga de eliminar una regla
     * @param obj la regla que se va a eliminar
     * @throws OperacionInvalidaException si no se puede eliminar la regla
     */
    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        reglas.remove(obj);
    }

    /**
     * Metodo que se encarga de retornar todas las reglas
     * @param c la clase a la cual pertenecen los elementos que se quieren retornar
     * @return todas las reglas
     */
    @Override
    public List findAll(Class c) {
        return reglas;
    }

    /**
     * Metodo que retorna una regla dado su id
     * @param c la clase a la cual pertenece el objeto que se quiere retornar
     * @param id el id de la regla que se esta buscando
     * @return la regla que se esta buscando
     */
    @Override
    public Object findById(Class c, Object id) {
        int idL = Integer.parseInt(id.toString());
        for(int i=0;i<reglas.size();i++)
        {
            Regla actual = reglas.get(i);
            if(actual.getId()==idL)
            {
                return actual;
            }
        }
        return null;
    }
}