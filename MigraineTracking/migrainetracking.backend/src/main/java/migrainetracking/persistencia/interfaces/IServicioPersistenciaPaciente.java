/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.interfaces;

import java.util.List;
import migrainetracking.dto.EpisodioDolor;
import migrainetracking.dto.Paciente;

/**
 *
 * @author Personal
 */
public interface IServicioPersistenciaPaciente extends IServicioPersistenciaMockRemote{
    
    /**
     * Agrega un episodio a un paciente especifico
     * @param nuevo - Episodio de dolor nuevo.
     * @param noIdPaciente - Numero id del paciente al cual se le quiere agregar el ep.
     * @return 1. Id del paciente agregado
     *         2. Null en caso de que no exista el paciente.
     */
    public Long agregarEpsiodio(EpisodioDolor nuevo,int noIdPaciente);
    
    /**
     * 
     * @param viejo - Episodio viejo
     * @param noIdPaciente - Nuemro id del paciente al cual se le va a eliminar el episodio.
     * @return Id del episodio eliminado o null si el paciente no existe.
     */
    public Long eliminarEpisodio(EpisodioDolor viejo,int noIdPaciente);
    
    /**
     * 
     * @param toEdit - Episodio de dolor que se quiere editar.
     * @return Id del episodio editado. 
     */
    public Long actualizarEpsiodio(EpisodioDolor toEdit,int noIdPaciente);
    
    /**
     * <b>pre;</b> El paciente ya existe.
     * @param noId
     * @return Los episodios de un paciente en especifico
     */
    public List<EpisodioDolor> getEpisodiosByPaciente(int noId);
    
}
