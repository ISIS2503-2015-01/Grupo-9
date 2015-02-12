/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.dto.Medicamento;
import migrainetracking.dto.Sintoma;
import migrainetracking.logica.interfaces.ServicioRevisionEpisodiosMockLocal;
import migrainetracking.logica.interfaces.ServicioRevisionEpisodiosMockRemote;
import migrainetracking.logica.interfaces.ServiciosCRUDMockLocal;
import migrainetracking.persistencia.mock.ServicioPersistenciaMock;
/**
 *
 * @author estudiante
 */
@Stateless
public class ServicioRevisionEpisodiosMock implements ServicioRevisionEpisodiosMockLocal,ServicioRevisionEpisodiosMockRemote {

    //---------------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------------
    
    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    private ServiciosCRUDMockLocal persistencia;
    
    /**
     * Lista que contiene los episodios de dolor
     */
    private ArrayList<EpisodioDolor> episodios;
    
    //---------------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------------
    
    /**
     * Metodo Constructor de la clase
     */
    public ServicioRevisionEpisodiosMock()
    {
        persistencia = new ServicioPersistenciaMock();
        episodios = new ArrayList<EpisodioDolor>();
    }
    
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------

    @Override
    public List<EpisodioDolor> getEpisodiosById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EpisodioDolor> getEpisodioByFechas(Date fecha_in, Date fecha_fin, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sintoma> getSintomasDelEpisodio(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Catalizador> getCatalizadoresDelEpisodio(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medicamento> getMedicamentosDelEpisodio(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
