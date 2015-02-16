/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.ejb;

import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.Doctor;
import migrainetracking.dto.Medicamento;
import migrainetracking.dto.Regla;
import migrainetracking.dto.Sintoma;
import migrainetracking.logica.interfaces.IServiciosCRUDMockRemote;

/**
 *
 * Este bean se encarga de implementar los servicios CRUD de: Catalizador,Regla,Sintoma,Medicamento.
 */
@Stateless
public class ServiciosCRUDMock implements IServiciosCRUDMockRemote {

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
