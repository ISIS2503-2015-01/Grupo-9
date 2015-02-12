/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.mock;

import java.util.List;
import migrainetracking.excepciones.OperacionInvalidaException;
import migrainetracking.logica.interfaces.ServiciosCRUDMockRemote;
import migrainetracking.logica.interfaces.ServiciosCRUDMockLocal;

/**
 *
 * @author estudiante
 */
public class ServicioPersistenciaMock implements ServiciosCRUDMockRemote, ServiciosCRUDMockLocal {
    
    public ServicioPersistenciaMock()
    {
        
    }

    @Override
    public Long create(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long delete(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long update(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> getAll(Class clase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
