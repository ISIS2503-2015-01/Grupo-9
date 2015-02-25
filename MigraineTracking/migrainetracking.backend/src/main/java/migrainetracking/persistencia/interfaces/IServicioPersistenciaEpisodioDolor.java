/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.interfaces;

import java.util.Date;
import java.util.List;
import migrainetracking.dto.EpisodioDolorDTO;

/**
 *
 * @author Personal
 */
public interface IServicioPersistenciaEpisodioDolor extends IServicioPersistenciaMockRemote {

    /**
     *
     * @param fecha_in 
     * @param fecha_fin
     * @param episodiosDelPaciente 
     * @return Los episodios de un paciente en un rango de fechas dado. La lista es vacia si no hay episodios en el rango.
     */
    public List<EpisodioDolorDTO> getEpisodioByFechas(Date fecha_in, Date fecha_fin, List<EpisodioDolorDTO> episodiosDelPaciente);

}
