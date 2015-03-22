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
    
    /**
     * Metoso que se encarga de retornar los sintomas de un episodio cuyo id se pasa por parametro
     * @param id el id del episodio del cual se quieren los sintomas
     * @return los sintomas del episodio cuyo id se paso por parametro
     */
    public List<SintomaDTO> getSintomas(Long id) ;  
   
    /**
     * Metodo que retorna los catalizadores de un episodio dado cuyo id se da por parametro
     * @param id el id del episodio del cual se quieren los cataliadores
     * @return los catalizadores del episodio cuyo id se dio por parametro
     */
    public List<CatalizadorDTO> getCatalizadores(Long id) ;

   /**
    * Metodo que retorna los medicamentos de un episodio cuyo id se pasa por parametro
    * @param id el id del episodio del cual se quieren conocer los medicamentos
    * @return la lista de los medicamentos del episodio cuyo id se dio por parametro 
    */
    public List<MedicamentoDTO> getMedicamentos(Long id) ;
    
    /**
     * Metodo que retorna todos los episodios registrados en los ultimos dos dias
     * @return los episodios que fueron registrados en los ultimos dos dias
     */
    public List<EpisodioDolorDTO> getEpisodios2Dias();
    
}
