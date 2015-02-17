/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.List;
import migrainetracking.dto.Regla;
import migrainetracking.dto.Sintoma;
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
    private List<Sintoma> sintomas;
    
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
            sintomas = new ArrayList<Sintoma>();
        }
    }
    
    /**
     *Metodo que retorna la instancia de la clase 
     */
    public static ServicioPersistenciaSintoma getInstance()
    {
        if(instancia==null)
        {
            instancia = new ServicioPersistenciaSintoma();
        }
        return instancia;
    }
    
    //----------------------------------------------------------------------
    // Metodos
    //----------------------------------------------------------------------

    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        Sintoma nuevo = (Sintoma) obj;
        if(findById(Sintoma.class, nuevo.getNombre())!=null)
        {
            sintomas.add(nuevo);
            Utils.printf("Se ha agregado el sintoma con el nombre " + nuevo.getNombre());
        }
        else
        {
            throw new OperacionInvalidaException("El sintoma " + nuevo.getNombre() + " no se pudo agregar en el sistema, porque ya hay un sintoma con el mismsmo nombre");
        }
    }

    @Override
    public void update(Object obj) {
        Sintoma nuevo = (Sintoma) obj;
        for(int i=0;i<sintomas.size();i++)
        {
            Sintoma actual = sintomas.get(i);
            if(actual.getNombre().equals(nuevo.getNombre()))
            {
                sintomas.set(i, nuevo);
                Utils.printf("Se ha modificado el sintoma con nombre " + nuevo.getNombre());
            }
        }
    }

    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        Sintoma borrar = (Sintoma) obj;
        
        for(int i=0;i<sintomas.size();i++)
        {
            Sintoma actual = sintomas.get(i);
            if(actual.getNombre().equals(borrar.getNombre()))
            {
                sintomas.remove(i);
                Utils.printf("Se ha eliminado el sintoma con nombre " + borrar.getNombre());
                return;
            }
        }
        throw new OperacionInvalidaException ("El sintoma " + borrar.getNombre() + " no se puede eliminar porque no existe en el sistema");
    }

    @Override
    public List findAll(Class c) {
        return sintomas;
    }

    @Override
    public Object findById(Class c, Object id) {
        String nombre = id.toString();
        for(int i=0;i<sintomas.size();i++)
        {
            Sintoma actual = sintomas.get(i);
            if(actual.getNombre().equals(nombre))
            {
                return actual;
            }
        }
        return null;
    }
}
