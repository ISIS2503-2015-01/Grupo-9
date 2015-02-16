/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package migrainetracking.persistencia.interfaces;

import java.util.List;
import migrainetracking.dto.EpisodioDolor;

/**
 *
 * @author Personal
 */
public interface IServicioPersistenciaPaciente extends IServicioPersistenciaMockRemote{

    public List<EpisodioDolor> getEpisodiosByPaciente(int noIdPaciente);

    public Long actualizarEpsiodio(EpisodioDolor editado, int noIdPaciente);

    public Long eliminarEpisodio(EpisodioDolor ep, int noIdPaciente);

    public Long agregarEpsiodio(EpisodioDolor nuevo, int noIdPaciente);
    
}
