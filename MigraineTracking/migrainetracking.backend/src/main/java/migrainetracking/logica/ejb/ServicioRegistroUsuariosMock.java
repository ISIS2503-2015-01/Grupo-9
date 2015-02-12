/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.Paciente;
import migrainetracking.logica.interfaces.ServicioRegistroUsuariosMockLocal;
import migrainetracking.logica.interfaces.ServicioRegistroUsuariosMockRemote;
import migrainetracking.logica.interfaces.ServiciosCRUDMockLocal;
import migrainetracking.persistencia.mock.ServicioPersistenciaMock;

/**
 *
 * @author Personal
 */
@Stateless
public class ServicioRegistroUsuariosMock implements ServicioRegistroUsuariosMockLocal,ServicioRegistroUsuariosMockRemote  {

    //---------------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------------
    
    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    private ServiciosCRUDMockLocal persistencia;
    
    /**
     * Lista de pacientes Registrados  
     */
    private ArrayList<Paciente> pacientes;
    
    //---------------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------------
    
    /**
     * Metodo Constructor de la clase
     */
    public ServicioRegistroUsuariosMock()
    {
        persistencia = new ServicioPersistenciaMock();
        pacientes = new ArrayList<Paciente>();
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
