/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.ejb;


import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.logica.interfaces.ServicioAnalisisMockLocal;
import migrainetracking.logica.interfaces.ServicioAnalisisMockRemote;
import migrainetracking.logica.interfaces.ServicioPersistenciaMockLocal;
import migrainetracking.persistencia.mock.ServicioPersistenciaMock;

/**
 *
 * @author estudiante
 */
@Stateless
public class ServicioAnalisisMock implements ServicioAnalisisMockLocal,ServicioAnalisisMockRemote {

    
    //---------------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------------
    
    /**
     * 
     */
    private ServicioPersistenciaMockLocal persistencia;
    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    
    public ServicioAnalisisMock()
    {
        persistencia = new ServicioPersistenciaMock();
    }
    
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------

    @Override
    public List<Catalizador> getCatalizadores(EpisodioDolor episodio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
