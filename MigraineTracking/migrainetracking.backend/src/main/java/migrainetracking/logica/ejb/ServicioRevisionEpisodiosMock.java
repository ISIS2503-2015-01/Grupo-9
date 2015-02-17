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
import migrainetracking.logica.interfaces.IServicioRevisionEpisodiosMockRemote;

/**
 *
 * @author estudiante
 */
@Stateless
public class ServicioRevisionEpisodiosMock implements IServicioRevisionEpisodiosMockRemote {

    //---------------------------------------------------------------------------
    // Atributos
    //---------------------------------------------------------------------------
    

    
    //---------------------------------------------------------------------------
    // Constructor
    //---------------------------------------------------------------------------
    
    /**
     * Metodo Constructor de la clase
     */
    public ServicioRevisionEpisodiosMock()
    {
       
    }
    
    //---------------------------------------------------------------------------
    // Metodos
    //---------------------------------------------------------------------------

    @Override
    public List<EpisodioDolor> getEpisodiosById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<EpisodioDolor> getEpisodioByFechas(Date fecha_in, Date fecha_fin, int noId) {
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

    @Override
    public List<EpisodioDolor> getEpisodioByFechas(Date fecha_in, Date fecha_fin, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
