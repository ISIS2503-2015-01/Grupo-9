/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.interfaces;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.dto.Medicamento;
import migrainetracking.dto.Sintoma;

/**
 *
 * @author estudiante
 */
@Local
public interface ServicioRevisionEpisodiosMockLocal {
    /**
     * Devuelve los episodios de dolor dado el ID de un paciente
     * @param id - Consecutivo identificador del paciente.
     * @return Los episodios de dolor de un paciente especifico.
     */
    public List<EpisodioDolor> getEpisodiosById(Long id);
    
    /**
     * Devuelve los episodios de dolor que ha registrado un paciente, en un periodo de tiempo especifico.
     * @param fecha_in - Fecha inicial del periodo de tiempo
     * @param fecha_fin - Fecha final del periodo de tiempo 
     * @param id - Consecutivo identificador del paciente.
     * @return 
     */
    public List<EpisodioDolor> getEpisodioByFechas(Date fecha_in,Date fecha_fin,Long id);
    
    
    // Revisiones del episodio en mas profundidad. (Para armar un reporte o informe del episodio)
    
    /**
     * Devuelve la lista de sintomas presentados en el episodio de dolor.
     * @param id - Consecutivo identificador del paciente.
     * @return 
     */
    public List<Sintoma> getSintomasDelEpisodio(Long id);
    
    /**
     * Devuelve la lista de catalizadores presentados en el episodio de dolor.
     * @param id - Consecutivo identificador del paciente.
     * @return 
     */
    public List<Catalizador> getCatalizadoresDelEpisodio(Long id);
    
    /**
     * Devuelve la lista de medicamentos presentados en el episodio de dolor.
     * @param id - Consecutivo identificador del paciente.
     * @return 
     */
    public List<Medicamento> getMedicamentosDelEpisodio(Long id);
}
