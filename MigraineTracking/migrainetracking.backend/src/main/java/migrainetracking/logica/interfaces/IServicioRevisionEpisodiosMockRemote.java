/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.logica.interfaces;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.dto.SintomaDTO;
import migrainetracking.excepciones.NoExisteException;

/**
 *
 * @author Personal
 */
@Remote
public interface IServicioRevisionEpisodiosMockRemote {
    
    /**
     * Devuelve los episodios de dolor dado el ID de un paciente
     * @param id - Consecutivo identificador del paciente.
     * @return Los episodios de dolor de un paciente especifico.
     */
    public List<EpisodioDolorDTO> getEpisodiosById(Long id);
    
    /**
     * Devuelve los episodios de dolor que ha registrado un paciente, en un periodo de tiempo especifico.
     * @param fecha_in - Fecha inicial del periodo de tiempo
     * @param fecha_fin - Fecha final del periodo de tiempo 
     * @param noId - Cedula o numero de id del paciente.
     * @return los episodios que se encuentran entre las fechas dadas por parametros
     * @throws migrainetracking.excepciones.NoExisteException  - Cuando no existe el paciente.
     */
    public List<EpisodioDolorDTO> getEpisodioByFechas(Date fecha_in,Date fecha_fin,int noId) throws NoExisteException;
    
    
    // Revisiones del episodio en mas profundidad. (Para armar un reporte o informe del episodio)
    
    /**
     * Devuelve la lista de sintomas presentados en el episodio de dolor.
     * @param id - Consecutivo identificador del episodio.
     * @return los sintomas del episodio
     */
    public List<SintomaDTO> getSintomasDelEpisodio(Long id);
    
    /**
     * Devuelve la lista de catalizadores presentados en el episodio de dolor.
     * @param id - Consecutivo identificador del episodio.
     * @return los catalizadores del episodio
     */
    public List<CatalizadorDTO> getCatalizadoresDelEpisodio(Long id);
    
    /**
     * Devuelve la lista de medicamentos presentados en el episodio de dolor.
     * @param id - Consecutivo identificador del episodio.
     * @return los medicamentos del episodio
     */
    public List<MedicamentoDTO> getMedicamentosDelEpisodio(Long id);
}

