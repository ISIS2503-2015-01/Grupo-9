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

    /**
     * Se encarga de crear los objetos que recibe como parametro
     * @param o el objeto a crear
     * @return el id del objeto creado
     */
    @Override
    public Long create(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Se encarga de eliminar los objetos que le llegan como parametro
     * @param o el objeto a eliminar
     * @return el id del objeto que se elimino
     */
    @Override
    public Long delete(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que edita el objeto que recibe por parametro
     * @param o el objeto que se va a editar
     * @return el id del objeto que se edito
     */
    @Override
    public Long update(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Retorna todos los elementos de la clase que se dio como parametro
     * @param clase la clase de la cual se quieren tener todos los objetos
     * @return la lista con todos los objetos de la clase que se paso por parametro
     */
    @Override
    public List<Object> getAll(Class clase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
