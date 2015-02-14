/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.ejb;

import java.util.List;
import javax.ejb.Stateless;

import migrainetracking.logica.interfaces.IServicioRegistroEpisodioMockRemote;
import migrainetracking.logica.interfaces.IServiciosCRUDMockLocal;
import migrainetracking.persistencia.mock.ServicioPersistenciaMock;

/**
 *
 * @author estudiante
 */
@Stateless
public class SevicioRegistroEpisodioMock implements IServicioRegistroEpisodioMockRemote {

    //---------------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------------
    
    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    private IServiciosCRUDMockLocal persistencia;
    
    
    //---------------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------------
    
    /**
     * Metodo Constructor de la clase
     */
    public SevicioRegistroEpisodioMock()
    {
        persistencia = new ServicioPersistenciaMock();
    }
    
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------

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
