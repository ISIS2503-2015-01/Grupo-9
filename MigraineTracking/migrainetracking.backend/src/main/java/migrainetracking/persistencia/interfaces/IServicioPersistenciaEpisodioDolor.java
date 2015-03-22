/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.persistencia.interfaces;

import java.util.Date;
import java.util.List;
import migrainetracking.dto.CatalizadorDTO;
import migrainetracking.dto.EpisodioDolorDTO;
import migrainetracking.dto.MedicamentoDTO;
import migrainetracking.dto.SintomaDTO;

/**
 *
 * @author Personal
 */
public interface IServicioPersistenciaEpisodioDolor extends IServicioPersistenciaMockRemote {

    /**
     *
     * @param fecha_in fecha de inicio de los episodios
     * @param fecha_fin la fecha fin de los episodios
     * @param episodiosDelPaciente los episodios del paciente del cual se quiere conocer los episodios en las fechas establecidas
     * @return Los episodios de un paciente en un rango de fechas dado. La lista es vacia si no hay episodios en el rango.
     */
    public List<EpisodioDolorDTO> getEpisodioByFechas(Date fecha_in, Date fecha_fin, int noId);
    
    
   
    public List<SintomaDTO> getSintomas(Long id) ;  
    

   
    public List<CatalizadorDTO> getCatalizadores(Long id) ;

   
    public List<MedicamentoDTO> getMedicamentos(Long id) ;
    
}
