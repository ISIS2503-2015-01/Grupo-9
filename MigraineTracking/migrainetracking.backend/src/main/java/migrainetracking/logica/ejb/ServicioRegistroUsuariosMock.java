/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.ejb;


import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.Paciente;
import migrainetracking.logica.interfaces.IServicioRegistroUsuariosMockRemote;


/**
 * Este bean se encarga de los servicios de registro, ya sean CRUD o de otras consultas,
 * que se realizan sobre los usuarios (i.e Doctor y Paciente)
 */
@Stateless
public class ServicioRegistroUsuariosMock implements IServicioRegistroUsuariosMockRemote  {

    //---------------------------------------------------------------------------
    // Constantes
    //---------------------------------------------------------------------------

    //---------------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------------
    
    
    
    //---------------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------------
    
    /**
     * Metodo Constructor de la clase
     */
    public ServicioRegistroUsuariosMock()
    {
       
    }
    
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------
}
