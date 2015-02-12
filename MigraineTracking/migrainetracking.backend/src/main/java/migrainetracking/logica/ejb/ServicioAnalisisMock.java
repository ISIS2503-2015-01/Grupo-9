/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.ejb;


import java.util.List;
import java.util.*;
import javax.ejb.Stateless;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.logica.interfaces.ServicioAnalisisMockLocal;
import migrainetracking.logica.interfaces.ServicioAnalisisMockRemote;
import migrainetracking.logica.interfaces.ServiciosCRUDMockLocal;
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
     * Interface con referencia al servicio de persistencia en el sistema
     */
    private ServiciosCRUDMockLocal persistencia;
    
    /**
     *Lista de catalizadores en el sistema para poder hacer el analisis
     */
    private ArrayList<Catalizador> catalizadores;
    
    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    
    public ServicioAnalisisMock()
    {
        persistencia = new ServicioPersistenciaMock();
        catalizadores = new ArrayList<Catalizador>();
    }
    
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------

    @Override
    public List<Catalizador> getCatalizadores(EpisodioDolor episodio) {
        return catalizadores;
    }
}
