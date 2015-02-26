/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.mock;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.conexion.PersistenceManager;
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
     * 
     */
    public static ServicioPersistenciaCatalizador instancia;
    
    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    public ServicioPersistenciaCatalizador()
    {   
        super();
    }
    
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

    @Override
    public void create(Object obj) throws OperacionInvalidaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object obj) throws OperacionInvalidaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findAll(Class c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object findById(Class c, Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
