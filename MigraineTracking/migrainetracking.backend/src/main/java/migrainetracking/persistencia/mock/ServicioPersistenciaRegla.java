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

    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        Regla nueva = (Regla) obj;
        reglas.add(nueva);
        Utils.printf("Se ha agregado la nueva regla");
    }

    @Override
    public void update(Object obj) {
        new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        reglas.remove(obj);
    }

    @Override
    public List findAll(Class c) {
        return reglas;
    }

    @Override
    public Object findById(Class c, Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
