/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migrainetracking.logica.interfaces;

import java.util.List;
import javax.ejb.Local;
import migrainetracking.dto.Catalizador;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.dto.Paciente;
import migrainetracking.excepciones.NoExisteException;

/**
 *
 * @author estudiante
 */
@Local
public interface IServicioAnalisisMockLocal {
   
    /**
     * Devuelve una lista de catalizadores posibles, asociados con un episodio de dolor.
     * @param episodio
     * @return la lista de los catalizadores 
     * @throws NoExisteException si el episodio de dolor no existe
     */
    public List<Catalizador> getCatalizadores(EpisodioDolor episodio) throws NoExisteException;
    
    /**
     * Devuelve una lista de episodios de dolor, asociados a un numero de identificacion de un paciente
     * @param noIdPaciente Numero de identificacion del paciente
     * @return La lista de episodios
     * @throws NoExisteException si el paciente con el numero de identificacion no existe
     */
    public List<EpisodioDolor> getEpisodiosPaciente(String noIdPaciente) throws NoExisteException;
    

}
