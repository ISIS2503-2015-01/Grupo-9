/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import java.util.List;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.logica.interfaces.ServicioPersistenciaMockRemote;
import migrainetracking.logica.interfaces.ServicioPersistenciaMockLocal;

/**
 *
 * @author estudiante
 */
public class ServicioPersistenciaMock implements ServicioPersistenciaMockRemote, ServicioPersistenciaMockLocal {
    
    public ServicioPersistenciaMock()
    {
        
    }

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
