/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.mock;

import java.util.List;
import migrainetracking.dto.Catalizador;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.persistencia.interfaces.IServicioPersistenciaCatalizador;

/**
 *
 * @author Personal
 */
public class ServicioPersistenciaCatalizador implements IServicioPersistenciaCatalizador{
    
    //----------------------------------------------------------------------
    // Atributos
    //----------------------------------------------------------------------
    private List<Catalizador> catalizadores;
    
    //----------------------------------------------------------------------
    // Constructores
    //----------------------------------------------------------------------
    
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
